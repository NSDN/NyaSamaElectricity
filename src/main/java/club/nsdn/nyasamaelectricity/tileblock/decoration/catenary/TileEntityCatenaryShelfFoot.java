package club.nsdn.nyasamaelectricity.tileblock.decoration.catenary;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.material.Material;

public class TileEntityCatenaryShelfFoot extends TileBlock {

    public static class CatenaryShelfFoot extends TileEntity {

        @Override
        public double getMaxRenderDistanceSquared() {
            return 16384.0D;
        }

    }

    public TileEntityCatenaryShelfFoot() {
        super(Material.iron, "CatenaryShelfFoot");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_foot");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfFoot();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        switch (meta % 13) {
            case 1:
                setBlockBounds(0.375F, 0.0F, 0.25F, 0.625F, 1.0F, 0.75F);
                break;
            case 2:
                setBlockBounds(0.25F, 0.0F, 0.375F, 0.75F, 1.0F, 0.625F);
                break;
            case 3:
                setBlockBounds(0.375F, 0.0F, 0.25F, 0.625F, 1.0F, 0.75F);
                break;
            case 4:
                setBlockBounds(0.25F, 0.0F, 0.375F, 0.75F, 1.0F, 0.625F);
                break;
            case 5:
                setBlockBounds(0.375F, 0.25F, 0.0F, 0.625F, 0.75F, 1.0F);
                break;
            case 6:
                setBlockBounds(0.0F, 0.25F, 0.375F, 1.0F, 0.75F, 0.625F);
                break;
            case 7:
                setBlockBounds(0.375F, 0.25F, 0.0F, 0.625F, 0.75F, 1.0F);
                break;
            case 8:
                setBlockBounds(0.0F, 0.25F, 0.375F, 1.0F, 0.75F, 0.625F);
                break;
            case 9:
                setBlockBounds(0.375F, 0.0F, 0.25F, 0.625F, 1.0F, 0.75F);
                break;
            case 10:
                setBlockBounds(0.25F, 0.0F, 0.375F, 0.75F, 1.0F, 0.625F);
                break;
            case 11:
                setBlockBounds(0.375F, 0.0F, 0.25F, 0.625F, 1.0F, 0.75F);
                break;
            case 12:
                setBlockBounds(0.25F, 0.0F, 0.375F, 0.75F, 1.0F, 0.625F);
                break;
        }
    }

}
