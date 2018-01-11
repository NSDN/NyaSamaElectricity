package club.nsdn.nyasamaelectricity.tileblock.decoration.shelf;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityShelfTriangle extends TileBlock {

    public static class ShelfTriangle extends TileEntity { }

    public TileEntityShelfTriangle() {
        super(Material.iron ,"ShelfTriangle");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("shelf_triangle");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfTriangle();
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }
}
