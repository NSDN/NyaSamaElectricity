package club.nsdn.nyasamaelectricity;

import club.nsdn.nyasamaelectricity.event.EventRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NyaSama Electricity entry point, ported from the 1.12.2 {@code NyaSamaElectricity} (49 lines).
 *
 * <p>The 1.12.2 shape was the classic three-stage one: {@code @Mod.Instance} plus a static
 * {@code instance}, an {@code @SidedProxy} pair of {@code CommonProxy}/{@code ClientProxy}/
 * {@code ServerProxy}, and {@code preInit}/{@code init}/{@code postInit} handlers delegating to it.
 * None of that survives 1.21.1:</p>
 * <ul>
 *   <li>{@code @Mod.Instance} is gone; the constructor receives {@code IEventBus} and the
 *       {@code ModContainer} instead, and there is no static instance any more. In this module
 *       {@code getInstance()} had no caller, so nothing is lost -- if a later module needs the
 *       container, it takes the same constructor parameters.</li>
 *   <li>{@code @SidedProxy} is gone. Its three proxies existed to pick a side at preInit and then
 *       forward to {@code EventRegister}, {@code CreativeTabLoader} and {@code NetworkWrapper}; with
 *       {@code DeferredRegister} the side is decided per registry entry and per event subscriber, so
 *       {@code proxy.*} is deliberately not ported (see the module report).</li>
 *   <li>the lifecycle events collapse into the constructor for registration, which is exactly what
 *       {@link EventRegister#registerCommon} does.</li>
 * </ul>
 *
 * <p>{@code NAME} and {@code VERSION} are kept because they were public fields of the 1.12.2 class;
 * the values that matter to the loader now live in {@code META-INF/neoforge.mods.toml}, and
 * {@code VERSION} is kept in step with it. {@code logger} (assigned from {@code event.getModLog()} in
 * 1.12.2) stays a static {@code LOGGER}.</p>
 */
@Mod(NyaSamaElectricity.MODID)
public class NyaSamaElectricity {

    public static final String MODID = "nyasamaelectricity";
    public static final String NAME = "NyaSama Electricity";
    public static final String VERSION = "3.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger("NyaSamaElectricity");

    public NyaSamaElectricity(IEventBus modBus, ModContainer container) {
        EventRegister.registerCommon(modBus);
        LOGGER.info("[{}] registering blocks / block entities / creative tab", MODID);
    }
}
