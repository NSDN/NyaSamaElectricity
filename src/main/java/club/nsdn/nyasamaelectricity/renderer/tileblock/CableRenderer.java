package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.tileblock.CableNode;
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
 * Steel-cable renderer, ported from the 1.12.2 {@code CableRenderer} (87 non-blank lines).
 *
 * <p>1.12.2 was a {@code FastTESR}: {@code renderTileEntityFast} replayed cached
 * {@code BakedQuad}s into the chunk {@code BufferBuilder}. There is no fast path in 1.21.1
 * ({@code docs/09} §4), so this is a normal {@link AbsTileEntitySpecialRenderer} -- the same move the
 * optics renderers made -- and the geometry itself now comes from {@link Wire}, which emits the
 * vertices directly.</p>
 *
 * <p><b>Kept verbatim from 1.12.2:</b> the block entity guard; the {@code (0.5, 0.5, 0.5)} centre
 * offset; the two independent links -- {@code getSender()} drawn as
 * {@code renderHardCable(srcVec, theVec)} and {@code getTarget()} as
 * {@code renderHardCable(theVec, dstVec)} (note the argument order, which the 1.12.2 code also had);
 * the {@code block/cable} texture; and the two render bounds, {@code isGlobalRenderer() == true} and
 * {@code getMaxRenderDistanceSquared() == 65536.0}.</p>
 *
 * <p><b>Deferred, not dropped:</b> the {@code srcQuads}/{@code dstQuads} caches and the
 * {@code srcQuads.isEmpty() / clear()} bookkeeping around them were client-only fields on the block
 * entity, which 1.21.1 block entities cannot carry across sides; {@link Wire} rebuilds the geometry
 * per frame instead and the cache clearing has no counterpart. See {@code Wire} for the two other
 * deliberate differences (lightmap/shading spellings and the removed partial-tick translation).</p>
 */
public class CableRenderer extends AbsTileEntitySpecialRenderer {

    public CableRenderer() {
    }

    public CableRenderer(BlockEntityRendererProvider.Context context) {
        this();
    }

    @Override
    public void renderDevice(TileEntityBase blockEntity, float partialTick, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!(blockEntity instanceof CableNode.TileEntityCableNode cableNode)) {
            return;
        }

        BlockPos pos = cableNode.getBlockPos();
        Level level = cableNode.getLevel();

        Vec3 offset = new Vec3(0.5, 0.5, 0.5);
        Vec3 theVec = Vec3.atLowerCornerOf(pos).add(offset);
        TextureAtlasSprite texture = DeviceTextures.sprite(DeviceTextures.CABLE);

        // Wire's geometry is in absolute world coordinates (as in 1.12.2); the dispatcher already put
        // the pose stack at this block, so only the block offset has to be undone.
        poseStack.pushPose();
        poseStack.translate(-pos.getX(), -pos.getY(), -pos.getZ());

        TileEntityBase sender = cableNode.getSender();
        if (sender != null) {
            Vec3 srcVec = Vec3.atLowerCornerOf(sender.getBlockPos()).add(offset);
            Wire.draw(Wire.renderHardCable(srcVec, theVec, texture), poseStack, bufferSource, level, packedOverlay);
        }

        BlockEntity target = cableNode.getTarget();
        if (target != null) {
            Vec3 dstVec = Vec3.atLowerCornerOf(target.getBlockPos()).add(offset);
            Wire.draw(Wire.renderHardCable(theVec, dstVec, texture), poseStack, bufferSource, level, packedOverlay);
        }

        poseStack.popPose();
    }

    /** 1.12.2 {@code isGlobalRenderer}: the cable reaches its peers, so the block's own box is not enough. */
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
