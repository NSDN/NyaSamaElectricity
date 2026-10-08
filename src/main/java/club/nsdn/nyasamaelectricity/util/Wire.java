package club.nsdn.nyasamaelectricity.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

/**
 * Cable / wire / pillar geometry, ported from the 1.12.2 {@code util.Wire} (200 lines).
 *
 * <p>1.12.2 built its geometry out of {@code cn.ac.nya.rawmdl.RawQuadCube} moved by
 * {@code RawQuadGroup} and baked them into {@code BakedQuad}s that the three fast renderers replayed
 * into the chunk buffer. Decision D4 discards that pipeline ({@code rawmdl} / {@code forgeobj} /
 * {@code mutable}), so the two classes it stood on are re-expressed here, inside this module, with the
 * same numbers:</p>
 * <ul>
 *   <li>{@link Box} is {@code RawQuadBase}+{@code RawQuadCube}: the same eight corners in the same
 *       order (0..3 on the top face, 4..7 on the bottom, centred in X/Z and grown upwards from Y=0),
 *       the same {@code translateCoord}/{@code rotateAroundX|Y|Z}/{@code rotateAroundVector} matrices
 *       (degree-based, right-hand rule) and the same {@code rotateToVec} default from
 *       {@code IRawElement}. The two degenerate cases of the original are kept verbatim: a zero-length
 *       target vector returns unchanged, and a vertical target substitutes {@code z = 1};</li>
 *   <li>the group transform of {@code RawQuadGroup} is the loop over the returned list applied by
 *       {@link #rotateToVec} / {@link #translate}, in the same order the 1.12.2 methods used
 *       (rotate first, then translate).</li>
 * </ul>
 *
 * <p><b>What replaces {@code bake} + {@code BakedQuad} + {@code BufferBuilder}.</b> The 1.12.2
 * renderers dumped each baked quad's {@code DefaultVertexFormats.ITEM} ints into the chunk
 * {@code BufferBuilder}, then overrode the lightmap with the constant {@code 15728640} and multiplied
 * the vertex colour by the face's diffuse factor. {@link #draw} emits the same six faces per box
 * straight into a {@link VertexConsumer}, using the 1.21.1 equivalents so the picture is unchanged:</p>
 * <ul>
 *   <li>face order, corner order and the full-sprite UVs ({@code getInterpolatedU/V(0..16)}) are the
 *       ones {@code RawQuadCube#bake} wrote; the 1.21.1 spelling of the full sprite range is
 *       {@code getU0/getU1/getV0/getV1} (v2's {@code uMin=0}/{@code uMax=16} pair);</li>
 *   <li>{@code 15728640 == 0xF00000} is {@code LightTexture.pack(0, 15)} -- sky light 15, block light
 *       0, exactly as the constant decodes, and it was used for all four vertices;</li>
 *   <li>{@code LightUtil.diffuseLight(face)} (1.12.2 Forge) becomes
 *       {@code BlockAndTintGetter#getShade(face, true)}, the same vanilla face-brightness table, read
 *       from the level the block entity belongs to (it also carries the End's constant-ambient-light
 *       replacement of that table);</li>
 *   <li>the render type is {@link RenderType#solid()}: all three 1.12.2 node blocks declared
 *       {@code getBlockLayer() == BlockRenderLayer.SOLID}, and the fast renderers appended into that
 *       layer's chunk buffer.</li>
 * </ul>
 *
 * <p><b>Two deliberate differences, both renderer state:</b></p>
 * <ol>
 *   <li>the {@code srcQuads}/{@code dstQuads} caches lived on the block entity in 1.12.2 and are not
 *       ported (they are client-only fields of a class that must also load on the server). The
 *       geometry is therefore rebuilt each frame; the emitted vertex count is identical, only the
 *       {@code BakedQuad} objects are not kept between frames;</li>
 *   <li>the 1.12.2 renderers ended with {@code buffer.setTranslation(x - posX, ...)}, the
 *       partial-tick offset of the fast-renderer call. A 1.21.1 block entity renderer is already
 *       positioned at its block by the dispatcher, so {@link #draw} expects the caller to have applied
 *       {@code -blockPos} and nothing else -- the geometry itself is in absolute world coordinates,
 *       exactly as {@code Wire} produced it in 1.12.2.</li>
 * </ol>
 *
 * <p>This class touches client-only types, so it is only reachable from the three renderers in
 * {@code renderer.tileblock}; nothing on the server side refers to it (1.21.2 will make that explicit
 * with the client source set).</p>
 */
