package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.block.SideSolid;
import club.nsdn.nyasamatelecom.api.device.SignalBoxSender;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Steel cable endpoint, ported from the 1.12.2 {@code CableEndpoint} (registry id
 * {@code cable_endpoint}).
 *
 * <p>Structurally identical to {@code CableNode} on the block side -- the same six connection booleans
 * (virtual properties in 1.12.2, stored state here), the same {@code isSideSolid == true} answer carried
 * through {@link SideSolid}, the same {@code 0.25..0.75}-expanded shape -- but it is a
 * {@code SignalBoxSender} rather than a {@code SignalBox}, and, as in 1.12.2, the sender's
 * click-to-toggle is deliberately suppressed: {@code onBlockActivated} returned {@code false} there,
 * which is {@link InteractionResult#PASS} in {@link #useWithoutItem}. Cable endpoints are wired, not
 * toggled.</p>
 */
public class CableEndpoint extends SignalBoxSender implements SideSolid {

    public static class TileEntityCableEndpoint extends SignalBoxSender.TileEntitySignalBoxSender {

        public TileEntityCableEndpoint(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }
    }

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;

    public CableEndpoint(BlockBehaviour.Properties properties,
                         Supplier<BlockEntityType<?>> blockEntityType) {
        super(properties, blockEntityType);

        registerDefaultState(stateDefinition.any()
                .setValue(UP, false).setValue(DOWN, false)
                .setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(WEST, false).setValue(EAST, false)
        );
    }

    @Override
    protected TileEntityBase createBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        return new TileEntityCableEndpoint(type, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, SOUTH, WEST, EAST);
    }

    /** 1.12.2 {@code isSideSolid} body, kept verbatim through {@link SideSolid}. */
    @Override
    public boolean isSideSolid(BlockState state, BlockGetter level, BlockPos pos, Direction facing) {
        return true;
    }

    /** The state property that carries the connection in this direction. */
    public static BooleanProperty propertyFor(Direction direction) {
        switch (direction) {
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            case EAST:
            default:
                return EAST;
        }
    }

    /** 1.12.2 {@code getActualState} run once, at placement, over all six directions. */
    protected BlockState withConnections(BlockState state, BlockGetter level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            state = state.setValue(propertyFor(direction),
                    SideSolid.test(level, pos.relative(direction), direction.getOpposite()));
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return withConnections(defaultBlockState(), context.getLevel(), context.getClickedPos());
    }

    /** 1.12.2 {@code getActualState}, per direction, as a stored-state update. */
    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return state.setValue(propertyFor(direction),
                SideSolid.test(level, neighborPos, direction.getOpposite()));
    }

    /** 1.12.2 {@code getBoundingBox}: {@code 0.25..0.75}, expanded by 0.25 per connected direction. */
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        double x1 = 0.25, y1 = 0.25, z1 = 0.25;
        double x2 = 0.75, y2 = 0.75, z2 = 0.75;

        if (state.getValue(UP)) y2 = 1.0;
        if (state.getValue(DOWN)) y1 = 0.0;
        if (state.getValue(NORTH)) z1 = 0.0;
        if (state.getValue(SOUTH)) z2 = 1.0;
        if (state.getValue(WEST)) x1 = 0.0;
        if (state.getValue(EAST)) x2 = 1.0;

        return Shapes.box(x1, y1, z1, x2, y2, z2);
    }

    /** 1.12.2 {@code onBlockActivated} returned {@code false}: nothing is consumed here on purpose. */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                              Player player, BlockHitResult hit) {
        return InteractionResult.PASS;
    }
}
