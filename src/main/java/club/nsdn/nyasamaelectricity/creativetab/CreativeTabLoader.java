package club.nsdn.nyasamaelectricity.creativetab;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.item.Item;
import cpw.mods.fml.common.event.*;
import net.minecraft.creativetab.CreativeTabs;
import club.nsdn.nyasamaelectricity.block.BlockLoader;

public class CreativeTabLoader {

    public static CreativeTabs tabNyaSamaElectricity;

    public CreativeTabLoader(FMLPreInitializationEvent event) {
        tabNyaSamaElectricity = new CreativeTabs("tabNyaSamaElectricity") {
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(BlockLoader.blockNSELogo);
            }
        };
    }

}
