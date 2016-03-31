package club.nsdn.nyasamaelectricity;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.Proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

@Mod(modid = NyaSamaElectricity.MODID, version = NyaSamaElectricity.VERSION)
public class NyaSamaElectricity {

    public static final String MODID = "NyaSamaElectricity";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "club.nsdn.nyasamaelectricity.Proxy.ClientProxy",
                serverSide = "club.nsdn.nyasamaelectricity.Proxy.CommonProxy")
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
