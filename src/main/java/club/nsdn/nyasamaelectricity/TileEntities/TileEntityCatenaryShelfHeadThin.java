package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHeadThin extends TileEntityBase {

    public static class CatenaryShelfHeadThin extends TileEntity { }

    public TileEntityCatenaryShelfHeadThin() {
        super(Material.iron, "CatenaryShelfHeadThin");
        setStepSound(Block.soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadThin();
    }

}
