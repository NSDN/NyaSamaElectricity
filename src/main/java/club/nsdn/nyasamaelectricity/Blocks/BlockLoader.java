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
    public static Block catenaryShelfHead;
    public static Block catenaryShelfBody;
    public static Block catenaryShelfFoot;
    public static Block catenaryShelfHeadNoInsulator;
    public static Block catenaryShelfHeadTwin;
    public static Block catenaryShelfHeadThin;
    public static Block catenaryShelfHeadNoInsulatorThin;
    public static Block catenaryShelfHeadTwinThin;

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
        catenaryShelfHead = new TileEntityCatenaryShelfHead();
        register(catenaryShelfHead, "catenary_shelf_head_block");
        catenaryShelfBody = new TileEntityCatenaryShelfBody();
        register(catenaryShelfBody, "catenary_shelf_body_block");
        catenaryShelfFoot = new TileEntityCatenaryShelfFoot();
        register(catenaryShelfFoot, "catenary_shelf_foot_block");
        catenaryShelfHeadNoInsulator = new TileEntityCatenaryShelfHeadNoInsulator();
        register(catenaryShelfHeadNoInsulator, "catenary_shelf_head_no_insulator_block");
        catenaryShelfHeadTwin = new TileEntityCatenaryShelfHeadTwin();
        register(catenaryShelfHeadTwin, "catenary_shelf_head_twin_block");
        catenaryShelfHeadThin = new TileEntityCatenaryShelfHeadThin();
        register(catenaryShelfHeadThin, "catenary_shelf_head_thin_block");
        catenaryShelfHeadNoInsulatorThin = new TileEntityCatenaryShelfHeadNoInsulatorThin();
        register(catenaryShelfHeadNoInsulatorThin, "catenary_shelf_head_no_insulator_thin_block");
        catenaryShelfHeadTwinThin = new TileEntityCatenaryShelfHeadTwinThin();
        register(catenaryShelfHeadTwinThin, "catenary_shelf_head_twin_thin_block");
    }

}