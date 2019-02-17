package club.nsdn.nyasamaelectricity.block;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;

/**
 * Created by drzzm32 on 2019.1.30.
 */
public class BlockSign extends BlockGlass {

    public BlockSign() {
        super(Material.GLASS, false);
        setUnlocalizedName("NSESign");
        setRegistryName(NyaSamaElectricity.MODID, "nse_sign");
        setHardness(2.0F);
        setLightLevel(1);
        setSoundType(SoundType.GLASS);
        setResistance(10.0F);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);
    }

}
