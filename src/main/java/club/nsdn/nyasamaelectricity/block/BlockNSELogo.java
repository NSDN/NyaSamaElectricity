package club.nsdn.nyasamaelectricity.block;

/**
 * Created by drzzm32 on 2016.5.6.
 */

import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNSELogo extends Block {

    public BlockNSELogo() {
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
