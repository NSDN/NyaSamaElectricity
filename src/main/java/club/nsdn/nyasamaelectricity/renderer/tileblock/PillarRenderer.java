package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.tileblock.PillarNode;
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
 * Steel-pillar renderer, ported from the 1.12.2 {@code PillarRenderer} (87 non-blank lines).
 *
 * <p>Structural twin of {@link CableRenderer} -- same {@link Wire} geometry emitter, same two links,
 * same render bounds -- with the pillar's own differences reproduced: the
 * {@link PillarNode.TileEntityPillarNode} guard, the {@code block/pillar} texture and
 * {@code Wire#renderPillar}, whose box is {@code 0.75} across instead of the cable's {@code 0.125}.
 * The pillar node is a full opaque cube, so what this renderer adds to the block is the link between
 * pillar centres, exactly as in 1.12.2.</p>
 *
 * <p>1.12.2's {@code srcQuads}/{@code dstQuads} caches are not ported here either; see
 * {@link CableRenderer} and {@code Wire}.</p>
 */
public class PillarRenderer extends AbsTileEntitySpecialRenderer {

    public PillarRenderer() {
    }

    public PillarRenderer(BlockEntityRendererProvider.Context context) {
        this();
    }

    @Override
    public void renderDevice(TileEntityBase blockEntity, float partialTick, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!(blockEntity instanceof PillarNode.TileEntityPillarNode pillarNode)) {
            return;
        }

        BlockPos pos = pillarNode.getBlockPos();
        Level level = pillarNode.getLevel();

        Vec3 offset = new Vec3(0.5, 0.5, 0.5);
        Vec3 theVec = Vec3.atLowerCornerOf(pos).add(offset);
        TextureAtlasSprite texture = DeviceTextures.sprite(DeviceTextures.PILLAR);

        poseStack.pushPose();
        poseStack.translate(-pos.getX(), -pos.getY(), -pos.getZ());

        TileEntityBase sender = pillarNode.getSender();
        if (sender != null) {
            Vec3 srcVec = Vec3.atLowerCornerOf(sender.getBlockPos()).add(offset);
            Wire.draw(Wire.renderPillar(srcVec, theVec, texture), poseStack, bufferSource, level, packedOverlay);
        }

        BlockEntity target = pillarNode.getTarget();
        if (target != null) {
            Vec3 dstVec = Vec3.atLowerCornerOf(target.getBlockPos()).add(offset);
            Wire.draw(Wire.renderPillar(theVec, dstVec, texture), poseStack, bufferSource, level, packedOverlay);
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
