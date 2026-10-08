package club.nsdn.nyasamaelectricity.event;

import club.nsdn.nyasamaelectricity.ElectricityBlockEntities;
import club.nsdn.nyasamaelectricity.ElectricityBlocks;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import net.neoforged.bus.api.IEventBus;

/**
 * Registration entry point, ported from the 1.12.2 {@code EventRegister} (26 lines).
 *
 * <p>The 1.12.2 class registered {@code block.BlockLoader} and {@code tileblock.TileEntityLoader} on
 * {@code MinecraftForge.EVENT_BUS} so their {@code @SubscribeEvent} methods would fire on
 * {@code RegistryEvent}; clients additionally registered {@code tileblock.TileEntityModelBinder} for the
 * {@code ModelRegistryEvent} pass. NeoForge replaces the event-driven registration with
 * {@code DeferredRegister}, which is bound to the <b>mod bus</b>, so the three methods keep their 1.12.2
 * names and split but now hand out that bus:</p>
 * <ul>
 *   <li>{@link #registerCommon} -- the block, block-entity and creative-tab deferred registers
 *       (1.12.2: {@code BlockLoader} + {@code TileEntityLoader});</li>
 *   <li>{@link #registerServer} -- empty in 1.12.2 and still empty;</li>
 *   <li>{@link #registerClient} -- 1.12.2 registered {@code tileblock.TileEntityModelBinder} here, which
 *       both ran the model pass and bound the three cable/wire/pillar special renderers. Both halves
 *       moved: item/block models are JSON resources now, and the renderers are bound to block entity
 *       types by {@code renderer.ElectricityRenderers} on
 *       {@code EntityRenderersEvent.RegisterRenderers}. That class carries
 *       {@code @EventBusSubscriber(value = Dist.CLIENT)} and is discovered by the loader, so it must
 *       <b>not</b> be referenced from here -- this method runs in the mod constructor, i.e. on both
 *       sides, and the subscriber class names client-only types. The body therefore stays empty, with
 *       the 1.12.2 call site recorded rather than invented in advance.</li>
 * </ul>
 */
public class EventRegister {

    private EventRegister() {
    }

    public static void registerCommon(IEventBus modBus) {
        ElectricityBlocks.register(modBus);
        ElectricityBlockEntities.register(modBus);
        CreativeTabLoader.register(modBus);
    }

    public static void registerServer(IEventBus modBus) {
    }

    /**
     * 1.12.2 registered {@code TileEntityModelBinder.instance()} here. Its two jobs are now done
     * elsewhere -- JSON models by the resource pack, the three renderers by
     * {@code renderer.ElectricityRenderers} (client-only, annotation-discovered); see the class note.
     */
    public static void registerClient(IEventBus modBus) {
    }
}