public final class Wire {

    private Wire() {
    }

    /**
     * The 1.12.2 fast renderers' lightmap constant {@code 15728640}, i.e.
     * {@code LightTexture.pack(blockLight = 0, skyLight = 15)}: cables were lit by the sky only.
     */
    public static final int FLAT_LIGHT = LightTexture.pack(0, 15);

    /*
     * RawQuadCube#bake face order and corner order, kept verbatim, plus which end of the sprite each
     * corner samples: 0 = u0/v0, 1 = u1/v1 of the full sprite. Indexed by Direction#ordinal()
     * (DOWN, UP, NORTH, SOUTH, WEST, EAST).
     */
    private static final int[][] FACE_VERTICES = {
            { 7, 6, 5, 4 }, // DOWN   - Yneg
            { 0, 1, 2, 3 }, // UP     - Ypos
            { 2, 1, 5, 6 }, // NORTH  - Zneg
            { 3, 7, 4, 0 }, // SOUTH  - Zpos
            { 3, 2, 6, 7 }, // WEST   - Xneg
            { 1, 0, 4, 5 }, // EAST   - Xpos
    };

    /** The per-corner sprite sample of each face above, as {u,v} pairs: 0 -> min, 1 -> max. */
    private static final int[][] FACE_UV = {
            { 0, 0, 0, 1, 1, 1, 1, 0 }, // DOWN
            { 1, 1, 1, 0, 0, 0, 0, 1 }, // UP
            { 1, 0, 0, 0, 0, 1, 1, 1 }, // NORTH
            { 0, 0, 0, 1, 1, 1, 1, 0 }, // SOUTH
            { 1, 0, 0, 0, 0, 1, 1, 1 }, // WEST
            { 1, 0, 0, 0, 0, 1, 1, 1 }, // EAST
    };

    // Catenary (not railway)
    protected static class Catenary {

        private final float u, x1, k;

        public Catenary(double from, double to, double dist, double drop) {
            this((float) from, (float) to, (float) dist, (float) drop);
        }

        public Catenary(float yFrom, float yTo, float d, float h) {
            float h1, a, offset;
            if (yFrom < yTo) {
                h1 = yFrom;
                a = yTo - yFrom;
            } else {
                h1 = yTo;
                a = yFrom - yTo;
            }

            if (a < 1e-4)
                a = 1e-4F;    //Prevent NAN

            float L = calcL(a, h, d);
            float L1 = -h * L / a + Mth.sqrt(h * (a + h) * (L * L - a * a)) / a;
            float u = 2 * h / (L1 * L1 - h * h);
            float x1 = MathAssist.asinh(u * L1) / u;
            float k = h1 - h - 1 / u;

            if (yFrom > yTo)
                x1 = x1 + d - MathAssist.acosh(u * (yTo - k)) / u - x1;

            this.u = u;
            this.x1 = x1;
            this.k = k;
        }

        public Float apply(Float x) {
            return (float) (Math.cosh(u * (x - x1)) / u + k);
        }

        public float derivative(float x) {
            return (float) (Math.sinh(u * (x - x1)));
        }

        public static float calcL(float a, float h, float d) {
            float lower = Mth.sqrt(d * d + a * a);
            float upper = 2 * h + d + a;

            float TV = (upper + lower) / 2;
            int n = 0;
            while ((upper - lower) > 1e-4 && ++n < 100) {
                if (calcD(a, TV, h) > d)
                    upper = TV;
                else
                    lower = TV;

                TV = (upper + lower) / 2;
            }

            return TV;
        }

        public static float calcD(float a, float L, float h) {
            float q = 2 * Mth.sqrt(h * (a + h) * (L * L - a * a));
            return ((L * L - a * a) * (a + 2 * h) - L * q)
                    /
                    (a * a) * MathAssist.atanh(a * a / (L * (a + 2 * h) - q));
        }

    }

