package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityInsulatorSquareBig extends TileEntityBase {

    public static class InsulatorSquareBig extends TileEntity { }

    public TileEntityInsulatorSquareBig() {
        super("InsulatorSquareBig");
        //setBlockTextureName("nyasamaelectricity:insulator_square_big");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new InsulatorSquareBig();
    }

}
