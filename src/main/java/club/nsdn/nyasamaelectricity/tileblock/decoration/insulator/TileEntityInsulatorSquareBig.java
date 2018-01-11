package club.nsdn.nyasamaelectricity.tileblock.decoration.insulator;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInsulatorSquareBig extends TileBlock {

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
