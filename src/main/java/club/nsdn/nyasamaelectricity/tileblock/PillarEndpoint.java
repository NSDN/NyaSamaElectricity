package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamatelecom.api.device.SignalBoxSender;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Steel pillar endpoint, ported from the 1.12.2 {@code PillarEndpoint} (registry id
 * {@code pillar_endpoint}).
 *
 * <p>The full-cube sibling of {@code PillarNode} -- same {@code isSideSolid}/{@code isOpaqueCube}/
 * {@code isFullCube} answers and the same {@code FULL_BLOCK_AABB}, ported as the
 * {@link #getShape} override, see that class for why the override matters -- but it is a
 * {@code SignalBoxSender}, and, as in 1.12.2, its click-to-toggle is suppressed:
 * {@code onBlockActivated} returned {@code false} there, which is {@link InteractionResult#PASS} in
 * {@link #useWithoutItem}.</p>
 */
public class PillarEndpoint extends SignalBoxSender {

    public static class TileEntityPillarEndpoint extends SignalBoxSender.TileEntitySignalBoxSender {

        public TileEntityPillarEndpoint(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }
    }

    public PillarEndpoint(BlockBehaviour.Properties properties,
                          Supplier<BlockEntityType<?>> blockEntityType) {
        super(properties, blockEntityType);
    }

    @Override
    protected TileEntityBase createBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        return new TileEntityPillarEndpoint(type, pos, state);
    }

    /** 1.12.2 {@code getBoundingBox} returned {@code FULL_BLOCK_AABB}. */
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    /** 1.12.2 {@code onBlockActivated} returned {@code false}: nothing is consumed here on purpose. */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                              Player player, BlockHitResult hit) {
        return InteractionResult.PASS;
    }
}
