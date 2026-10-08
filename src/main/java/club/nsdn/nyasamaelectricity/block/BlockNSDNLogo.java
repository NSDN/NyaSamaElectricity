package club.nsdn.nyasamaelectricity.block;

import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * NSDN logo panel, ported from the 1.12.2 {@code BlockNSDNLogo} (registry id {@code nse_nsdn}).
 *
 * <p>Same story as {@code BlockSign}: 1.12.2's class body was nothing but property setters, which now
 * live in the {@code BlockBehaviour.Properties} built by {@code ElectricityBlocks}.</p>
 */
public class BlockNSDNLogo extends TransparentBlock {

    public BlockNSDNLogo(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
