package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class TileEntityLoader {

    public TileEntityLoader(FMLInitializationEvent event) {

        GameRegistry.registerTileEntity(
                TileEntityInsulatorSquareSmall.InsulatorSquareSmall.class,
                "tileEntityInsulatorSquareSmall");

        GameRegistry.registerTileEntity(
                TileEntityInsulatorSquareMedium.InsulatorSquareMedium.class,
                "tileEntityInsulatorSquareMedium");

        GameRegistry.registerTileEntity(
                TileEntityInsulatorSquareBig.InsulatorSquareBig.class,
                "tileEntityInsulatorSquareBig");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfBody.CatenaryShelfBody.class,
                "tileEntityCatenaryShelfBody");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfBodyH.CatenaryShelfBodyH.class,
                "tileEntityCatenaryShelfBodyH");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHeadH.CatenaryShelfHeadH.class,
                "tileEntityCatenaryShelfHeadH");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfFoot.CatenaryShelfFoot.class,
                "tileEntityCatenaryShelfFoot");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHeadNoInsulatorH.CatenaryShelfHeadNoInsulatorH.class,
                "tileEntityCatenaryShelfHeadNoInsulatorH");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHeadTwinH.CatenaryShelfHeadTwinH.class,
                "tileEntityCatenaryShelfHeadTwinH");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHeadThin.CatenaryShelfHeadThin.class,
                "tileEntityCatenaryShelfHeadThin");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHeadNoInsulatorThin.CatenaryShelfHeadNoInsulatorThin.class,
                "tileEntityCatenaryShelfHeadNoInsulatorThin");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHeadTwinThin.CatenaryShelfHeadTwinThin.class,
                "tileEntityCatenaryShelfHeadTwinThin");

        GameRegistry.registerTileEntity(
                TileEntityShelfConverter.ShelfConverter.class,
                "tileEntityShelfConverter");

        GameRegistry.registerTileEntity(
                TileEntityShelfQuad.ShelfQuad.class,
                "tileEntityShelfQuad");

        GameRegistry.registerTileEntity(
                TileEntityShelfQuadHead.ShelfQuadHead.class,
                "tileEntityShelfQuadHead");

        GameRegistry.registerTileEntity(
                TileEntityShelfTriangle.ShelfTriangle.class,
                "tileEntityShelfTriangle");

        GameRegistry.registerTileEntity(
                TileEntityShelfTriangleHead.ShelfTriangleHead.class,
                "tileEntityShelfTriangleHead");

        GameRegistry.registerTileEntity(
                TileEntityJunkCatenaryShelf.JunkCatenaryShelf.class,
                "tileEntityJunkCatenaryShelf");

        GameRegistry.registerTileEntity(
                TileEntityShelfQuadThin.ShelfQuadThin.class,
                "tileEntityShelfQuadThin");

        GameRegistry.registerTileEntity(
                TileEntityShelfQuadConverter.ShelfQuadConverter.class,
                "tileEntityShelfQuadConverter");

        GameRegistry.registerTileEntity(
                TileEntityShelfQuadThinConnector.ShelfQuadThinConnector.class,
                "tileEntityShelfQuadThinConnector");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfQuadThinHead.CatenaryShelfQuadThinHead.class,
                "tileEntityCatenaryShelfQuadThinHead");

        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfQuadThinHeadTwin.CatenaryShelfQuadThinHeadTwin.class,
                "tileEntityCatenaryShelfQuadThinHeadTwin");

        GameRegistry.registerTileEntity(
                TileEntityHVDangerSignA.HVDangerSignA.class,
                "tileEntityHVDangerSignA");

        GameRegistry.registerTileEntity(
                TileEntityHVDangerSignB.HVDangerSignB.class,
                "tileEntityHVDangerSignB");

        GameRegistry.registerTileEntity(
                TileEntityParticleSystem.ParticlesSystem.class,
                "tileEntityParticleSystem");
    }

}
