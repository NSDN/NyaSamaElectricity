package club.nsdn.nyasamaelectricity.TileEntities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Created by drzzm on 2017.4.30.
 */
public class TileEntityWire extends TileEntityBase {

    public static class WireNode extends TileEntity {

        public Vec3 nextNode;
        public double size;

        public WireNode() {
            super();
            nextNode = null;
            size = 0;
        }

        public boolean verifyNode(Vec3 pos) {
            if (worldObj.getTileEntity((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord) == null)
                return false;
            return worldObj.getTileEntity((int) pos.xCoord, (int) pos.yCoord, (int) pos.zCoord) instanceof WireNode;
        }

        @Override
        public boolean shouldRenderInPass(int pass) {
            return true;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public AxisAlignedBB getRenderBoundingBox()
        {
            return INFINITE_EXTENT_AABB;
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            if (nextNode != null) {
                if (verifyNode(nextNode)) {
                    tagCompound.setDouble("nextX", nextNode.xCoord);
                    tagCompound.setDouble("nextY", nextNode.yCoord);
                    tagCompound.setDouble("nextZ", nextNode.zCoord);
                }
            } else {
                tagCompound.setDouble("nextX", -1);
                tagCompound.setDouble("nextY", -1);
                tagCompound.setDouble("nextZ", -1);
            }
            tagCompound.setDouble("size", size);
        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            Vec3 vec = Vec3.createVectorHelper(-1,-1,-1);
            vec.xCoord = tagCompound.getDouble("nextX");
            vec.yCoord = tagCompound.getDouble("nextY");
            vec.zCoord = tagCompound.getDouble("nextZ");
            if (verifyNode(vec)) nextNode = vec;
            else nextNode = null;
            size = tagCompound.getDouble("size");
        }

        @Override
        public Packet getDescriptionPacket() {
            NBTTagCompound tagCompound = new NBTTagCompound();

            return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
        }

        @Override
        public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
            NBTTagCompound tagCompound = packet.func_148857_g();

        }

    }

    public TileEntityWire(String name) {
        super(Material.iron, name);
        setStepSound(Block.soundTypeMetal);
        setIconLocation("hv_wire");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new WireNode();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
    }

}
