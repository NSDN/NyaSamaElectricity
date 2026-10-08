package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.ElectricityBlocks;
import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import club.nsdn.nyasamaelectricity.util.Wire;
import club.nsdn.nyasamatelecom.api.render.AbsTileEntitySpecialRenderer;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

/**
 * Wire / catenary renderer, ported from the 1.12.2 {@code WireRenderer} (91 non-blank lines).
 *
 * <p>Third of the {@link Wire} consumers, and the one with a branch: the 1.12.2 code asked
 * {@code te.getBlockType() == BlockLoader.catenaryNode} and, for the catenary node,</p>
 * <ul>
 *   <li>raised the centre by {@code 0.5 - 0.03125} (the wire node's shape is {@code 0.25} wide, the
 *       catenary node's is {@code 0.25 x 1 x 0.25}, and the sagging wire hangs just below its top), and</li>
 *   <li>drew {@code Wire.renderCatenary} -- two parallel strands with periodic droppers -- instead of
 *       the plain {@code Wire.renderCable} sag.</li>
 * </ul>
 * <p>Both are kept. {@code BlockLoader.catenaryNode} is {@link ElectricityBlocks#catenaryNode()},
 * which the registration layer already exposed for exactly this comparison, and the block test that
 * used to be {@code getBlockType()} is {@code getBlockState().getBlock()} (the 1.21.1 spelling, as in
 * the optics renderers).</p>
 *
 * <p>Same {@code block/wire} texture, same two links, same render bounds
 * ({@code isGlobalRenderer}, {@code getMaxRenderDistanceSquared() == 65536.0}) and the same two
 * non-ported block entity caches as {@link CableRenderer}; see {@code Wire} for the renderer-state
 * differences.</p>
 */
public class WireRenderer extends AbsTileEntitySpecialRenderer {

    public WireRenderer() {
    }

    public WireRenderer(BlockEntityRendererProvider.Context context) {
        this();
    }

    @Override
    public void renderDevice(TileEntityBase blockEntity, float partialTick, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!(blockEntity instanceof WireNode.TileEntityWireNode wireNode)) {
            return;
        }

        boolean isCatenary = wireNode.getBlockState().getBlock() == ElectricityBlocks.catenaryNode();

        BlockPos pos = wireNode.getBlockPos();
        Level level = wireNode.getLevel();

        Vec3 offset = new Vec3(0.5, 0.5, 0.5);
        if (isCatenary) {
            offset = offset.add(0, 0.5 - 0.03125, 0);
        }

        Vec3 theVec = Vec3.atLowerCornerOf(pos).add(offset);
        TextureAtlasSprite texture = DeviceTextures.sprite(DeviceTextures.WIRE);

        poseStack.pushPose();
        poseStack.translate(-pos.getX(), -pos.getY(), -pos.getZ());

        TileEntityBase sender = wireNode.getSender();
        if (sender != null) {
            Vec3 srcVec = Vec3.atLowerCornerOf(sender.getBlockPos()).add(offset);
            Wire.draw(isCatenary ? Wire.renderCatenary(srcVec, theVec, texture)
                            : Wire.renderCable(srcVec, theVec, texture),
                    poseStack, bufferSource, level, packedOverlay);
        }

        BlockEntity target = wireNode.getTarget();
        if (target != null) {
            Vec3 dstVec = Vec3.atLowerCornerOf(target.getBlockPos()).add(offset);
            Wire.draw(isCatenary ? Wire.renderCatenary(theVec, dstVec, texture)
                            : Wire.renderCable(theVec, dstVec, texture),
                    poseStack, bufferSource, level, packedOverlay);
        }

        poseStack.popPose();
    }

    /** 1.12.2 {@code isGlobalRenderer}. */
    @Override
    public boolean shouldRenderOffScreen(TileEntityBase blockEntity) {
        return true;
    }

    /** 1.12.2 {@code getMaxRenderDistanceSquared() == 65536.0}, i.e. 256 blocks. */
    @Override
    public int getViewDistance() {
        return 256;
    }
}
