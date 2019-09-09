package club.nsdn.nyasamaelectricity.util;

import club.nsdn.nyasamaelectricity.util.catenary.RawQuadCube;
import club.nsdn.nyasamaelectricity.util.catenary.RawQuadGroup;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * Created by drzzm32 on 2019.2.18.
 */
public class Wire {

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
            float L1 = -h * L / a + MathHelper.sqrt(h * (a + h) * (L * L - a * a)) / a;
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
            float lower = MathHelper.sqrt(d * d + a * a);
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
            float q = 2 * MathHelper.sqrt(h * (a + h) * (L * L - a * a));
            return ((L * L - a * a) * (a + 2 * h) - L * q)
                    /
                    (a * a) * MathAssist.atanh(a * a / (L * (a + 2 * h) - q));
        }

    }

    protected static RawQuadGroup renderCatenaryCable(Vec3d from, Vec3d to, boolean half, float drop, float thickness, TextureAtlasSprite texture) {
        RawQuadGroup ret = new RawQuadGroup();

        Vec3d vec = to.subtract(from);
        double len = vec.lengthVector();
        float steps = len < 64 ? 128 : MathHelper.floor(len * 2);
        float y0 = 0, y1;

        float d = MathHelper.sqrt((from.x-to.x)*(from.x-to.x) + (from.z-to.z)*(from.z-to.z));
        float step = d/steps;
        Catenary c = new Catenary(0.0, to.y-from.y, d, drop);

        //Origin
        for (int i = 0; i < steps / (half ? 2 : 1); i++) {
            y1 = c.apply((i + 1) * step);

            ret.add((new RawQuadCube(thickness, MathHelper.sqrt(step*step + (y1 - y0)*(y1 - y0)), thickness, texture))
                    .rotateAroundZ(-(float) Math.atan2(y0 - y1, step) * 180F / (float) Math.PI)
                    .translateCoord(-y0, i * step, 0)
            );
            y0 = y1;
        }

        ret.rotateToVec((float) from.x, 0, (float) from.z, (float) to.x, 0, (float) to.z);
        ret.translateCoord((float) from.x, (float) from.y, (float) from.z);
        return ret;
    }

    public static RawQuadGroup renderCable(Vec3d from, Vec3d to, TextureAtlasSprite texture) {
        Vec3d vec = to.subtract(from);
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        double drop = 0.05 * hlen;
        if (Math.abs(vec.y) < 0.0625)
            vec = to.addVector(0, 0.0625, 0);
        else
            vec = to;
        return renderCatenaryCable(from, vec, false, (float) drop, 0.0625F, texture);
    }

    public static RawQuadGroup renderHardCable(Vec3d from, Vec3d to, TextureAtlasSprite texture) {
        RawQuadGroup ret = new RawQuadGroup();

        float size = 0.125F;
        Vec3d vec = to.subtract(from);
        double len = vec.lengthVector();
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        float angle = (float) MathHelper.atan2(vec.y, hlen);

        ret.add((new RawQuadCube(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
        );

        ret.rotateToVec((float) from.x, 0, (float) from.z, (float) to.x, 0, (float) to.z);
        ret.translateCoord((float) from.x, (float) from.y, (float) from.z);
        return ret;
    }
    
    public static RawQuadGroup renderCatenary(Vec3d from, Vec3d to, TextureAtlasSprite texture) {
        RawQuadGroup ret = new RawQuadGroup();

        float size = 0.0625F;
        float heigh = 0.5F;

        Vec3d vec = to.subtract(from);
        double len = vec.lengthVector();
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        int steps = MathHelper.floor(hlen);
        float step = (float) hlen / (float) steps;
        float angle = (float) MathHelper.atan2(vec.y, hlen);

        ret.add((new RawQuadCube(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
        );
        ret.add((new RawQuadCube(size, (float) len, size, texture))
                .rotateAroundZ(angle * 180F / (float) Math.PI)
                .translateCoord(heigh - size, 0, 0)
        );

        for (int i = 1; i < steps; i++) {
            ret.add((new RawQuadCube(heigh - size, size / 2, size / 2, texture))
                    .translateCoord(heigh / 2 - size / 2, step * i, 0)
                    .rotateAroundZ(angle * 180F / (float) Math.PI)
            );
        }

        ret.rotateToVec((float) from.x, 0, (float) from.z, (float) to.x, 0, (float) to.z);
        ret.translateCoord((float) from.x, (float) from.y, (float) from.z);
        return ret;
    }

}
