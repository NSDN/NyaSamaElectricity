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
 * Catenary shelf, ported from the 1.12.2 {@code BlockCatenary}. One class backs eight registry entries
 * (long/short shelf, insulator pair, the H-shape pillar and the three old-pillar parts), exactly as in
 * 1.12.2, where {@code BlockLoader} passed a name, an id and the box size to the constructor.
 *
 * <p>Port notes:</p>
 * <ul>
 *   <li>{@code BlockHorizontal.FACING} becomes {@link BlockStateProperties#HORIZONTAL_FACING}; the
 *       state name stays {@code facing}, so the converted blockstate JSON keeps selecting on it.</li>
 *   <li>The size ({@code x}, {@code y}, {@code z}) was constructor data in 1.12.2 and stays
 *       constructor data, but a block subclass with extra constructor arguments cannot use
 *       {@code simpleCodec}; the codec below is a {@link RecordCodecBuilder} carrying
 *       {@code propertiesCodec()} plus the three doubles, which is the shape the API cheatsheet calls
 *       for.</li>
 *   <li>The 1.12.2 static {@code AABBs} cache existed to avoid allocating an {@code AxisAlignedBB} per
 *       query. {@code VoxelShape} is far more expensive to build than an AABB, so the four rotated
 *       shapes are computed once in the constructor instead -- same intent, and {@code getShape} is a
 *       plain array lookup.</li>
 *   <li>{@code getStateFromMeta}/{@code getMetaFromState} disappear with the flattening; placement uses
 *       {@link BlockPlaceContext#getHorizontalDirection()}, the modern equivalent of
 *       {@code player.getHorizontalFacing()}.</li>
 *   <li>{@code isSideSolid} returned {@code facing.getAxis() == Y}; carried over through
 *       {@link SideSolid} (1.21.1 has no such hook, javap-verified).</li>
 *   <li>{@code getBlockLayer()} returned {@code SOLID}; in 1.21.1 the render layer comes from the model
 *       JSON, so nothing is needed here.</li>
 * </ul>
 */
public class BlockCatenary extends Block implements SideSolid {

    public static final MapCodec<BlockCatenary> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockBehaviour.propertiesCodec(),
            Codec.DOUBLE.fieldOf("x").forGetter((BlockCatenary block) -> block.x),
            Codec.DOUBLE.fieldOf("y").forGetter((BlockCatenary block) -> block.y),
            Codec.DOUBLE.fieldOf("z").forGetter((BlockCatenary block) -> block.z)
    ).apply(instance, BlockCatenary::new));

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    /** {@code Direction#ordinal()} indexed, as in 1.12.2's {@code EnumFacing} switch. */
    private final VoxelShape[] shapes = new VoxelShape[6];

    public final double x, y, z;

    /**
     * @param x 1.12.2 {@code x}: width along the face-parallel horizontal axis
     * @param y 1.12.2 {@code y}: height
     * @param z 1.12.2 {@code z}: depth perpendicular to the shelf
     */
    public BlockCatenary(BlockBehaviour.Properties properties, double x, double y, double z) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
        );

        this.x = x;
        this.y = y;
        this.z = z;

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
     * 1.12.2 {@code getBoxByXYZ}, verbatim mathematics, with the result materialised as a
     * {@code VoxelShape}. The unreachable vertical cases keep 1.12.2's {@code FULL_BLOCK_AABB} fallback.
     */
    protected static VoxelShape getBoxByXYZ(Direction facing, double x, double y, double z) {
        double x1 = 0.5 - x / 2, y1 = 0, z1 = 0.5 - z / 2;
        double x2 = 0.5 + x / 2, y2 = y, z2 = 0.5 + z / 2;

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
