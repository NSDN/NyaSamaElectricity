package club.nsdn.nyasamaelectricity.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
 * Quad pillar shelf, ported from the 1.12.2 {@code BlockShelf} (ids {@code quad_head}, {@code quad_shelf},
 * {@code quad_small}).
 *
 * <p>Structurally a sibling of {@code BlockInsulator}: six-way {@code facing}, constructor sizes
 * {@code (d, h)} stored as {@code x = d, y = h, z = d}, and the same box mathematics -- but a different
 * solidity rule and a different material. The 1.12.2 {@code isSideSolid} returned {@code true}
 * unconditionally while the shape is only {@code d x h x d}, so this class is one of the cases where the
 * shape-derived answer of 1.21.1 would change behaviour; the original answer is carried over through
 * {@link SideSolid}.</p>
 *
 * <p>As with {@code BlockInsulator}, the codec has two fields ({@code d} and {@code h}) because that is
 * what the 1.12.2 constructor took; {@link #z} is derived from {@link #x}.</p>
 */
public class BlockShelf extends Block implements SideSolid {

    public static final MapCodec<BlockShelf> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockBehaviour.propertiesCodec(),
            Codec.DOUBLE.fieldOf("d").forGetter((BlockShelf block) -> block.x),
            Codec.DOUBLE.fieldOf("h").forGetter((BlockShelf block) -> block.y)
    ).apply(instance, BlockShelf::new));

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    /** {@code Direction#ordinal()} indexed. */
    private final VoxelShape[] shapes = new VoxelShape[6];

    public final double x, y, z;

    /**
     * @param d 1.12.2 {@code d}: cross-section, used for both {@code x} and {@code z}
     * @param h 1.12.2 {@code h}: height, used for {@code y}
     */
    public BlockShelf(BlockBehaviour.Properties properties, double d, double h) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.UP)
        );

        this.x = d;
        this.y = h;
        this.z = d;

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
     * 1.12.2 {@code getBoxByXYZ}, verbatim mathematics; the unreachable cases keep the original
     * {@code FULL_BLOCK_AABB} fallback.
     */
    protected static VoxelShape getBoxByXYZ(Direction facing, double x, double y, double z) {
        double x1 = 0.5 - x / 2, y1 = 0, z1 = 0.5 - z / 2;
        double x2 = 0.5 + x / 2, y2 = y, z2 = 0.5 + z / 2;

        switch (facing) {
            case NORTH:
                return Shapes.box(1.0 - x2, z1, 1.0 - y2, 1.0 - x1, z2, 1.0 - y1);
            case EAST:
                return Shapes.box(y1, z1, 1.0 - x2, y2, z2, 1.0 - x1);
            case SOUTH:
                return Shapes.box(x1, z1, y1, x2, z2, y2);
            case WEST:
                return Shapes.box(1.0 - y2, z1, x1, 1.0 - y1, z2, x2);
            case UP:
                return Shapes.box(x1, y1, z1, x2, y2, z2);
            case DOWN:
                return Shapes.box(x1, 1.0 - y2, z1, x2, 1.0 - y1, z2);
        }

        return Shapes.block();
    }

    /** 1.12.2 {@code isSideSolid}: every face, despite the partial shape. */
    @Override
    public boolean isSideSolid(BlockState state, BlockGetter level, BlockPos pos, Direction facing) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shapes[state.getValue(FACING).ordinal()];
    }

    /** 1.12.2 {@code getStateForPlacement}: attached to the clicked face. */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(FACING, context.getClickedFace());
    }
}
