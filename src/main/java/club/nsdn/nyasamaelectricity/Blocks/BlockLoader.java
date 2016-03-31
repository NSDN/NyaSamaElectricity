package club.nsdn.nyasamaelectricity.Blocks;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import club.nsdn.nyasamaelectricity.TileEntities.*;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class BlockLoader {

    public static Block blockSign;
    public static Block insulatorSquareSmall;
    public static Block insulatorSquareMedium;
    public static Block insulatorSquareBig;

    private static void register(Block block, String name) {
        GameRegistry.registerBlock(block, name);
    }

    public BlockLoader(FMLPreInitializationEvent event) {
        blockSign = new BlockSign();
        register(blockSign, "block_sign");
        insulatorSquareSmall = new TileEntityInsulatorSquareSmall();
        register(insulatorSquareSmall, "insulator_square_small_block");
        insulatorSquareMedium = new TileEntityInsulatorSquareMedium();
        register(insulatorSquareMedium, "insulator_square_medium_block");
        insulatorSquareBig = new TileEntityInsulatorSquareBig();
        register(insulatorSquareBig, "insulator_square_big_block");
    }

}