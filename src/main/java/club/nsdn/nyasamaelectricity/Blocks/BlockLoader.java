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
    public static Block catenaryShelfHeadH;
    public static Block catenaryShelfBody;
    public static Block catenaryShelfBodyH;
    public static Block catenaryShelfFoot;
    public static Block catenaryShelfHeadNoInsulatorH;
    public static Block catenaryShelfHeadTwinH;
    public static Block catenaryShelfHeadThin;
    public static Block catenaryShelfHeadNoInsulatorThin;
    public static Block catenaryShelfHeadTwinThin;
    public static Block shelfConverter;
    public static Block shelfQuad;
    public static Block shelfQuadHead;
    public static Block shelfTriangle;
    public static Block shelfTriangleHead;
    public static Block junkCatenaryShelf;

    private static void register(Block block, String name) {
        GameRegistry.registerBlock(block, name);
    }

    public BlockLoader(FMLPreInitializationEvent event) {
        blockSign = new BlockSign();
        register(blockSign, "nyasamaelectricity_block_sign");
        insulatorSquareSmall = new TileEntityInsulatorSquareSmall();
        register(insulatorSquareSmall, "insulator_square_small_block");
        insulatorSquareMedium = new TileEntityInsulatorSquareMedium();
        register(insulatorSquareMedium, "insulator_square_medium_block");
        insulatorSquareBig = new TileEntityInsulatorSquareBig();
        register(insulatorSquareBig, "insulator_square_big_block");
        catenaryShelfHeadH = new TileEntityCatenaryShelfHeadH();
        register(catenaryShelfHeadH, "catenary_shelf_head_h_block");
        catenaryShelfBody = new TileEntityCatenaryShelfBody();
        register(catenaryShelfBody, "catenary_shelf_body_block");
        catenaryShelfBodyH = new TileEntityCatenaryShelfBodyH();
        register(catenaryShelfBodyH, "catenary_shelf_body_h_block");
        catenaryShelfFoot = new TileEntityCatenaryShelfFoot();
        register(catenaryShelfFoot, "catenary_shelf_foot_block");
        catenaryShelfHeadNoInsulatorH = new TileEntityCatenaryShelfHeadNoInsulatorH();
        register(catenaryShelfHeadNoInsulatorH, "catenary_shelf_head_no_insulator_h_block");
        catenaryShelfHeadTwinH = new TileEntityCatenaryShelfHeadTwinH();
        register(catenaryShelfHeadTwinH, "catenary_shelf_head_twin_h_block");
        catenaryShelfHeadThin = new TileEntityCatenaryShelfHeadThin();
        register(catenaryShelfHeadThin, "catenary_shelf_head_thin_block");
        catenaryShelfHeadNoInsulatorThin = new TileEntityCatenaryShelfHeadNoInsulatorThin();
        register(catenaryShelfHeadNoInsulatorThin, "catenary_shelf_head_no_insulator_thin_block");
        catenaryShelfHeadTwinThin = new TileEntityCatenaryShelfHeadTwinThin();
        register(catenaryShelfHeadTwinThin, "catenary_shelf_head_twin_thin_block");

        shelfConverter = new TileEntityShelfConverter();
        register(shelfConverter, "shelf_converter_block");
        shelfQuad = new TileEntityShelfQuad();
        register(shelfQuad, "shelf_quad_block");
        shelfQuadHead = new TileEntityShelfQuadHead();
        register(shelfQuadHead, "shelf_quad_head_block");
        shelfTriangle = new TileEntityShelfTriangle();
        register(shelfTriangle, "shelf_triangle_block");
        shelfTriangleHead = new TileEntityShelfTriangleHead();
        register(shelfTriangleHead, "shelf_triangle_head_block");
        junkCatenaryShelf = new TileEntityJunkCatenaryShelf();
        register(junkCatenaryShelf, "junk_catenary_shelf_block");
    }

}