package club.nsdn.nyasamaelectricity.block;

import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Glass sign panel, ported from the 1.12.2 {@code BlockSign} (registry id {@code nse_sign}).
 *
 * <p>1.12.2 extended {@code BlockGlass} and only ever set hardness/resistance/light level/sound. None of
 * that is behaviour any more: it belongs to the block properties supplied by the registration layer
 * ({@code ElectricityBlocks}), so -- exactly as in the ported {@code nyasamaoptics} -- the class keeps
 * its name and stays empty apart from inheriting {@link TransparentBlock}, which is 1.21.1's plain
 * glass behaviour and already provides the render/occlusion rules and a codec.</p>
 */
public class BlockSign extends TransparentBlock {

    public BlockSign(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
