package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfInsulator extends TileEntityBase {

    public static class CatenaryShelfInsulator extends TileEntity {
    }

    public TileEntityCatenaryShelfInsulator() {
        super(Material.iron, "CatenaryShelfInsulator");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_insulator");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfInsulator();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

}
