package club.nsdn.nyasamaelectricity.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Big pillar, ported from the 1.12.2 {@code BlockBigPillar} (registry id {@code big_pillar}).
 *
 * <p>1.12.2 built the pillar out of six virtual boolean properties ({@code up}, {@code down},
 * {@code north}, {@code south}, {@code west}, {@code east}) that were filled in {@code getActualState}
 * by asking each neighbour for its {@code isSideSolid} answer. Neither half of that mechanism exists in
 * 1.21.1:</p>
 * <ul>
 *   <li>there is no {@code getActualState}; the block state a model is selected for is the <b>stored</b>
 *       state. The six properties are therefore real state now, initialised at placement in
 *       {@link #getStateForPlacement} and maintained per direction in {@link #updateShape} -- the
 *       vanilla {@code FenceBlock} idiom, and the reason the blockstate JSON can still pick the side
 *       stubs by property as it did in 1.12.2;</li>
 *   <li>{@code isSideSolid} is gone (javap-verified). The original {@code return true} is carried over
 *       through {@link SideSolid}, which neighbours consult instead of the removed hook.</li>
 * </ul>
 *
 * <p>The 1.12.2 answer for a neighbour was {@code world.getBlockState(pos.up()).isSideSolid(world,
 * pos.up(), EnumFacing.DOWN)} -- the neighbour's face pointing back at us, i.e.
 * {@code direction.getOpposite()} -- and that is exactly what {@link #updateShape} and
 * {@link #getStateForPlacement} ask for.</p>
 *
 * <p>Shapes: the original {@code getBoundingBox} started from the box {@code 0.25..0.75} on every axis
 * and expanded it by 0.25 per connected direction, which lands exactly on 0.0/1.0, so
 * {@link #getShape} spells the same result out as a single {@code Shapes.box}. {@code isOpaqueCube}
 * and {@code isFullCube} were both {@code false} in 1.12.2, which is {@code noOcclusion()} in the
 * block properties.</p>
 */
public class BlockBigPillar extends Block implements SideSolid {

    public static final MapCodec<BlockBigPillar> CODEC = simpleCodec(BlockBigPillar::new);

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;

    public BlockBigPillar(BlockBehaviour.Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(UP, false).setValue(DOWN, false)
                .setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(WEST, false).setValue(EAST, false)
        );
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, SOUTH, WEST, EAST);
    }

    /** The 1.12.2 {@code isSideSolid} body, kept verbatim through {@link SideSolid}. */
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
