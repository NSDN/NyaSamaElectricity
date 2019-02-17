package club.nsdn.nyasamaelectricity.network;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Created by drzzm32 on 2019.1.30.
 */
public class NetworkWrapper {
    public static SimpleNetworkWrapper instance;

    public NetworkWrapper(FMLPreInitializationEvent event) {
        instance = NetworkRegistry.INSTANCE.newSimpleChannel(NyaSamaElectricity.MODID);
        club.nsdn.nyasamatelecom.api.network.NetworkRegister.register(NyaSamaElectricity.logger, instance, 0);
    }
}
