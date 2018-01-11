package club.nsdn.nyasamaelectricity.tileblock.decoration;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHVDangerSignB extends TileBlock {

    public static class HVDangerSignB extends TileEntity { }

    public TileEntityHVDangerSignB() {
        super("HVDangerSignB");
        setIconLocation("hv_danger_sign_b");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new HVDangerSignB();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        float x1 = 0.09375F, y1 = 0.0F, z1 = 0.9375F, x2 = 0.90625F, y2 = 1.0F, z2 = 1.0F;
        switch (meta % 13) {
            case 1:
                setBlockBounds(x1, y1, z1, x2, y2, z2);
                break;
            case 2:
                setBlockBounds(1.0F - z2, y1, x1, 1.0F - z1, y2, x2);
                break;
            case 3:
                setBlockBounds(1.0F - x2, y1, 1.0F - z2, 1.0F - x1, y2, 1.0F - z1);
                break;
            case 4:
                setBlockBounds(z1, y1, 1.0F - x2, z2, y2, 1.0F - x1);
                break;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
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

    }

}
