package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityParticleSystem extends TileEntityBase {

    public static class ParticlesSystem extends TileEntity { }

    public TileEntityParticleSystem() {
        super(Material.glass ,"ParticleSystem");
        setStepSound(Block.soundTypeGlass);
        setIconLocation("particle_system");
        setLightLevel(1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ParticlesSystem();
    }

}
