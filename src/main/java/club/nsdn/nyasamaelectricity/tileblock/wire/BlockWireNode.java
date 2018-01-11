package club.nsdn.nyasamaelectricity.tileblock.wire;

import club.nsdn.nyasamatelecom.api.tileentity.TileEntityTransceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by drzzm32 on 2018.1.11.
 */
public class BlockWireNode extends BlockWire {

    public static class WireNode extends TileEntityTransceiver {

        @Override
        @SideOnly(Side.CLIENT)
        public AxisAlignedBB getRenderBoundingBox()
        {
            return AxisAlignedBB
                    .getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1)
                    .expand(4, 4, 4);
        }

        public void fromNBT(NBTTagCompound tagCompound) {
            super.fromNBT(tagCompound);

        }

        public NBTTagCompound toNBT(NBTTagCompound tagCompound) {

            return super.toNBT(tagCompound);
        }

        @Override
        public double getMaxRenderDistanceSquared() {
            return 16384.0D;
        }

    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new WireNode();
    }

    @Override
    public Material getMaterial() {
        return Material.rock;
    }

    public BlockWireNode() {
        super();
        setBlockName("WireNode");
        setIconLocation("block_wire_node");
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        //Switch do not need this!
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        world.scheduleBlockUpdate(x, y, z, this, 1);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            doUpdate(world, x, y, z);
        }
    }

    public void doUpdate(World world, int x , int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof WireNode) {
            WireNode wireNode = (WireNode) world.getTileEntity(x, y, z);
            int old = world.getBlockMetadata(x, y, z);
            int meta = 0;

            // TODO: Add functional code

            if (old != meta) {
                world.setBlockMetadataWithNotify(x, y, z, meta, 3);
                world.notifyBlockChange(x, y, z, this);
                world.markBlockForUpdate(x, y, z);
            }
            world.scheduleBlockUpdate(x, y, z, this, 1);
        }
    }

}
