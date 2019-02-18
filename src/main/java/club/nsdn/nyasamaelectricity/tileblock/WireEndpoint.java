package club.nsdn.nyasamaelectricity.tileblock;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamaelectricity.creativetab.CreativeTabLoader;
import club.nsdn.nyasamatelecom.api.device.SignalBoxSender;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * Created by drzzm32 on 2019.2.18.
 */
public class WireEndpoint extends SignalBoxSender {

    public static class TileEntityWireEndpoint extends SignalBoxSender.TileEntitySignalBoxSender { }

    public static enum EnumShape implements IStringSerializable {
        MAIN("main"),
        WIRE("wire"),
        DROP("drop");

        private final String name;

        EnumShape(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static final PropertyEnum<EnumShape> SHAPE = PropertyEnum.create("shape", EnumShape.class);

    public final AxisAlignedBB AABB;

    public WireEndpoint(String name, String id, double x, double y, double z) {
        super(NyaSamaElectricity.MODID, name, id);
        setHardness(2.0F);
        setResistance(blockHardness * 5.0F);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);

        setDefaultState(blockState.getBaseState()
                        .withProperty(SHAPE, EnumShape.MAIN)
        );

        this.AABB = new AxisAlignedBB(
                0.5 - x / 2, 0.5 - y / 2, 0.5 - z / 2,
                0.5 + x / 2, 0.5 + y / 2, 0.5 + z / 2
        );
    }

    @Override
    public TileEntity createNewTileEntity() {
        return new TileEntityWireEndpoint();
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return AABB;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        switch (meta) {
            default:
            case 0: return getDefaultState().withProperty(SHAPE, EnumShape.MAIN);
            case 1: return getDefaultState().withProperty(SHAPE, EnumShape.WIRE);
            case 2: return getDefaultState().withProperty(SHAPE, EnumShape.DROP);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumShape shape = state.getValue(SHAPE);
        switch (shape) {
            default:
            case MAIN: return 0;
            case WIRE: return 1;
            case DROP: return 2;
        }
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SHAPE);
    }

    @Override
    public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return false;
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }

}