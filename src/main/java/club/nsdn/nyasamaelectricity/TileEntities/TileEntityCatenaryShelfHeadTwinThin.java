package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHeadTwinThin extends TileEntityBase {

    public static class CatenaryShelfHeadTwinThin extends TileEntity { }

    public TileEntityCatenaryShelfHeadTwinThin() {
        super("CatenaryShelfHeadTwinThin");
        //setIconLocation("nyasamaelectricity:insulator_square_medium");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadTwinThin();
    }

}
