package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.block.SideSolid;
import club.nsdn.nyasamatelecom.api.device.SignalBox;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Steel cable node, ported from the 1.12.2 {@code CableNode} (registry id {@code cable_node}).
 *
 * <p>The block side is the same three-way port as {@code BlockBigPillar}:</p>
 * <ul>
 *   <li>the six connection booleans were <b>virtual</b> properties filled by {@code getActualState};
 *       they are stored state now, seeded by {@link #getStateForPlacement} and maintained per direction
 *       by {@link #updateShape}, so the blockstate JSON still selects the side stubs on them;</li>
 *   <li>{@code isSideSolid} (the unconditional {@code true}, which is what lets one cable node connect to
 *       the next despite the small shape) is carried over through {@link SideSolid};</li>
 *   <li>{@code getBoundingBox} -- the {@code 0.25..0.75} core expanded by 0.25 per connected direction --
 *       becomes {@link #getShape}, spelled out as one {@code Shapes.box} since every expansion lands
 *       exactly on 0.0/1.0.</li>
 * </ul>
 *
 * <p>Cable behaviour itself is untouched: this is still a {@code SignalBox} (sender/target link,
 * {@code META} bit 0x8, control functions) exactly as in 1.12.2. The block entity's client-only
 * {@code srcQuads}/{@code dstQuads} quad caches and the removed render hooks
 * ({@code hasFastRenderer}, {@code shouldRenderInPass}, {@code getRenderBoundingBox},
 * {@code getMaxRenderDistanceSquared}) have no 1.21.1 counterpart (javap-verified: the hooks no longer
 * exist on {@code BlockEntity}); the cable geometry itself is drawn by
 * {@code renderer.tileblock.CableRenderer} with {@code util.Wire}, and the two render bounds are
 * supplied there. See {@code WireNode} for the full note.</p>
 */
public class CableNode extends SignalBox implements SideSolid {

    public static class TileEntityCableNode extends SignalBox.TileEntitySignalBox {

        public TileEntityCableNode(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        /* NOT PORTED: srcQuads / dstQuads (client-only renderer caches), hasFastRenderer(),
         * shouldRenderInPass(int), getRenderBoundingBox(), getMaxRenderDistanceSquared().
         * The geometry and the two render bounds are supplied by
         * renderer.tileblock.CableRenderer; see the class note. */
    }

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;

    public CableNode(BlockBehaviour.Properties properties,
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
        return new TileEntityCableNode(type, pos, state);
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
}