    /**
     * A textured axis-aligned box, the port of {@code cn.ac.nya.rawmdl.RawQuadCube} (see the class
     * note for what is and is not reproduced). Vertices are absolute world coordinates: the 1.12.2
     * {@code Wire} built each box at the origin, then moved it with {@code translateCoord} and the
     * group-level {@code rotateToVec}/{@code translateCoord}.
     */
    public static final class Box {

        private final float[][] vertexes;
        private final TextureAtlasSprite sprite;

        public Box(float maxX, float maxY, float maxZ, TextureAtlasSprite sprite) {
            this.vertexes = new float[8][];
            float x = maxX / 2.0F;
            float z = maxZ / 2.0F;

            //Top
            this.vertexes[0] = new float[] { x, maxY, z };
            this.vertexes[1] = new float[] { x, maxY, -z };
            this.vertexes[2] = new float[] { -x, maxY, -z };
            this.vertexes[3] = new float[] { -x, maxY, z };

            //Bottom
            this.vertexes[4] = new float[] { x, 0, z };
            this.vertexes[5] = new float[] { x, 0, -z };
            this.vertexes[6] = new float[] { -x, 0, -z };
            this.vertexes[7] = new float[] { -x, 0, z };

            this.sprite = sprite;
        }

        public Box translateCoord(float x, float y, float z) {
            for (int i = 0; i < this.vertexes.length; i++) {
                this.vertexes[i][0] += x;
                this.vertexes[i][1] += y;
                this.vertexes[i][2] += z;
            }

            return this;
        }

        /** {@code RawQuadBase#rotateAroundX}: right-hand rule about +X, degrees. */
        public Box rotateAroundX(float angle) {
            float cos = MathAssist.cosAngle(angle);
            float sin = MathAssist.sinAngle(angle);

            for (int i = 0; i < this.vertexes.length; i++) {
                float y = this.vertexes[i][1] * cos - this.vertexes[i][2] * sin;
                float z = this.vertexes[i][1] * sin + this.vertexes[i][2] * cos;
                this.vertexes[i][1] = y;
                this.vertexes[i][2] = z;
            }

            return this;
        }

        /** {@code RawQuadBase#rotateAroundY}: right-hand rule about +Y, degrees. */
        public Box rotateAroundY(float angle) {
            float cos = MathAssist.cosAngle(angle);
            float sin = MathAssist.sinAngle(angle);

            for (int i = 0; i < this.vertexes.length; i++) {
                float x = this.vertexes[i][0] * cos + this.vertexes[i][2] * sin;
                float z = -this.vertexes[i][0] * sin + this.vertexes[i][2] * cos;
                this.vertexes[i][0] = x;
                this.vertexes[i][2] = z;
            }

            return this;
        }

        /** {@code RawQuadBase#rotateAroundZ}: right-hand rule about +Z, degrees. */
        public Box rotateAroundZ(float angle) {
            float cos = MathAssist.cosAngle(angle);
            float sin = MathAssist.sinAngle(angle);

            for (int i = 0; i < this.vertexes.length; i++) {
                float x = this.vertexes[i][0] * cos - this.vertexes[i][1] * sin;
                float y = this.vertexes[i][0] * sin + this.vertexes[i][1] * cos;
                this.vertexes[i][0] = x;
                this.vertexes[i][1] = y;
            }

            return this;
        }

        /** {@code RawQuadBase#rotateAroundVector}: Rodrigues' rotation about an arbitrary axis. */
        public Box rotateAroundVector(float angle, float x, float y, float z) {
            //Normalize the axis vector
            float length = Mth.sqrt(x * x + y * y + z * z);
            if (length < 1e-12f)
                return this;    //length is 0, [x, y, z] does not represent a vector with valid direction

            x = x / length;
            y = y / length;
            z = z / length;

            angle = angle * 0.01745329252F;    //Cast to radian
            float cos = Mth.cos(angle);
            float sin = Mth.sin(angle);

            for (int i = 0; i < this.vertexes.length; i++) {
                float d0 = this.vertexes[i][0] * (cos + x * x * (1 - cos)) + this.vertexes[i][1] * (x * y * (1 - cos) - z * sin) + this.vertexes[i][2] * (x * z * (1 - cos) + y * sin);
                float d1 = this.vertexes[i][0] * (x * y * (1 - cos) + z * sin) + this.vertexes[i][1] * (cos + y * y * (1 - cos)) + this.vertexes[i][2] * (y * z * (1 - cos) - x * sin);
                float d2 = this.vertexes[i][0] * (x * z * (1 - cos) - y * sin) + this.vertexes[i][1] * (y * z * (1 - cos) + x * sin) + this.vertexes[i][2] * (cos + z * z * (1 - cos));
                this.vertexes[i][0] = d0;
                this.vertexes[i][1] = d1;
                this.vertexes[i][2] = d2;
            }

            return this;
        }

