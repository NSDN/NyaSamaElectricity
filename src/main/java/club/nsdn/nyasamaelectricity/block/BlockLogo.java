package club.nsdn.nyasamaelectricity.block;

import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * NyaSama Electricity logo panel, ported from the 1.12.2 {@code BlockLogo} (registry id
 * {@code nse_logo}). It is also the icon of the module's creative tab
 * ({@code CreativeTabLoader}), as it was in 1.12.2 ({@code BlockLoader.logo}).
 */
public class BlockLogo extends TransparentBlock {

    public BlockLogo(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
