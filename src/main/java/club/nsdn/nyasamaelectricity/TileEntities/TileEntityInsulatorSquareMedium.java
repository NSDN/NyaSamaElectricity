package club.nsdn.nyasamaelectricity.TileEntities;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInsulatorSquareMedium extends TileEntityBase {

    public static class InsulatorSquareMedium extends TileEntity { }

    public TileEntityInsulatorSquareMedium() {
        super("InsulatorSquareMedium");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new InsulatorSquareMedium();
    }


}
