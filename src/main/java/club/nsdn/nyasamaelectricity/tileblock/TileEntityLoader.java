package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

import java.util.LinkedList;

/**
 * Created by drzzm32 on 2019.2.17.
 */
public class TileEntityLoader {

    private static TileEntityLoader instance;
    public static TileEntityLoader instance() {
        if (instance == null) instance = new TileEntityLoader();
        return instance;
    }

    public static LinkedList<Class<? extends TileEntity>> tileEntities;

    private static void register(Class<? extends TileEntity> tileEntity) {
        String name = tileEntity.getSimpleName().replace("TileEntity", "tile");
        GameRegistry.registerTileEntity(tileEntity, new ResourceLocation(NyaSamaElectricity.MODID, name));
    }

    @SubscribeEvent
    public void registerTileEntities(RegistryEvent.Register<Block> event) {
        NyaSamaElectricity.logger.info("registering TileEntities");
        for (Class<? extends TileEntity> t : tileEntities)
            register(t);
    }

    public TileEntityLoader() {
        tileEntities = new LinkedList<>();

        tileEntities.add(WireEndpoint.TileEntityWireEndpoint.class);
        tileEntities.add(WireNode.TileEntityWireNode.class);
        tileEntities.add(CableEndpoint.TileEntityCableEndpoint.class);
        tileEntities.add(CableNode.TileEntityCableNode.class);
        tileEntities.add(PillarEndpoint.TileEntityPillarEndpoint.class);
        tileEntities.add(PillarNode.TileEntityPillarNode.class);
    }

}
