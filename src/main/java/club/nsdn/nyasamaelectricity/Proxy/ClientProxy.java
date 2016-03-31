package club.nsdn.nyasamaelectricity.Proxy;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import cpw.mods.fml.common.event.*;
import club.nsdn.nyasamaelectricity.TileEntities.TileEntityLoader;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new TileEntityLoader(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }


}
