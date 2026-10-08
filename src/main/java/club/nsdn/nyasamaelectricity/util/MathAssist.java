package club.nsdn.nyasamaelectricity.util;

import net.minecraft.util.Mth;

/**
 * Trigonometry / distance helpers, ported from the 1.12.2 {@code MathAssist}.
 *
 * <p>The only change is the helper class: {@code net.minecraft.util.math.MathHelper} is
 * {@link Mth} in 1.21.1. The formulas -- including the degree-based {@code cosAngle}/{@code sinAngle}
 * pair and the three inverse hyperbolics -- are kept verbatim, because {@code Wire}'s catenary solver
 * depends on their exact behaviour.</p>
 *
 * <p><b>Consumer note:</b> this class is the pure-mathematics half of {@code util}; the other half is
 * {@link Wire}, which used to depend on {@code cn.ac.nya.rawmdl.RawQuad*} (decision D4) and now emits
 * its vertices directly with the modern pipeline. {@code Wire} is what the catenary solver here is
 * for: {@code Wire.Catenary} calls {@code asinh}/{@code acosh}/{@code atanh} with the 1.12.2 formulas
 * unchanged.</p>
 */
public class MathAssist {

    public static float cosAngle(float angle) {
        return Mth.cos(angle * 0.01745329252F);
    }

    public static float sinAngle(float angle) {
        return Mth.sin(angle * 0.01745329252F);
    }

    public static float asinh(float x) {
        return (float) Math.log(x + Mth.sqrt(x * x + 1));
    }

    public static float acosh(float x) {
        return (float) Math.log(x + Mth.sqrt(x * x - 1));
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
        return Mth.sqrt((xStart - xEnd) * (xStart - xEnd) +
                (yStart - yEnd) * (yStart - yEnd) +
                (zStart - zEnd) * (zStart - zEnd));
    }
}
