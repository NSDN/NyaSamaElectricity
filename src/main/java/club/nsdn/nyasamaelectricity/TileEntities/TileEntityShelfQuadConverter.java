package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityShelfQuadConverter extends TileEntityBase {

    public static class ShelfQuadConverter extends TileEntity { }

    public TileEntityShelfQuadConverter() {
        super(Material.iron, "ShelfQuadConverter");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("shelf_quad_converter");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfQuadConverter();
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }
}
