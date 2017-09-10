package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityJunkCatenaryShelf extends TileEntityBase {

    public static class JunkCatenaryShelf extends TileEntity {
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

    public TileEntityJunkCatenaryShelf() {
        super(Material.iron ,"JunkCatenaryShelf");
        setStepSound(Block.soundTypeMetal);
        setIconLocation("junk_catenary_shelf");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new JunkCatenaryShelf();
    }

}
