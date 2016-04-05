package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInsulatorSquareSmall extends TileEntityBase {

    public static class InsulatorSquareSmall extends TileEntity { }

    public TileEntityInsulatorSquareSmall() {
        super("InsulatorSquareSmall");
        //setIconLocation("nyasamaelectricity:insulator_square_small");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new InsulatorSquareSmall();
    }

}
