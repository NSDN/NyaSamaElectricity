package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHeadNoInsulator extends TileEntityBase {

    public static class CatenaryShelfHeadNoInsulator extends TileEntity { }

    public TileEntityCatenaryShelfHeadNoInsulator() {
        super("CatenaryShelfHeadNoInsulator");
        //setIconLocation("nyasamaelectricity:insulator_square_medium");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadNoInsulator();
    }

}
