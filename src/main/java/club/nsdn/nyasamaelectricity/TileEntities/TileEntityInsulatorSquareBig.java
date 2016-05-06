package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInsulatorSquareBig extends TileEntityBase {

    public static class InsulatorSquareBig extends TileEntity { }

    public TileEntityInsulatorSquareBig() {
        super("InsulatorSquareBig");
        setIconLocation("insulator_square_big");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new InsulatorSquareBig();
    }

}
