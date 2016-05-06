package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHeadThin extends TileEntityBase {

    public static class CatenaryShelfHeadThin extends TileEntity { }

    public TileEntityCatenaryShelfHeadThin() {
        super(Material.iron, "CatenaryShelfHeadThin");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_head");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadThin();
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

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (player.rotationPitch > 22.5F) {
            if (l == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            }

            if (l == 1) {
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
            }

            if (l == 2) {
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
            }

            if (l == 3) {
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
            }
        } else if (player.rotationPitch > -22.5F) {
            if (l == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
            }

            if (l == 1) {
                world.setBlockMetadataWithNotify(x, y, z, 6, 2);
            }

            if (l == 2) {
                world.setBlockMetadataWithNotify(x, y, z, 7, 2);
            }

            if (l == 3) {
                world.setBlockMetadataWithNotify(x, y, z, 8, 2);
            }
        } else {
            if (l == 0) {
                world.setBlockMetadataWithNotify(x, y, z, 9, 2);
            }

            if (l == 1) {
                world.setBlockMetadataWithNotify(x, y, z, 10, 2);
            }

            if (l == 2) {
                world.setBlockMetadataWithNotify(x, y, z, 11, 2);
            }

            if (l == 3) {
                world.setBlockMetadataWithNotify(x, y, z, 12, 2);
            }
        }

    }

}
