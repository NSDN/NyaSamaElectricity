package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHead extends TileEntityBase {

    public static class CatenaryShelfHead extends TileEntity {
    }

    public TileEntityCatenaryShelfHead() {
        super(Material.iron, "CatenaryShelfHead");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_head_single");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHead();
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