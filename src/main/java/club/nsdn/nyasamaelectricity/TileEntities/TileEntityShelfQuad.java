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

public class TileEntityShelfQuad extends TileEntityBase {

    public static class ShelfQuad extends TileEntity { }

    public TileEntityShelfQuad() {
        super(Material.iron ,"ShelfQuad");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("shelf_quad");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfQuad();
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }
}
