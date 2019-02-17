package club.nsdn.nyasamaelectricity.creativetab;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.*;
import net.minecraft.creativetab.CreativeTabs;
import club.nsdn.nyasamaelectricity.block.BlockLoader;

/**
 * Created by drzzm32 on 2019.1.30.
 */
public class CreativeTabLoader {

    public static CreativeTabs tabNyaSamaElectricity;

    public CreativeTabLoader(FMLPreInitializationEvent event) {
        tabNyaSamaElectricity = new CreativeTabs("tabNyaSamaElectricity") {
            @Override
            public ItemStack getTabIconItem() {
                return BlockLoader.itemBlocks.get(BlockLoader.logo).getDefaultInstance();
            }
        };
    }

}
