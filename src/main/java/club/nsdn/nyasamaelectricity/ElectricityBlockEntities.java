package club.nsdn.nyasamaelectricity;

import club.nsdn.nyasamaelectricity.tileblock.CableEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.CableNode;
import club.nsdn.nyasamaelectricity.tileblock.PillarEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.PillarNode;
import club.nsdn.nyasamaelectricity.tileblock.WireEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Block entity types for {@code nyasamaelectricity}.
 *
 * <p>1.12.2 needed none of this: {@code tileblock.TileEntityLoader} (49 lines) walked a
 * {@code LinkedList<Class<? extends TileEntity>>} inside a {@code RegistryEvent.Register<Block>} handler
 * and called {@code GameRegistry.registerTileEntity}, deriving the name from the class name
 * ({@code "TileEntityWireNode"} -> {@code "tileWireNode"}). In 1.21.1 a block entity cannot exist without
 * a registered {@code BlockEntityType}, which is also what carries the ticker through
 * {@code DeviceBase#getTicker}, so the types below take over that role.</p>
 *
 * <p>The mapping keeps the 1.12.2 granularity, which was <b>per tile-entity class, not per block</b>:
 * the loader registered six tile entities, and the catenary pair reuses the wire classes. So the
 * {@code wire_endpoint} type lists both {@code wire_endpoint} and {@code catenary_endpoint} as valid
 * blocks, and {@code wire_node} lists {@code wire_node} and {@code catenary_node} -- one type each, as
 * before. The other four blocks have their own class and therefore their own type.</p>
 *
 * <p>Two API details that are easy to get wrong, both verified with javap against
 * {@code neoforge-21.1.256-merged.jar}:</p>
 * <ul>
 *   <li>{@code BlockEntityType.Builder.of(...)} takes a
 *       {@code BlockEntitySupplier<T>} whose signature is {@code T create(BlockPos, BlockState)} -- the
 *       type is <b>not</b> passed in, unlike the {@code BlockEntity(BlockEntityType, BlockPos,
 *       BlockState)} constructor. The suppliers below therefore resolve the type lazily through the
 *       static accessors, which is safe because a supplier only runs when a block entity is actually
 *       created, long after registration.</li>
 *   <li>{@code build(Type<?>)} takes the vanilla datafixer type; the mod has no datafixer entry, hence
 *       {@code build(null)} -- the same call the ported optics module makes.</li>
 * </ul>
 *
 * <p>Valid blocks are read from {@code ElectricityBlocks} at registry time. That is sound because
 * NeoForge fires the {@code BLOCKS} registry event before {@code BLOCK_ENTITY_TYPE}, and
 * {@code EventRegister} registers the block deferred register first as well.</p>
 */
public final class ElectricityBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NyaSamaElectricity.MODID);

    private ElectricityBlockEntities() {
    }

    public static void register(IEventBus modBus) {
        BLOCK_ENTITIES.register(modBus);
    }

    /** Backs both {@code wire_endpoint} and {@code catenary_endpoint} (1.12.2 registered the class once). */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> WIRE_ENDPOINT =
            BLOCK_ENTITIES.register("wire_endpoint", () -> BlockEntityType.Builder
                    .of((pos, state) -> new WireEndpoint.TileEntityWireEndpoint(wireEndpointType(), pos, state),
                            ElectricityBlocks.WIRE_ENDPOINT.get(),
                            ElectricityBlocks.CATENARY_ENDPOINT.get())
                    .build(null));

    /** Backs both {@code wire_node} and {@code catenary_node} (1.12.2 registered the class once). */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> WIRE_NODE =
            BLOCK_ENTITIES.register("wire_node", () -> BlockEntityType.Builder
                    .of((pos, state) -> new WireNode.TileEntityWireNode(wireNodeType(), pos, state),
                            ElectricityBlocks.WIRE_NODE.get(),
                            ElectricityBlocks.CATENARY_NODE.get())
                    .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CABLE_ENDPOINT =
            BLOCK_ENTITIES.register("cable_endpoint", () -> BlockEntityType.Builder
                    .of((pos, state) -> new CableEndpoint.TileEntityCableEndpoint(cableEndpointType(), pos, state),
                            ElectricityBlocks.CABLE_ENDPOINT.get())
                    .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CABLE_NODE =
            BLOCK_ENTITIES.register("cable_node", () -> BlockEntityType.Builder
                    .of((pos, state) -> new CableNode.TileEntityCableNode(cableNodeType(), pos, state),
                            ElectricityBlocks.CABLE_NODE.get())
                    .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> PILLAR_ENDPOINT =
            BLOCK_ENTITIES.register("pillar_endpoint", () -> BlockEntityType.Builder
                    .of((pos, state) -> new PillarEndpoint.TileEntityPillarEndpoint(pillarEndpointType(), pos, state),
                            ElectricityBlocks.PILLAR_ENDPOINT.get())
                    .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> PILLAR_NODE =
            BLOCK_ENTITIES.register("pillar_node", () -> BlockEntityType.Builder
                    .of((pos, state) -> new PillarNode.TileEntityPillarNode(pillarNodeType(), pos, state),
                            ElectricityBlocks.PILLAR_NODE.get())
                    .build(null));

    /** Convenience accessors, used by the blocks' lazy type suppliers (1.12.2 had no equivalent). */
    public static BlockEntityType<?> wireEndpointType() {
        return WIRE_ENDPOINT.get();
    }

    public static BlockEntityType<?> wireNodeType() {
        return WIRE_NODE.get();
    }

    public static BlockEntityType<?> cableEndpointType() {
        return CABLE_ENDPOINT.get();
    }

    public static BlockEntityType<?> cableNodeType() {
        return CABLE_NODE.get();
    }

    public static BlockEntityType<?> pillarEndpointType() {
        return PILLAR_ENDPOINT.get();
    }

    public static BlockEntityType<?> pillarNodeType() {
        return PILLAR_NODE.get();
    }
}
