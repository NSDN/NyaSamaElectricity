package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.block.SideSolid;
import club.nsdn.nyasamatelecom.api.device.SignalBoxSender;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Wire endpoint (also used for the catenary endpoint), ported from the 1.12.2 {@code WireEndpoint}
 * (ids {@code wire_endpoint} and {@code catenary_endpoint}).
 *
 * <p>Same shape and solidity handling as {@code WireNode} -- see that class for the {@link AABB} and
 * {@link SideSolid} notes -- with two differences that come from its {@code SignalBoxSender} parent:</p>
 * <ul>
 *   <li>the block entity is the sender variant (empty subclass in 1.12.2, still empty here);</li>
 *   <li>1.12.2 <b>overrode {@code onBlockActivated} to return {@code false}</b>, deliberately
 *       suppressing the click-to-toggle that {@code SignalBoxSender} installs. That override is ported
 *       as {@link #useWithoutItem} returning {@link InteractionResult#PASS}, which is the 1.21.1
 *       spelling of "not handled, carry on with the default interaction". Dropping it would make wire
 *       endpoints toggleable, which they never were.</li>
 * </ul>
 *
 * <p>The renderer-only members of the 1.12.2 block entity are not ported; see {@code WireNode} and the
 * module report.</p>
 */
public class WireEndpoint extends SignalBoxSender implements SideSolid {

    public static class TileEntityWireEndpoint extends SignalBoxSender.TileEntitySignalBoxSender {

        public TileEntityWireEndpoint(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }
    }

    public final AABB AABB;

    public WireEndpoint(BlockBehaviour.Properties properties,
                        Supplier<BlockEntityType<?>> blockEntityType,
                        double x, double y, double z) {
        super(properties, blockEntityType);

        this.AABB = new AABB(
                0.5 - x / 2, 0.5 - y / 2, 0.5 - z / 2,
                0.5 + x / 2, 0.5 + y / 2, 0.5 + z / 2
        );
    }

    @Override
    protected TileEntityBase createBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        return new TileEntityWireEndpoint(type, pos, state);
    }

    /** 1.12.2 {@code isSideSolid}: never solid, whatever the shape. */
    @Override
    public boolean isSideSolid(BlockState state, BlockGetter level, BlockPos pos, Direction facing) {
        return false;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.create(AABB);
    }

    /** 1.12.2 {@code onBlockActivated} returned {@code false}: nothing is consumed here on purpose. */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                              Player player, BlockHitResult hit) {
        return InteractionResult.PASS;
    }
}
