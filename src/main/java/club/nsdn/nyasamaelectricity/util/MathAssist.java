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
 * <p><b>Consumer note:</b> this class is the only part of {@code util} that is not tied to the
 * discarded rendering pipeline; {@code Wire} itself (200 lines of {@code cn.ac.nya.rawmdl.RawQuad*}
 * geometry, decision D4) is deferred together with the three block entity renderers. Porting
 * {@code MathAssist} now keeps the pure mathematics in the tree, ready for the renderer task.</p>
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
