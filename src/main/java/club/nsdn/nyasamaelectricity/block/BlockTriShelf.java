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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Triangular pillar shelf, ported from the 1.12.2 {@code BlockTriShelf} (ids {@code quad_tri_conv},
 * {@code tri_head}, {@code tri_shelf}).
 *
 * <p>Two properties, both from 1.12.2: the horizontal facing and a {@code vertical} flag that records
 * whether the shelf was placed on a vertical face (floor/ceiling) rather than a wall. In 1.12.2 they
 * lived in the metadata as {@code horizontalIndex | (vertical ? 0x4 : 0)}; after the flattening they are
 * ordinary state properties, with the same names, so the blockstate JSON keys
 * ({@code facing=...,vertical=...}) are the ones the old file already used.</p>
 *
 * <p>{@code getBoundingBox} returned the full block in 1.12.2 (while {@code isOpaqueCube} and
 * {@code isFullCube} were both {@code false}), so the shape stays a full cube and the block properties
 * carry {@code noOcclusion()}. {@code isSideSolid} returned {@code true}, which now follows from the
 * full collision shape -- no seam is needed here, unlike the small-shaped devices.</p>
 */
public class BlockTriShelf extends Block {

    public static final MapCodec<BlockTriShelf> CODEC = simpleCodec(BlockTriShelf::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty VERTICAL = BooleanProperty.create("vertical");

    public BlockTriShelf(BlockBehaviour.Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.SOUTH)
                .setValue(VERTICAL, true)
        );
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, VERTICAL);
    }

    /** 1.12.2 {@code getBoundingBox} returned {@code Block.FULL_BLOCK_AABB}. */
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    /** 1.12.2 {@code getStateForPlacement}: yaw for the facing, "was the clicked face vertical" for the flag. */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(VERTICAL, context.getClickedFace().getAxis() == Direction.Axis.Y);
    }
}
