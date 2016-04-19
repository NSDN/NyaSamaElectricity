package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityShelfQuad extends TileEntityBase {

    public static class ShelfQuad extends TileEntity { }

    public TileEntityShelfQuad() {
        super(Material.iron ,"ShelfQuad");
        setStepSound(Block.soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfQuad();
    }

}