        /** {@code IRawElement#rotateToVec}: aim the box's +Y axis at the target vector. */
        public Box rotateToVec(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
            float distance = MathAssist.distanceOf(xStart, yStart, zStart, xEnd, yEnd, zEnd);
            if (distance < 1e-12f)
                return this;    //length is 0, [x, y, z] does not represent a vector with valid direction

            this.rotateAroundY((float) (Math.atan2(zStart - zEnd, xEnd - xStart) * 180 / Math.PI));
            float z = zEnd - zStart;
            float x = xStart - xEnd;

            if (Mth.abs(z) < 1e-12f && Mth.abs(x) < 1e-12f)
                z = 1;    //Vertical

            this.rotateAroundVector((float) (Math.acos((yEnd - yStart) / distance) * 180 / Math.PI), z, 0, x);

            return this;
        }
    }

    // Catenary cable (not railway)
    protected static List<Box> renderCatenaryCable(Vec3 from, Vec3 to, boolean half, float drop, float thickness, TextureAtlasSprite texture) {
        List<Box> ret = new ArrayList<>();

        Vec3 vec = to.subtract(from);
        double len = vec.length();
        float steps = len < 64 ? 128 : Mth.floor(len * 2);
        float y0 = 0, y1;

        float d = Mth.sqrt((float) ((from.x - to.x) * (from.x - to.x) + (from.z - to.z) * (from.z - to.z)));
        float step = d / steps;
        Catenary c = new Catenary(0.0, to.y - from.y, d, drop);

        //Origin
        for (int i = 0; i < steps / (half ? 2 : 1); i++) {
            y1 = c.apply((i + 1) * step);

            ret.add((new Box(thickness, Mth.sqrt(step * step + (y1 - y0) * (y1 - y0)), thickness, texture))
                    .rotateAroundZ(-(float) Math.atan2(y0 - y1, step) * 180F / (float) Math.PI)
                    .translateCoord(-y0, i * step, 0)
            );
            y0 = y1;
        }

        rotateToVec(ret, from.x, 0, from.z, to.x, 0, to.z);
        translate(ret, (float) from.x, (float) from.y, (float) from.z);
        return ret;
    }

    // Catenary cable (not railway)
    public static List<Box> renderCable(Vec3 from, Vec3 to, TextureAtlasSprite texture) {
        Vec3 vec = to.subtract(from);
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        double drop = 0.05 * hlen;
        if (Math.abs(vec.y) < 0.0625)
            vec = to.add(0, 0.0625, 0);
        else
            vec = to;
        return renderCatenaryCable(from, vec, false, (float) drop, 0.0625F, texture);
    }

