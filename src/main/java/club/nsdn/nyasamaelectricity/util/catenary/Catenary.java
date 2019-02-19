package club.nsdn.nyasamaelectricity.util.catenary;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * Created by drzzm32 on 2019.2.18.
 */
public class Catenary {

    public static float cosAngle (float angle) {
        return MathHelper.cos(angle * 0.01745329252F);
    }

    public static float sinAngle (float angle) {
        return MathHelper.sin(angle * 0.01745329252F);
    }

    public static float asinh(float x) {
        return (float) Math.log(x + MathHelper.sqrt(x * x + 1));
    }

    public static float acosh(float x) {
        return (float) Math.log(x + MathHelper.sqrt(x * x - 1));
    }

    public static float atanh(float x) {
        return (float) (0.5 * Math.log((1 + x) / (1 - x)));
    }

    public static double distanceOf(double xStart, double yStart, double zStart, double xEnd, double yEnd, double zEnd) {
        return Math.sqrt((xStart - xEnd) * (xStart - xEnd) +
                (yStart - yEnd) * (yStart - yEnd) +
                (zStart - zEnd) * (zStart - zEnd));
    }

    public static float distanceOf(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
        return MathHelper.sqrt((xStart - xEnd) * (xStart - xEnd) +
                (yStart - yEnd) * (yStart - yEnd) +
                (zStart - zEnd) * (zStart - zEnd));
    }

    public static class Cls {

        private final float u, x1, k;

        public Cls(double from, double to, double dist, double drop) {
            this((float) from, (float) to, (float) dist, (float) drop);
        }

        public Cls(float yFrom, float yTo, float d, float h) {
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
            float x1 = asinh(u * L1) / u;
            float k = h1 - h - 1 / u;

            if (yFrom > yTo)
                x1 = x1 + d - acosh(u * (yTo - k)) / u - x1;

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
                    (a * a) * atanh(a * a / (L * (a + 2 * h) - q));
        }

    }

    public static  RawQuadGroup renderCatenaryCable(Vec3d from, Vec3d to, TextureAtlasSprite texture) {
        Vec3d vec = to.subtract(from);
        double hlen = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        double drop = (Math.PI - 3) * hlen;
        Vec3d offset = new Vec3d(0, 0.01, 0);
        if (Math.abs(from.y - to.y) < 0.01)
            vec = to.add(offset);
        else vec = to;
        return renderCatenaryCable(from, vec, false, (float) drop, 0.0625F, texture);
    }

    public static RawQuadGroup renderCatenaryCable(Vec3d from, Vec3d to, boolean half, float drop, float thickness, TextureAtlasSprite texture) {
        RawQuadGroup ret = new RawQuadGroup();

        Vec3d vec = to.subtract(from);
        double len = vec.lengthVector();
        float steps = len < 64 ? 128 : MathHelper.floor(len * 2);
        float y0 = 0, y1;

        float d = MathHelper.sqrt((from.x-to.x)*(from.x-to.x) + (from.z-to.z)*(from.z-to.z));
        float step = d/steps;
        Cls c = new Cls(0.0, to.y-from.y, d, drop);

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

}
