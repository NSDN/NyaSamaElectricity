package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.Renderers.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class TileEntityModelBinder {

    public TileEntityModelBinder(FMLInitializationEvent event) {

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityInsulatorSquareSmall.InsulatorSquareSmall.class,
                new BaseRenderer(new InsulatorSquareSmallModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityInsulatorSquareMedium.InsulatorSquareMedium.class,
                new BaseRenderer(new InsulatorSquareMediumModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityInsulatorSquareBig.InsulatorSquareBig.class,
                new BaseRenderer(new InsulatorSquareBigModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfBody.CatenaryShelfBody.class,
                new BaseRenderer(new CatenaryShelfBodyModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfBodyH.CatenaryShelfBodyH.class,
                new BaseRenderer(new CatenaryShelfBodyHModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadH.CatenaryShelfHeadH.class,
                new BaseRenderer(new CatenaryShelfHeadHModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfFoot.CatenaryShelfFoot.class,
                new BaseRenderer(new CatenaryShelfFootModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadNoInsulatorH.CatenaryShelfHeadNoInsulatorH.class,
                new BaseRenderer(new CatenaryShelfHeadNoInsulatorHModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadTwinH.CatenaryShelfHeadTwinH.class,
                new BaseRenderer(new CatenaryShelfHeadTwinHModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadThin.CatenaryShelfHeadThin.class,
                new BaseRenderer(new CatenaryShelfHeadThinModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadNoInsulatorThin.CatenaryShelfHeadNoInsulatorThin.class,
                new BaseRenderer(new CatenaryShelfHeadNoInsulatorThinModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadTwinThin.CatenaryShelfHeadTwinThin.class,
                new BaseRenderer(new CatenaryShelfHeadTwinThinModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfConverter.ShelfConverter.class,
                new BaseRenderer(new ShelfConverterModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuad.ShelfQuad.class,
                new BaseRenderer(new ShelfQuadModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuadHead.ShelfQuadHead.class,
                new BaseRenderer(new ShelfQuadHeadModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfTriangle.ShelfTriangle.class,
                new BaseRenderer(new ShelfTriangleModel()));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfTriangleHead.ShelfTriangleHead.class,
                new BaseRenderer(new ShelfTriangleHeadModel()));
    }

}
