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
 *   <li>{@link #registerClient} -- 1.12.2 registered the tile-entity special renderers here. That is
 *       deferred with the renderers themselves ({@code renderer.tileblock.*}, {@code util.Wire} and
 *       {@code TileEntityModelBinder}, all of which depend on the discarded {@code cn.ac.nya.rawmdl}
 *       pipeline and on the fast-TESR API that 1.21.1 no longer has), so the body is deliberately
 *       empty and the 1.12.2 call site is recorded here rather than invented in advance.</li>
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
     * 1.12.2 registered {@code TileEntityModelBinder.instance()} here. The renderers are not ported yet;
     * see the class note and the module report.
     */
    public static void registerClient(IEventBus modBus) {
    }
}
