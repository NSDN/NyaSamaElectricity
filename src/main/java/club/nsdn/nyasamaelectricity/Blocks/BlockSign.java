package club.nsdn.nyasamaelectricity.Blocks;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import club.nsdn.nyasamaelectricity.CreativeTab.CreativeTabLoader;

public class BlockSign extends Block {

    public BlockSign() {
        super(Material.glass);
        setBlockName("NyaSamaElectricityBlockSign");
        setBlockTextureName("nyasamaelectricity:logo");
        setHardness(2.0F);
        setLightLevel(1);
        setStepSound(Block.soundTypeGlass);
        setResistance(10.0F);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);
    }

}
