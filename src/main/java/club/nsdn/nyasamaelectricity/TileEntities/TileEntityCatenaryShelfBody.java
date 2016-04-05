package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCatenaryShelfBody extends TileEntityBase {

    public static class CatenaryShelfBody extends TileEntity { }

    public TileEntityCatenaryShelfBody() {
        super("CatenaryShelfBody");
        //setIconLocation("nyasamaelectricity:insulator_square_medium");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CatenaryShelfBody();
    }

}
