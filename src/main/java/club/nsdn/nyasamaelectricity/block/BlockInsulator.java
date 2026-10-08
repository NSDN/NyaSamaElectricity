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
 * Insulator, ported from the 1.12.2 {@code BlockInsulator} (ids {@code insulator_big},
 * {@code insulator_mid}, {@code insulator_small}).
 *
 * <p>1.12.2 used {@code PropertyDirection.create("facing")} -- all six faces, not the horizontal-only
 * variant -- which is {@link BlockStateProperties#FACING}; the state name is unchanged. The
 * constructor took {@code (d, h)} and stored {@code x = d, y = h, z = d}, i.e. a square cross-section of
 * {@code d} and a height of {@code h} along the block face it is attached to; that asymmetry is why the
 * codec below carries two fields ({@code d} and {@code h}) rather than three, and {@link #z} is derived
 * from {@link #x} exactly as before.</p>
 *
 * <p>Two 1.12.2 answers are carried over unchanged: {@code isSideSolid} compared the attachment axis
 * with the queried face's axis (through {@link SideSolid}, since the hook is gone in 1.21.1), and
 * {@code getBlockLayer()} returned {@code TRANSLUCENT}. The latter now belongs to the model JSON:
 * the block model for this class must carry {@code "render_type": "translucent"} -- noted here because
 * no Java-side hook can express it any more.</p>
 */
public class BlockInsulator extends Block implements SideSolid {

    public static final MapCodec<BlockInsulator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockBehaviour.propertiesCodec(),
            Codec.DOUBLE.fieldOf("d").forGetter((BlockInsulator block) -> block.x),
            Codec.DOUBLE.fieldOf("h").forGetter((BlockInsulator block) -> block.y)
    ).apply(instance, BlockInsulator::new));

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    /** {@code Direction#ordinal()} indexed. */
    private final VoxelShape[] shapes = new VoxelShape[6];

    public final double x, y, z;

    /**
     * @param d 1.12.2 {@code d}: cross-section, used for both {@code x} and {@code z}
     * @param h 1.12.2 {@code h}: height, used for {@code y}
     */
    public BlockInsulator(BlockBehaviour.Properties properties, double d, double h) {
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

    /** 1.12.2 {@code isSideSolid}: solid on the attachment axis only. */
    @Override
    public boolean isSideSolid(BlockState state, BlockGetter level, BlockPos pos, Direction facing) {
        return state.getValue(FACING).getAxis() == facing.getAxis();
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
