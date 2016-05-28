package club.nsdn.nyasamaelectricity.Items;

/**
 * Created by drzzm32 on 2016.3.25.
 */


import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ItemLoader {

    public static Item itemNyaSamaElectricityLogo;

    private static void register(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }

    public ItemLoader(FMLPreInitializationEvent event) {
        //itemNyaSamaElectricityLogo = new ItemNyaSamaElectricityLogo();
        //register(itemNyaSamaElectricityLogo, "logo_nyasama_electricity");

    }
}
