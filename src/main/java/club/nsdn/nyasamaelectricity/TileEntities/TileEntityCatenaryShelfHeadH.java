package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCatenaryShelfHeadH extends TileEntityBase {

    public static class CatenaryShelfHeadH extends TileEntity { }

    public TileEntityCatenaryShelfHeadH() {
        super(Material.iron, "CatenaryShelfHeadH");
        setStepSound(Block.soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadH();
    }

}
