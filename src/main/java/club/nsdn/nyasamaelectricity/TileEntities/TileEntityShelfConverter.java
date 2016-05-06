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

public class TileEntityShelfConverter extends TileEntityBase {

    public static class ShelfConverter extends TileEntity { }

    public TileEntityShelfConverter() {
        super(Material.iron ,"ShelfConverter");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("shelf_converter");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfConverter();
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }
}
