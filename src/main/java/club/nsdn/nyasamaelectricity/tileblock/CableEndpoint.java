package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import club.nsdn.nyasamatelecom.api.device.SignalBoxSender;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * Created by drzzm32 on 2019.9.9
 */
public class CableEndpoint extends SignalBoxSender {

    public static class TileEntityCableEndpoint extends SignalBoxSender.TileEntitySignalBoxSender { }

    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool EAST = PropertyBool.create("east");

    public CableEndpoint() {
        super(NyaSamaElectricity.MODID, "CableEndpoint", "cable_endpoint");
        setHardness(2.0F);
        setResistance(blockHardness * 5.0F);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);

        setDefaultState(blockState.getBaseState()
                .withProperty(UP, false).withProperty(DOWN, false)
                .withProperty(NORTH, false).withProperty(SOUTH, false)
                .withProperty(WEST, false).withProperty(EAST, false)
        );
    }

    @Override
    public TileEntity createNewTileEntity() {
        return new TileEntityCableEndpoint();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isSideSolid(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing facing) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, UP, DOWN, NORTH, SOUTH, WEST, EAST);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        state = getDefaultState();

        if (world.getBlockState(pos.up()).isSideSolid(world, pos.up(), EnumFacing.DOWN))
           state = state.withProperty(UP, true);
        if (world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP))
            state = state.withProperty(DOWN, true);
        if (world.getBlockState(pos.north()).isSideSolid(world, pos.north(), EnumFacing.SOUTH))
            state = state.withProperty(NORTH, true);
        if (world.getBlockState(pos.south()).isSideSolid(world, pos.south(), EnumFacing.NORTH))
            state = state.withProperty(SOUTH, true);
        if (world.getBlockState(pos.west()).isSideSolid(world, pos.west(), EnumFacing.EAST))
            state = state.withProperty(WEST, true);
        if (world.getBlockState(pos.east()).isSideSolid(world, pos.east(), EnumFacing.WEST))
            state = state.withProperty(EAST, true);

        return state;
    }

    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        AxisAlignedBB box = new AxisAlignedBB(
                0.25, 0.25, 0.25,
                0.75, 0.75, 0.75
        );

        state = getActualState(state, world, pos);

        if (state.getValue(UP))
            box = box.expand(0.0, 0.25, 0.0);
        if (state.getValue(DOWN))
            box = box.expand(0.0, -0.25, 0.0);
        if (state.getValue(NORTH))
            box = box.expand(0.0, 0.0, -0.25);
        if (state.getValue(SOUTH))
            box = box.expand(0.0, 0.0, 0.25);
        if (state.getValue(WEST))
            box = box.expand(-0.25, 0.0, 0.0);
        if (state.getValue(EAST))
            box = box.expand(0.25, 0.0, 0.0);

        return box;
    }

}
