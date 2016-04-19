package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityShelfTriangle extends TileEntityBase {

    public static class ShelfTriangle extends TileEntity { }

    public TileEntityShelfTriangle() {
        super(Material.iron ,"ShelfTriangle");
        setStepSound(Block.soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfTriangle();
    }

}