    // Straight cable (not railway)
    public static List<Box> renderHardCable(Vec3 from, Vec3 to, TextureAtlasSprite texture) {
        List<Box> ret = new ArrayList<>();

        float size = 0.125F;
        Vec3 vec = to.subtract(from);
        double len = vec.length();
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        float angle = (float) Mth.atan2(vec.y, hlen);
        if (hlen == 0) angle = (vec.y > 0) ? 0 : (float) Math.PI;

        ret.add((new Box(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
        );

        rotateToVec(ret, from.x, 0, from.z, to.x, 0, to.z);
        translate(ret, (float) from.x, (float) from.y, (float) from.z);
        return ret;
    }

    // Straight pillar
    public static List<Box> renderPillar(Vec3 from, Vec3 to, TextureAtlasSprite texture) {
        List<Box> ret = new ArrayList<>();

        float size = 0.75F;
        Vec3 vec = to.subtract(from);
        double len = vec.length();
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        float angle = (float) Mth.atan2(vec.y, hlen);

        ret.add((new Box(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
        );

        rotateToVec(ret, from.x, 0, from.z, to.x, 0, to.z);
        translate(ret, (float) from.x, (float) from.y, (float) from.z);
        return ret;
    }

    // Railway catenary
    public static List<Box> renderCatenary(Vec3 from, Vec3 to, TextureAtlasSprite texture) {
        List<Box> ret = new ArrayList<>();

        float size = 0.0625F;
        float heigh = 0.5F;

        Vec3 vec = to.subtract(from);
        double len = vec.length();
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        int steps = Mth.floor(hlen);
        float step = (float) hlen / (float) steps;
        float angle = (float) Mth.atan2(vec.y, hlen);
        if (hlen == 0) angle = (vec.y > 0) ? 0 : (float) Math.PI;

        ret.add((new Box(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
        );
        ret.add((new Box(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
                .translateCoord(heigh - size, 0, 0)
        );

        for (int i = 1; i < steps; i++) {
            ret.add((new Box(heigh - size, size / 2, size / 2, texture))
                    .translateCoord(heigh / 2 - size / 2, step * i, 0)
                    .rotateAroundZ(angle * 180F / (float) Math.PI)
            );
        }

        rotateToVec(ret, from.x, 0, from.z, to.x, 0, to.z);
        translate(ret, (float) from.x, (float) from.y, (float) from.z);
        return ret;
    }

    /** The group-level {@code RawQuadGroup#rotateToVec}: applied to every element, in order. */
    private static void rotateToVec(List<Box> group, double xStart, double yStart, double zStart,
                                    double xEnd, double yEnd, double zEnd) {
        for (Box box : group) {
            box.rotateToVec((float) xStart, (float) yStart, (float) zStart,
                    (float) xEnd, (float) yEnd, (float) zEnd);
        }
    }

    /** The group-level {@code RawQuadGroup#translateCoord}: applied to every element, in order. */
    private static void translate(List<Box> group, float x, float y, float z) {
        for (Box box : group) {
            box.translateCoord(x, y, z);
        }
    }

    /**
     * Draws a group produced by the {@code render*} methods above.
     *
     * <p>The pose stack must already be at the drawing block entity (the dispatcher puts it there) and
     * must have been translated by {@code -blockPos}, because the geometry is in absolute world
     * coordinates -- see the second deliberate difference in the class note. The caller supplies the
     * level for the per-face brightness and the overlay the dispatcher passed in; the lightmap is the
     * 1.12.2 constant {@link #FLAT_LIGHT}.</p>
     */
    public static void draw(List<Box> group, PoseStack poseStack, MultiBufferSource bufferSource,
                            BlockAndTintGetter level, int packedOverlay) {
        if (group.isEmpty()) {
            return;
        }

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.solid());
        PoseStack.Pose pose = poseStack.last();

        for (Box box : group) {
            TextureAtlasSprite sprite = box.sprite;
            float u0 = sprite.getU0();
            float u1 = sprite.getU1();
            float v0 = sprite.getV0();
            float v1 = sprite.getV1();

            for (Direction direction : Direction.values()) {
                int face = direction.ordinal();
                int[] corners = FACE_VERTICES[face];
                int[] uv = FACE_UV[face];
                float shade = level == null ? 1.0F : level.getShade(direction, true);
                Vec3i normal = direction.getNormal();

                for (int i = 0; i < 4; i++) {
                    float[] vertex = box.vertexes[corners[i]];
                    consumer.addVertex(pose, vertex[0], vertex[1], vertex[2])
                            .setColor(shade, shade, shade, 1.0F)
                            .setUv(uv[i * 2] == 0 ? u0 : u1, uv[i * 2 + 1] == 0 ? v0 : v1)
                            .setOverlay(packedOverlay)
                            .setLight(FLAT_LIGHT)
                            .setNormal(pose, normal.getX(), normal.getY(), normal.getZ());
                }
            }
        }
    }
}
