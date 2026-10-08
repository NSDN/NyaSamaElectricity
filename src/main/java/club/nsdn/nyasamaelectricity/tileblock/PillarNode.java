package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamatelecom.api.device.SignalBox;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Steel pillar node, ported from the 1.12.2 {@code PillarNode} (registry id {@code pillar_node}).
 *
 * <p>Unlike the other electricity devices this one is a plain full opaque cube: 1.12.2 answered
 * {@code isSideSolid} and {@code isOpaqueCube}/{@code isFullCube} all {@code true} and pinned
 * {@code getBoundingBox} to {@code FULL_BLOCK_AABB}. The port keeps exactly that -- nothing is
 * {@code noOcclusion()} here, and {@link #getShape} returns {@link Shapes#block()}.</p>
 *
 * <p>That last override is not cosmetic: {@code DeviceBase.getShape} derives the shape from the block
 * entity's orientation-aware {@code AABB()} (built by {@code SignalBox}'s
 * {@code setInfo(8, 0.75, 0.5, 0.75)}), which for this block would be a small oriented box instead of
 * the full cube 1.12.2 returned. The 1.12.2 class overrode {@code getBoundingBox} and therefore won
 * over {@code DeviceBase}; overriding {@code getShape} here restores that precedence. The same full
 * shape supplies the {@code isSideSolid == true} answer that 1.12.2 gave by hand, so no
 * {@code SideSolid} seam is needed for this class.</p>
 *
 * <p>Cable/pillar behaviour is unchanged: this is still a {@code SignalBox}. The renderer-only members
 * of the 1.12.2 block entity are not ported -- the four hooks behind them do not exist on 1.21.1's
 * {@code BlockEntity} -- and the pillar link between node centres is drawn by
 * {@code renderer.tileblock.PillarRenderer} with {@code util.Wire}; see {@code WireNode}.</p>
 */
public class PillarNode extends SignalBox {

    public static class TileEntityPillarNode extends SignalBox.TileEntitySignalBox {

        public TileEntityPillarNode(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        /* NOT PORTED: srcQuads / dstQuads (client-only renderer caches), hasFastRenderer(),
         * shouldRenderInPass(int), getRenderBoundingBox(), getMaxRenderDistanceSquared().
         * The geometry and the two render bounds are supplied by
         * renderer.tileblock.PillarRenderer; see the class note. */
    }

    public PillarNode(BlockBehaviour.Properties properties,
                      Supplier<BlockEntityType<?>> blockEntityType) {
        super(properties, blockEntityType);
    }

    @Override
    protected TileEntityBase createBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        return new TileEntityPillarNode(type, pos, state);
    }

    /** 1.12.2 {@code getBoundingBox} returned {@code FULL_BLOCK_AABB}. */
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }
}
