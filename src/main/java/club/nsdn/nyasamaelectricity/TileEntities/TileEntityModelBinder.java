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
                new BaseRenderer(new CatenaryShelfBodyModel(), "textures/blocks/ConcreteWall.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfBodyH.CatenaryShelfBodyH.class,
                new BaseRenderer(new CatenaryShelfBodyHModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadH.CatenaryShelfHeadH.class,
                new BaseRenderer(new CatenaryShelfHeadHModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfFoot.CatenaryShelfFoot.class,
                new BaseRenderer(new CatenaryShelfFootModel(), "textures/blocks/ConcreteWall.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadNoInsulatorH.CatenaryShelfHeadNoInsulatorH.class,
                new BaseRenderer(new CatenaryShelfHeadNoInsulatorHModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadTwinH.CatenaryShelfHeadTwinH.class,
                new BaseRenderer(new CatenaryShelfHeadTwinHModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadThin.CatenaryShelfHeadThin.class,
                new BaseRenderer(new CatenaryShelfHeadThinModel(), "textures/blocks/ConcreteWall.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadNoInsulatorThin.CatenaryShelfHeadNoInsulatorThin.class,
                new BaseRenderer(new CatenaryShelfHeadNoInsulatorThinModel(), "textures/blocks/ConcreteWall.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHeadTwinThin.CatenaryShelfHeadTwinThin.class,
                new BaseRenderer(new CatenaryShelfHeadTwinThinModel(), "textures/blocks/ConcreteWall.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfConverter.ShelfConverter.class,
                new BaseRenderer(new ShelfConverterModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuad.ShelfQuad.class,
                new BaseRenderer(new ShelfQuadModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuadHead.ShelfQuadHead.class,
                new BaseRenderer(new ShelfQuadHeadModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfTriangle.ShelfTriangle.class,
                new BaseRenderer(new ShelfTriangleModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfTriangleHead.ShelfTriangleHead.class,
                new BaseRenderer(new ShelfTriangleHeadModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityJunkCatenaryShelf.JunkCatenaryShelf.class,
                new BaseRenderer(new JunkCatenaryShelfModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuadThin.ShelfQuadThin.class,
                new BaseRenderer(new ShelfQuadThinModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuadConverter.ShelfQuadConverter.class,
                new BaseRenderer(new ShelfQuadConverterModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityShelfQuadThinConnector.ShelfQuadThinConnector.class,
                new BaseRenderer(new ShelfQuadThinConnectorModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfQuadThinHead.CatenaryShelfQuadThinHead.class,
                new BaseRenderer(new CatenaryShelfQuadThinHeadModel(false), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfQuadThinHeadTwin.CatenaryShelfQuadThinHeadTwin.class,
                new BaseRenderer(new CatenaryShelfQuadThinHeadModel(true), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfArmShort.CatenaryShelfArmShort.class,
                new BaseRenderer(new CatenaryShelfArmShortModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfArmLong.CatenaryShelfArmLong.class,
                new BaseRenderer(new CatenaryShelfArmLongModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHead.CatenaryShelfHead.class,
                new BaseRenderer(new CatenaryShelfHeadModel(), "textures/blocks/ConcreteWall.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfInsulator.CatenaryShelfInsulator.class,
                new BaseRenderer(new CatenaryShelfInsulatorModel(), "textures/blocks/BrushedAluminum.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityHVDangerSignA.HVDangerSignA.class,
                new BaseRenderer(new HVDangerSignModel(), "textures/blocks/hv_danger_a.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityHVDangerSignB.HVDangerSignB.class,
                new BaseRenderer(new HVDangerSignModel(), "textures/blocks/hv_danger_b.png"));

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityParticleSystem.ParticlesSystem.class,
                new BaseRenderer(new ParticleSystemModel(), "textures/blocks/particle.png"));
    }

}
