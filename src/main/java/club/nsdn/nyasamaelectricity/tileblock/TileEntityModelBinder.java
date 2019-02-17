package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamatelecom.api.render.AbsTileEntitySpecialRenderer;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedHashMap;

/**
 * Created by drzzm32 on 2019.2.17.
 */
public class TileEntityModelBinder {

    private static TileEntityModelBinder instance;
    public static TileEntityModelBinder instance() {
        if (instance == null) instance = new TileEntityModelBinder();
        return instance;
    }

    public static LinkedHashMap<Class<? extends TileEntityBase>, AbsTileEntitySpecialRenderer> renderers;

    private static void bind(AbsTileEntitySpecialRenderer renderer, Class<? extends TileEntityBase> tileEntity) {
        ClientRegistry.bindTileEntitySpecialRenderer(tileEntity, renderer);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerTileEntitySpecialRenderers(ModelRegistryEvent event) {
        NyaSamaElectricity.logger.info("registering TESRs");
        for (Class<? extends TileEntityBase> tile : renderers.keySet()) {
            bind(renderers.get(tile), tile);
        }
    }

    public TileEntityModelBinder() {
        renderers = new LinkedHashMap<>();


    }

}
