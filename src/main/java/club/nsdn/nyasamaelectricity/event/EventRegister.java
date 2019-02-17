package club.nsdn.nyasamaelectricity.event;

import club.nsdn.nyasamaelectricity.block.BlockLoader;
import club.nsdn.nyasamaelectricity.tileblock.TileEntityLoader;
import club.nsdn.nyasamaelectricity.tileblock.TileEntityModelBinder;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by drzzm32 on 2019.1.30.
 */
public class EventRegister {

    public static void registerCommon() {
        MinecraftForge.EVENT_BUS.register(BlockLoader.instance());
        MinecraftForge.EVENT_BUS.register(TileEntityLoader.instance());
    }

    public static void registerServer() {

    }

    public static void registerClient() {
        MinecraftForge.EVENT_BUS.register(TileEntityModelBinder.instance());
    }

}
