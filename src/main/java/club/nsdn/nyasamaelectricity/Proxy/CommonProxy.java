package club.nsdn.nyasamaelectricity.Proxy;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import cpw.mods.fml.common.event.*;
import club.nsdn.nyasamaelectricity.Items.ItemLoader;
import club.nsdn.nyasamaelectricity.Blocks.BlockLoader;
import club.nsdn.nyasamaelectricity.CreativeTab.CreativeTabLoader;
import club.nsdn.nyasamaelectricity.TileEntities.TileEntityLoader;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        new CreativeTabLoader(event);
        new ItemLoader(event);
        new BlockLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
        new TileEntityLoader(event);
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
