package club.nsdn.nyasamaelectricity.tileblock.decoration.insulator;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import club.nsdn.nyasamaelectricity.tileblock.TileBlock;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInsulatorSquareSmall extends TileBlock {

    public static class InsulatorSquareSmall extends TileEntity { }

    public TileEntityInsulatorSquareSmall() {
        super("InsulatorSquareSmall");
        setIconLocation("insulator_square_small");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new InsulatorSquareSmall();
    }

    @Override
    protected void setBoundsByMeta(int meta) {
        switch (meta % 13) {
            case 1:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 2:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 3:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 4:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 5:
                setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
                break;
            case 6:
                setBlockBounds( 0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
            case 7:
                setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
                break;
            case 8:
                setBlockBounds( 0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
            case 9:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 10:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 11:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 12:
                setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
        }
    }

}
