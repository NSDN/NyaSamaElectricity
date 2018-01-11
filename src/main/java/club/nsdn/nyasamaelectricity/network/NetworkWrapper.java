package club.nsdn.nyasamaelectricity.network;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Created by drzzm32 on 2018.1.11.
 */
public class NetworkWrapper {
    public static SimpleNetworkWrapper instance;

    public NetworkWrapper(FMLPreInitializationEvent event) {
        instance = NetworkRegistry.INSTANCE.newSimpleChannel(NyaSamaElectricity.MODID);
        club.nsdn.nyasamatelecom.api.network.NetworkRegister.register(NyaSamaElectricity.log, instance, 0);
    }
}
