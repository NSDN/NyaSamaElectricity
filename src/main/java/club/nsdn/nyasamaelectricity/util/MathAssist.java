package club.nsdn.nyasamaelectricity.util;

import net.minecraft.util.math.MathHelper;

/**
 * Created by drzzm32 on 2019.2.22.
 */
public class MathAssist {

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

}
