package club.nsdn.nyasamaelectricity.block;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.tileblock.decoration.TileEntityHVDangerSignA;
import club.nsdn.nyasamaelectricity.tileblock.decoration.TileEntityHVDangerSignB;
import club.nsdn.nyasamaelectricity.tileblock.decoration.catenary.*;
import club.nsdn.nyasamaelectricity.tileblock.decoration.insulator.TileEntityInsulatorSquareBig;
import club.nsdn.nyasamaelectricity.tileblock.decoration.insulator.TileEntityInsulatorSquareMedium;
import club.nsdn.nyasamaelectricity.tileblock.decoration.insulator.TileEntityInsulatorSquareSmall;
import club.nsdn.nyasamaelectricity.tileblock.decoration.shelf.*;
import club.nsdn.nyasamaelectricity.tileblock.wire.BlockWire;
import club.nsdn.nyasamaelectricity.tileblock.wire.BlockWireNode;
import club.nsdn.nyasamaelectricity.tileblock.wire.BlockWireSwitch;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class BlockLoader {

    public static Block blockSign;
    public static Block blockNSDNLogo;
    public static Block blockNSELogo;
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
    public static Block catenaryShelfArmShort;
    public static Block catenaryShelfArmLong;
    public static Block catenaryShelfHead;
    public static Block catenaryShelfInsulator;

    public static Block shelfConverter;
    public static Block shelfQuad;
    public static Block shelfQuadHead;
    public static Block shelfTriangle;
    public static Block shelfTriangleHead;
    public static Block junkCatenaryShelf;
    public static Block shelfQuadThin;
    public static Block shelfQuadConverter;
    public static Block shelfQuadThinConnector;
    public static Block catenaryShelfQuadThinHead;
    public static Block catenaryShelfQuadThinHeadTwin;
    public static Block hvDangerSignA;
    public static Block hvDangerSignB;

    public static Block blockWire;
    public static Block blockWireNode;
    public static Block blockWireSwitch;

    private static void register(Block block, String name) {
        GameRegistry.registerBlock(block, name);
    }

    public BlockLoader(FMLPreInitializationEvent event) {
        blockSign = new BlockSign();
        register(blockSign, "nyasamaelectricity_block_sign");
        blockNSDNLogo = new BlockNSDNLogo();
        register(blockNSDNLogo, "nyasamaelectricity_nsdn_logo");
        blockNSELogo = new BlockNSELogo();
        register(blockNSELogo, "nyasamaelectricity_logo");
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
        catenaryShelfQuadThinHead = new TileEntityCatenaryShelfQuadThinHead();
        register(catenaryShelfQuadThinHead, "catenary_shelf_quad_thin_head_block");
        catenaryShelfQuadThinHeadTwin = new TileEntityCatenaryShelfQuadThinHeadTwin();
        register(catenaryShelfQuadThinHeadTwin, "catenary_shelf_quad_thin_head_twin_block");
        catenaryShelfArmShort = new TileEntityCatenaryShelfArmShort();
        register(catenaryShelfArmShort, "catenary_shelf_short");
        catenaryShelfArmLong = new TileEntityCatenaryShelfArmLong();
        register(catenaryShelfArmLong, "catenary_shelf_long");
        catenaryShelfHead = new TileEntityCatenaryShelfHead();
        register(catenaryShelfHead, "catenary_shelf_head_block");
        catenaryShelfInsulator = new TileEntityCatenaryShelfInsulator();
        register(catenaryShelfInsulator, "catenary_shelf_insulator_block");

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
        shelfQuadThin = new TileEntityShelfQuadThin();
        register(shelfQuadThin, "shelf_quad_thin_block");
        shelfQuadConverter = new TileEntityShelfQuadConverter();
        register(shelfQuadConverter, "shelf_quad_converter_block");
        shelfQuadThinConnector = new TileEntityShelfQuadThinConnector();
        register(shelfQuadThinConnector, "shelf_quad_thin_connector_block");
        junkCatenaryShelf = new TileEntityJunkCatenaryShelf();
        register(junkCatenaryShelf, "junk_catenary_shelf_block");

        hvDangerSignA = new TileEntityHVDangerSignA();
        register(hvDangerSignA, "hv_danger_sign_a_block");

        hvDangerSignB = new TileEntityHVDangerSignB();
        register(hvDangerSignB, "hv_danger_sign_b_block");

        blockWire = new BlockWire();
        register(blockWire, "wire_block");
        blockWireNode = new BlockWireNode();
        register(blockWireNode, "wire_node");
        blockWireSwitch = new BlockWireSwitch();
        register(blockWireSwitch, "wire_switch");
    }

}