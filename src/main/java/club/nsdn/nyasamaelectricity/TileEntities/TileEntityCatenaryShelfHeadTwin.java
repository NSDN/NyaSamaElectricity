package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCatenaryShelfHeadTwin extends TileEntityBase {

    public static class CatenaryShelfHeadTwin extends TileEntity { }

    public TileEntityCatenaryShelfHeadTwin() {
        super("CatenaryShelfHeadTwin");
        //setIconLocation("nyasamaelectricity:insulator_square_medium");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfHeadTwin();
    }

}
