package club.nsdn.nyasamaelectricity;

import club.nsdn.nyasamaelectricity.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Created by drzzm32 on 2019.2.17.
 */
@Mod(modid = NyaSamaElectricity.MODID, name = NyaSamaElectricity.NAME, version = NyaSamaElectricity.VERSION)
public class NyaSamaElectricity {

    @Mod.Instance
    public static NyaSamaElectricity instance;
    public static final String MODID = "nyasamaelectricity";
    public static final String NAME = "NyaSama Electricity";
    public static final String VERSION = "2.0";
    public static Logger logger;

    @SidedProxy(clientSide = "club.nsdn.nyasamaelectricity.proxy.ClientProxy",
                serverSide = "club.nsdn.nyasamaelectricity.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static NyaSamaElectricity getInstance() {
        return instance;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
