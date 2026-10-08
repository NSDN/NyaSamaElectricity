package club.nsdn.nyasamaelectricity.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * High-voltage danger sign, ported from the 1.12.2 {@code BlockHVSign} (ids {@code hv_danger_a} and
 * {@code hv_danger_b}).
 *
 * <p>The 1.12.2 class carried two 1.12.2-only mechanisms that had to be re-expressed, and one that had
 * to be preserved verbatim:</p>
 * <ul>
 *   <li>{@code BlockHorizontal.FACING} becomes {@link BlockStateProperties#HORIZONTAL_FACING}
 *       (same state name, so the blockstate JSON is unchanged in that respect);</li>
 *   <li>{@code isSideSolid} -- the vertical-face-only answer -- is carried over through
 *       {@link SideSolid}, because 1.21.1 has no such hook (javap-verified);</li>
 *   <li>{@code getBoundingBox} keeps the 1.12.2 quirk that the sign is glued to the opposite face:
 *       the original read {@code z1 = 1 - z, z2 = 1} (marked "Z has modified" in the source), which puts
 *       a 0.0625-thick plate on the far side of the block. That is reproduced literally -- it is a
 *       placement convention of the mod, not an accident to be tidied up.</li>
 * </ul>
 *
 * <p>The plate sizes are constants of the class in 1.12.2 as well ({@code x = 0.8125, y = 1,
 * z = 0.0625}, assigned in the constructor), so no extra codec data is needed and
 * {@code simpleCodec} suffices. As with {@code BlockCatenary}, the four rotated shapes are built once
 * in the constructor.</p>
 */
public class BlockHVSign extends Block implements SideSolid {

    public static final MapCodec<BlockHVSign> CODEC = simpleCodec(BlockHVSign::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    /** {@code Direction#ordinal()} indexed. */
    private final VoxelShape[] shapes = new VoxelShape[6];

    public final double x, y, z;

    public BlockHVSign(BlockBehaviour.Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
        );

        this.x = 0.8125;
        this.y = 1;
        this.z = 0.0625;

        for (Direction facing : Direction.values()) {
            shapes[facing.ordinal()] = getBoxByXYZ(facing, x, y, z);
        }
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /**
     * 1.12.2 {@code getBoxByXYZ}, verbatim including the modified Z bounds; the unreachable vertical
     * cases keep 1.12.2's {@code FULL_BLOCK_AABB} fallback.
     */
    protected static VoxelShape getBoxByXYZ(Direction facing, double x, double y, double z) {
        double x1 = 0.5 - x / 2, y1 = 0, z1 = 1 - z; // Z has modified
        double x2 = 0.5 + x / 2, y2 = y, z2 = 1; // Z has modified

        switch (facing) {
            case NORTH:
                return Shapes.box(x1, y1, z1, x2, y2, z2);
            case EAST:
                return Shapes.box(1.0 - z2, y1, x1, 1.0 - z1, y2, x2);
            case SOUTH:
                return Shapes.box(1.0 - x2, y1, 1.0 - z2, 1.0 - x1, y2, 1.0 - z1);
            case WEST:
                return Shapes.box(z1, y1, 1.0 - x2, z2, y2, 1.0 - x1);
        }

        return Shapes.block();
    }

    /** 1.12.2 {@code isSideSolid}: only the vertical faces count as solid. */
    @Override
    public boolean isSideSolid(BlockState state, BlockGetter level, BlockPos pos, Direction facing) {
        return facing.getAxis() == Direction.Axis.Y;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes[state.getValue(FACING).ordinal()];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
