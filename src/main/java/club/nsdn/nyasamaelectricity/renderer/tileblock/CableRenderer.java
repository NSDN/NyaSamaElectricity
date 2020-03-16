package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.tileblock.CableNode;
import club.nsdn.nyasamaelectricity.util.Wire;
import cn.ac.nya.rawmdl.RawQuadGroup;
import club.nsdn.nyasamatelecom.api.render.AbsFastTESR;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;
import java.util.LinkedList;

/**
 * Created by drzzm32 on 2019.9.9.
 */
public class CableRenderer extends AbsFastTESR {

    public CableRenderer() {
    }

    public void render(BufferBuilder buffer, double x, double y, double z, LinkedList<BakedQuad> quads) {
        buffer.setTranslation(x, y, z);

        int i = 15728640;
        for (BakedQuad quad: quads) {
            buffer.addVertexData(quad.getVertexData());
            buffer.putBrightness4(i, i, i, i);

            float diffuse = 1;
            if (quad.shouldApplyDiffuseLighting())
                diffuse = net.minecraftforge.client.model.pipeline.LightUtil.diffuseLight(quad.getFace());

            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 4);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 3);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 2);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 1);

            buffer.putPosition(0, 0, 0);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityBase te) {
        return true;
    }

    @Override
    public void renderTileEntityFast(
            @Nonnull TileEntityBase te,
            double x, double y, double z,
            float partialTicks, int destroyStage, float partial,
            @Nonnull BufferBuilder buffer
    ) {
        if (te instanceof CableNode.TileEntityCableNode) {
            CableNode.TileEntityCableNode cableNode = (CableNode.TileEntityCableNode) te;

            Vec3d offset = new Vec3d(0.5, 0.5, 0.5);

            if (cableNode.srcQuads == null)
                cableNode.srcQuads = new LinkedList<>();
            if (cableNode.dstQuads == null)
                cableNode.dstQuads = new LinkedList<>();

            Vec3d theVec = new Vec3d(cableNode.getPos()).add(offset);
            Vec3d srcVec = null, dstVec = null;
            if (cableNode.getSender() != null) {
                BlockPos senderPos = cableNode.getSender().getPos();
                srcVec = new Vec3d(senderPos).add(offset);
            } else if (!cableNode.srcQuads.isEmpty()) cableNode.srcQuads.clear();
            if (cableNode.getTarget() != null) {
                BlockPos targetPos = cableNode.getTarget().getPos();
                dstVec = new Vec3d(targetPos).add(offset);
            } else if (!cableNode.dstQuads.isEmpty()) cableNode.dstQuads.clear();

            TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(
                    "nyasamaelectricity:block/cable"
            );

            x = x - cableNode.getPos().getX();
            y = y - cableNode.getPos().getY();
            z = z - cableNode.getPos().getZ();

            if (srcVec != null) {
                if (cableNode.srcQuads.isEmpty()) {
                    RawQuadGroup group = Wire.renderHardCable(srcVec, theVec, texture);
                    group.bake(cableNode.srcQuads);
                }
                render(buffer, x, y, z, cableNode.srcQuads);
            }

            if (dstVec != null) {
                if (cableNode.dstQuads.isEmpty()) {
                    RawQuadGroup group = Wire.renderHardCable(theVec, dstVec, texture);
                    group.bake(cableNode.dstQuads);
                }
                render(buffer, x, y, z, cableNode.dstQuads);
            }

        }
    }

}
