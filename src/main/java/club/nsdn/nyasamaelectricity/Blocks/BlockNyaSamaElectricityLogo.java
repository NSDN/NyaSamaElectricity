package club.nsdn.nyasamaelectricity.Blocks;

/**
 * Created by drzzm32 on 2016.5.6.
 */

import club.nsdn.nyasamaelectricity.CreativeTab.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNyaSamaElectricityLogo extends Block {

    public BlockNyaSamaElectricityLogo() {
        super(Material.glass);
        setBlockName("NyaSamaElectricityLogo");
        setBlockTextureName("nyasamaelectricity:nsdn_e_logo");
        setHardness(2.0F);
        setLightLevel(1);
        setStepSound(Block.soundTypeGlass);
        setResistance(10.0F);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);
    }

}
