package club.nsdn.nyasamaelectricity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Seam for the 1.12.2 {@code Block#isSideSolid} override.
 *
 * <p>Every connectivity rule in this module -- {@code BlockBigPillar}, {@code BlockCatenary},
 * {@code BlockHVSign}, {@code BlockInsulator}, {@code BlockShelf}, {@code CableNode},
 * {@code CableEndpoint} -- was written against the 1.12.2 hook {@code isSideSolid(IBlockState,
 * IBlockAccess, BlockPos, EnumFacing)}: each of those blocks answered <b>by hand</b> whether one of its
 * faces counts as solid, and their neighbours queried that answer from {@code getActualState}.</p>
 *
 * <p>1.21.1 removed the hook entirely -- verified with javap against
 * {@code neoforge-21.1.256-merged.jar}: {@code BlockBehaviour}, {@code Block} and
 * {@code BlockBehaviour.BlockStateBase} contain no {@code isSideSolid} method; the closest thing is
 * {@code BlockStateBase#isFaceSturdy(BlockGetter, BlockPos, Direction)}, which derives sturdiness
 * from the <b>collision shape</b>. For a full cube the two agree, but this module's devices are
 * deliberately small-shaped while answering {@code true} (a 0.5-unit cable node is still a pole as far
 * as the cable network is concerned), so the shape-derived answer would silently drop every
 * pillar-to-pillar and cable-to-cable connection.</p>
 *
 * <p>This interface therefore carries the 1.12.2 answer forward verbatim: the blocks that overrode the
 * hook implement it with the original body, and {@link #test} -- the module's own replacement for the
 * old {@code world.getBlockState(pos).isSideSolid(...)} call site -- consults it first and falls back
 * to {@code isFaceSturdy} for vanilla and third-party blocks. The seam is deliberately local to this
 * module: it changes nothing about how vanilla decides sturdiness, so it cannot alter placement rules
 * of torches, rails or redstone.</p>
 */
public interface SideSolid {

    /** Verbatim 1.12.2 {@code isSideSolid} body of the implementing block. */
    boolean isSideSolid(BlockState state, BlockGetter level, BlockPos pos, Direction facing);

    /**
     * Module-local replacement for {@code state.isSideSolid(level, pos, facing)}: the 1.12.2 answer for
     * this module's own blocks, the modern shape-derived answer for everything else.
     */
    static boolean test(BlockGetter level, BlockPos pos, Direction facing) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof SideSolid sideSolid) {
            return sideSolid.isSideSolid(state, level, pos, facing);
        }
        return state.isFaceSturdy(level, pos, facing);
    }
}
