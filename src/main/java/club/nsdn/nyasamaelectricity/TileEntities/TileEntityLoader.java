package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.client.model.ModelBase;
import club.nsdn.nyasamaelectricity.Renderers.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class TileEntityLoader {

    /*private static void register(ModelBase model, String tileName) {
        ClientRegistry.bindTileEntitySpecialRenderer(
                ExtTileEntity.class,
                new InsulatorRenderer(model));
        GameRegistry.registerTileEntity(
                ExtTileEntity.class,
                tileName);
    }*/

    public TileEntityLoader(FMLInitializationEvent event) {
        //register(new InsulatorSquareSmallModel(), "tileEntityInsulatorSquareSmall");
        //register(new InsulatorSquareMediumModel(), "tileEntityInsulatorSquareMedium");

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityInsulatorSquareSmall.InsulatorSquareSmall.class,
                new InsulatorRenderer(new InsulatorSquareSmallModel()));
        GameRegistry.registerTileEntity(
                TileEntityInsulatorSquareSmall.InsulatorSquareSmall.class,
                "tileEntityInsulatorSquareSmall");

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityInsulatorSquareMedium.InsulatorSquareMedium.class,
                new InsulatorRenderer(new InsulatorSquareMediumModel()));
        GameRegistry.registerTileEntity(
                TileEntityInsulatorSquareMedium.InsulatorSquareMedium.class,
                "tileEntityInsulatorSquareMedium");

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityInsulatorSquareBig.InsulatorSquareBig.class,
                new InsulatorRenderer(new InsulatorSquareBigModel()));
        GameRegistry.registerTileEntity(
                TileEntityInsulatorSquareBig.InsulatorSquareBig.class,
                "tileEntityInsulatorSquareBig");

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfBody.CatenaryShelfBody.class,
                new InsulatorRenderer(new CatenaryShelfBodyModel()));
        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfBody.CatenaryShelfBody.class,
                "tileEntityCatenaryShelfBody");

        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCatenaryShelfHead.CatenaryShelfHead.class,
                new InsulatorRenderer(new CatenaryShelfHeadModel()));
        GameRegistry.registerTileEntity(
                TileEntityCatenaryShelfHead.CatenaryShelfHead.class,
                "tileEntityCatenaryShelfHead");
    }

}
