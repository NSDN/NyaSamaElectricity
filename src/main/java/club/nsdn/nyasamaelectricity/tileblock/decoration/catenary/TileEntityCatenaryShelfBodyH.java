package club.nsdn.nyasamaelectricity.tileblock.decoration.catenary;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfBodyH extends TileBlock {

    public static class CatenaryShelfBodyH extends TileEntity {

        @Override
        public double getMaxRenderDistanceSquared() {
            return 16384.0D;
        }

    }

    public TileEntityCatenaryShelfBodyH() {
        super(Material.iron, "CatenaryShelfBodyH");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_body_h");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfBodyH();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        switch (meta % 13) {
            case 1:
                setBlockBounds(0.375F, 0.0F, 0.3125F, 0.625F, 1.0F, 0.6875F);
                break;
            case 2:
                setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 1.0F, 0.625F);
                break;
            case 3:
                setBlockBounds(0.375F, 0.0F, 0.3125F, 0.625F, 1.0F, 0.6875F);
                break;
            case 4:
                setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 1.0F, 0.625F);
                break;
            case 5:
                setBlockBounds(0.375F, 0.3125F, 0.0F, 0.625F, 0.6875F, 1.0F);
                break;
            case 6:
                setBlockBounds(0.0F, 0.3125F, 0.375F, 1.0F, 0.6875F, 0.625F);
                break;
            case 7:
                setBlockBounds(0.375F, 0.3125F, 0.0F, 0.625F, 0.6875F, 1.0F);
                break;
            case 8:
                setBlockBounds(0.0F, 0.3125F, 0.375F, 1.0F, 0.6875F, 0.625F);
                break;
            case 9:
                setBlockBounds(0.375F, 0.0F, 0.3125F, 0.625F, 1.0F, 0.6875F);
                break;
            case 10:
                setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 1.0F, 0.625F);
                break;
            case 11:
                setBlockBounds(0.375F, 0.0F, 0.3125F, 0.625F, 1.0F, 0.6875F);
                break;
            case 12:
                setBlockBounds(0.3125F, 0.0F, 0.375F, 0.6875F, 1.0F, 0.625F);
                break;
        }
    }

}
