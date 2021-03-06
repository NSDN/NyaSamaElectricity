package club.nsdn.nyasamaelectricity.tileblock.decoration.shelf;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityShelfQuadThin extends TileBlock {

    public static class ShelfQuadThin extends TileEntity { }

    public TileEntityShelfQuadThin() {
        super(Material.iron, "ShelfQuadThin");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("shelf_quad_thin");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ShelfQuadThin();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        switch (meta % 13) {
            case 1:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 2:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 3:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 4:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 5:
                setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
                break;
            case 6:
                setBlockBounds( 0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
            case 7:
                setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
                break;
            case 8:
                setBlockBounds( 0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
            case 9:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 10:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 11:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 12:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
        }
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }

}
