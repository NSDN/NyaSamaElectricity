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

    @Override
    protected void setBoundsByMeta(int meta) {
        switch (meta % 13) {
            case 1:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 2:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 3:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 4:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 5:
                setBlockBounds(-0.25F, -0.25F, 0.0F, 1.25F, 1.25F, 1.0F);
                break;
            case 6:
                setBlockBounds(-0.25F, -0.25F, 0.0F, 1.25F, 1.25F, 1.0F);
                break;
            case 7:
                setBlockBounds(-0.25F, -0.25F, 0.0F, 1.25F, 1.25F, 1.0F);
                break;
            case 8:
                setBlockBounds(-0.25F, -0.25F, 0.0F, 1.25F, 1.25F, 1.0F);
                break;
            case 9:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 10:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 11:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
            case 12:
                setBlockBounds(-0.25F, 0.0F, -0.25F, 1.25F, 1.0F, 1.25F);
                break;
        }
    }

}
