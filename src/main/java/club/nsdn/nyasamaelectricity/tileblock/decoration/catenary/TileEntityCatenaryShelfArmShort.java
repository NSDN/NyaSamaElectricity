package club.nsdn.nyasamaelectricity.tileblock.decoration.catenary;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfArmShort extends TileBlock {

    public static class CatenaryShelfArmShort extends TileEntity {
        @Override
        @SideOnly(Side.CLIENT)
        public AxisAlignedBB getRenderBoundingBox()
        {
            return AxisAlignedBB
                    .getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1)
                    .expand(2, 2, 2);
        }

        @Override
        public double getMaxRenderDistanceSquared() {
            return 16384.0D;
        }

    }

    public TileEntityCatenaryShelfArmShort() {
        super(Material.iron, "CatenaryShelfArmShort");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("catenary_shelf_arm_short");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfArmShort();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.25F, 1.0F);
    }

}
