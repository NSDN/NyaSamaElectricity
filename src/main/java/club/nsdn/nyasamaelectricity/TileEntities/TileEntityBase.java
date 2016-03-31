package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import club.nsdn.nyasamaelectricity.CreativeTab.CreativeTabLoader;

public class TileEntityBase extends BlockContainer {

    public TileEntityBase(String blockName) {
        super(Material.glass);
        setBlockName(blockName);
        setBlockTextureName("minecraft:quartz_block_side");
        setHardness(2.0F);
        setLightLevel(0);
        setStepSound(Block.soundTypeGlass);
        setResistance(10.0F);
        setCreativeTab(CreativeTabLoader.tabNyaSamaElectricity);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntity();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /*This will rotate your model corresponding to player direction that was when you placed the block. If you want this to work,
        add these lines to onBlockPlacedBy method in your block class.
        int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, dir, 0);*/
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
       //int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
       //world.setBlockMetadataWithNotify(x, y, z, dir, 0);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

}
