package club.nsdn.nyasamaelectricity.tileblock.wire;

import club.nsdn.nyasamatelecom.api.tileentity.TileEntityTriStateReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * Created by drzzm32 on 2018.1.11.
 */
public class BlockWireSwitch extends BlockWire {

    public static class WireSwitch extends TileEntityTriStateReceiver {

        public ForgeDirection direction;

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
            direction = ForgeDirection.getOrientation(
                    tagCompound.getInteger("direction")
            );
        }

        public NBTTagCompound toNBT(NBTTagCompound tagCompound) {
            if (direction == null) direction = ForgeDirection.UNKNOWN;
            tagCompound.setInteger("direction", direction.ordinal());
            return super.toNBT(tagCompound);
        }

        @Override
        public double getMaxRenderDistanceSquared() {
            return 16384.0D;
        }

    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new WireSwitch();
    }

    @Override
    public Material getMaterial() {
        return Material.rock;
    }

    public BlockWireSwitch() {
        super();
        setBlockName("WireSwitch");
        setIconLocation("block_wire_switch");
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
    protected void func_150052_a(World world, int x, int y, int z, boolean control)
    {
        if (!world.isRemote)
        {
            (new Rail(world, x, y, z)).func_150655_a(world.isBlockIndirectlyGettingPowered(x, y, z), control);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        int meta = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (world.getTileEntity(x, y, z) instanceof WireSwitch) {
            WireSwitch wireSwitch = (WireSwitch) world.getTileEntity(x, y, z);
            switch (meta) {
                case 0:
                    wireSwitch.direction = ForgeDirection.SOUTH;
                    break;
                case 1:
                    wireSwitch.direction = ForgeDirection.WEST;
                    break;
                case 2:
                    wireSwitch.direction = ForgeDirection.NORTH;
                    break;
                case 3:
                    wireSwitch.direction = ForgeDirection.EAST;
                    break;
                default:
                    break;
            }
            if ((meta + 2) % 2 == 0) meta = 0;
            else meta = 1;
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        world.scheduleBlockUpdate(x, y, z, this, 1);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            doSwitch(world, x, y, z);
        }
    }

    public void doSwitch(World world, int x ,int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof WireSwitch) {
            WireSwitch wireSwitch = (WireSwitch) world.getTileEntity(x, y, z);
            int old = world.getBlockMetadata(x, y, z);
            int meta = 0;

            if (wireSwitch.direction == null)
                wireSwitch.direction = ForgeDirection.UNKNOWN;

            switch (wireSwitch.state) {
                case WireSwitch.STATE_POS: //left
                    switch (wireSwitch.direction) {
                        case SOUTH:
                            meta = 9;
                            break;
                        case WEST:
                            meta = 6;
                            break;
                        case NORTH:
                            meta = 7;
                            break;
                        case EAST:
                            meta = 8;
                            break;
                    }
                    break;
                case WireSwitch.STATE_NEG: //right
                    switch (wireSwitch.direction) {
                        case SOUTH:
                            meta = 8;
                            break;
                        case WEST:
                            meta = 9;
                            break;
                        case NORTH:
                            meta = 6;
                            break;
                        case EAST:
                            meta = 7;
                            break;
                    }
                    break;
                case WireSwitch.STATE_ZERO:
                    switch (wireSwitch.direction) {
                        case SOUTH:
                            meta = 0;
                            break;
                        case WEST:
                            meta = 1;
                            break;
                        case NORTH:
                            meta = 0;
                            break;
                        case EAST:
                            meta = 1;
                            break;
                    }
                    break;
                default:
                    break;
            }

            wireSwitch.state = WireSwitch.STATE_ZERO;

            if (old != meta) {
                world.setBlockMetadataWithNotify(x, y, z, meta, 3);
                world.notifyBlockChange(x, y, z, this);
                world.markBlockForUpdate(x, y, z);
            }
            world.scheduleBlockUpdate(x, y, z, this, 1);
        }
    }

    public class Rail extends BlockWire.Rail {

        @Override
        public boolean checkBlockIsMe(World world, int x, int y, int z) {
            return world.getBlock(x, y, z).getClass() == BlockWireSwitch.class;
        }

        @Override
        protected BlockWire.Rail func_150654_a(ChunkPosition chunkPosition)
        {
            return checkBlockIsMe(this.world, chunkPosition.chunkPosX, chunkPosition.chunkPosY, chunkPosition.chunkPosZ) ?
                    new Rail(this.world, chunkPosition.chunkPosX, chunkPosition.chunkPosY, chunkPosition.chunkPosZ)
                    : (
                    checkBlockIsMe(this.world, chunkPosition.chunkPosX, chunkPosition.chunkPosY + 1, chunkPosition.chunkPosZ) ?
                            new Rail(this.world, chunkPosition.chunkPosX, chunkPosition.chunkPosY + 1, chunkPosition.chunkPosZ)
                            : (
                            checkBlockIsMe(this.world, chunkPosition.chunkPosX, chunkPosition.chunkPosY - 1, chunkPosition.chunkPosZ) ?
                                    new Rail(this.world, chunkPosition.chunkPosX, chunkPosition.chunkPosY - 1, chunkPosition.chunkPosZ)
                                    : null)
            );
        }

        public Rail(World world, int x, int y, int z) {
            super(world, x, y, z);
        }

    }
}
