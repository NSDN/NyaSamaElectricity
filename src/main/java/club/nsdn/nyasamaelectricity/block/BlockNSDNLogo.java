package club.nsdn.nyasamaelectricity.block;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by drzzm32 on 2019.1.30.
 */
public class BlockNSDNLogo extends BlockGlass {

    public BlockNSDNLogo() {
        super(Material.GLASS, false);
        setUnlocalizedName("NSDNLogo");
        setRegistryName(NyaSamaElectricity.MODID, "nse_nsdn");
        setHardness(2.0F);
        setLightLevel(1);
        setSoundType(SoundType.GLASS);
        setResistance(10.0F);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);
    }

}
