package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.block.SideSolid;
import club.nsdn.nyasamatelecom.api.device.SignalBox;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Wire node (also used for the catenary node), ported from the 1.12.2 {@code WireNode} (ids
 * {@code wire_node} and {@code catenary_node}).
 *
 * <p>Its 1.12.2 surface was a centred box of the size passed in by {@code BlockLoader}
 * ({@code 0.25 x 0.25 x 0.25} for the wire node, {@code 0.25 x 1 x 0.25} for the catenary node) plus a
 * blocking-free solidity answer. Both are preserved: the {@link AABB} field is still built from the
 * constructor arguments exactly as before and {@link #getShape} returns it, while the
 * {@code isSideSolid == false} answer goes through {@link SideSolid} because 1.21.1 has no such hook.</p>
 *
 * <p>The class extends the ported {@code SignalBox}, i.e. the whole cabled-network behaviour (sender /
 * target link, {@code META} bit 0x8, control functions) comes from the telecom API, as it did in
 * 1.12.2. What is <b>not</b> here is the fast tile-entity renderer plumbing the 1.12.2 block entity
 * carried:</p>
 * <ul>
 *   <li>{@code srcQuads}/{@code dstQuads} (client-only {@code LinkedList<BakedQuad>} caches of the
 *       wire geometry),</li>
 *   <li>{@code hasFastRenderer()}, {@code shouldRenderInPass(int)},
 *       {@code getRenderBoundingBox() == INFINITE_EXTENT_AABB} and
 *       {@code getMaxRenderDistanceSquared() == 65536}.</li>
 * </ul>
 * <p>They are renderer state, not block-entity state, and 1.21.1 removed all four hooks from
 * {@code BlockEntity} (javap-verified); 1.21.1 also has no fast block-entity renderer at all. They are
 * deferred to the renderer task, together with {@code WireRenderer} and {@code util.Wire}, which are
 * the only consumers. See the module report for the planned replacement (a normal
 * {@code BlockEntityRenderer} with {@code shouldRender} / {@code getViewDistance} supplying what the
 * removed hooks used to).</p>
 *
 * <p>{@code shouldSideBeRendered} returned {@code true}; in 1.21.1 that is the {@code noOcclusion()}
 * block property, which the registration layer sets.</p>
 */
public class WireNode extends SignalBox implements SideSolid {

    public static class TileEntityWireNode extends SignalBox.TileEntitySignalBox {

        public TileEntityWireNode(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        /* NOT PORTED: srcQuads / dstQuads (client-only renderer caches), hasFastRenderer(),
         * shouldRenderInPass(int), getRenderBoundingBox(), getMaxRenderDistanceSquared() --
         * renderer state, and the hooks no longer exist on BlockEntity (javap-verified). */
    }

    public final AABB AABB;

    public WireNode(BlockBehaviour.Properties properties,
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
        return new TileEntityWireNode(type, pos, state);
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
}
