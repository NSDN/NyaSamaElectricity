package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfArmLong extends TileEntityBase {

    public static class CatenaryShelfArmLong extends TileEntity { }

    public TileEntityCatenaryShelfArmLong() {
        super(Material.iron, "CatenaryShelfArmLong");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_arm_long");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfArmLong();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.25F, 1.0F);
    }

}
