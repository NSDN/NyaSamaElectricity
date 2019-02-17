package club.nsdn.nyasamaelectricity.proxy;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamaelectricity.event.EventRegister;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.*;

/**
 * Created by drzzm32 on 2019.1.30.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        EventRegister.registerClient();
        OBJLoader.INSTANCE.addDomain(NyaSamaElectricity.MODID);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }


}
