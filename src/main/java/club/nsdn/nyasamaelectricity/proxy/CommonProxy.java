package club.nsdn.nyasamaelectricity.proxy;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.network.NetworkWrapper;
import cpw.mods.fml.common.event.*;
import club.nsdn.nyasamaelectricity.item.ItemLoader;
import club.nsdn.nyasamaelectricity.block.BlockLoader;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import club.nsdn.nyasamaelectricity.tileblock.TileEntityLoader;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        new NetworkWrapper(event);
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
