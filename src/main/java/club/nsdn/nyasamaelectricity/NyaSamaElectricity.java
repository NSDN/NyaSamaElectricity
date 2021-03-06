package club.nsdn.nyasamaelectricity;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import club.nsdn.nyasamaelectricity.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = NyaSamaElectricity.MODID, version = NyaSamaElectricity.VERSION)
public class NyaSamaElectricity {

    public static final String MODID = "NyaSamaElectricity";
    public static final String VERSION = "0.6";
    public static Logger log = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "club.nsdn.nyasamaelectricity.proxy.ClientProxy",
                serverSide = "club.nsdn.nyasamaelectricity.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

}
