package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHeadTwinH extends TileEntityBase {

    public static class CatenaryShelfHeadTwinH extends TileEntity { }

    public TileEntityCatenaryShelfHeadTwinH() {
        super(Material.iron, "CatenaryShelfHeadTwinH");
        setStepSound(Block.soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadTwinH();
    }

}
