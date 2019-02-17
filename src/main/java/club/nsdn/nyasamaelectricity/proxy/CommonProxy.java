package club.nsdn.nyasamaelectricity.proxy;

import club.nsdn.nyasamaelectricity.event.EventRegister;
import club.nsdn.nyasamaelectricity.network.NetworkWrapper;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import net.minecraftforge.fml.common.event.*;

/**
 * Created by drzzm32 on 2019.2.17.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new CreativeTabLoader(event);
        new NetworkWrapper(event);
        EventRegister.registerCommon();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
