package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.block.BlockLoader;
import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import club.nsdn.nyasamaelectricity.util.Wire;
import club.nsdn.nyasamaelectricity.util.catenary.RawQuadGroup;
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
 * Created by drzzm32 on 2019.2.17.
 */
public class WireRenderer extends AbsFastTESR {

    public WireRenderer() {
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
        boolean isCatenary = te.getBlockType() == BlockLoader.catenaryNode;

        if (te instanceof WireNode.TileEntityWireNode) {
            WireNode.TileEntityWireNode wireNode = (WireNode.TileEntityWireNode) te;

            Vec3d offset = new Vec3d(0.5, 0.5, 0.5);
            if (isCatenary)
                offset = offset.addVector(0, 0.5 - 0.03125, 0);

            Vec3d theVec = new Vec3d(wireNode.getPos()).add(offset);
            Vec3d srcVec = null, dstVec = null;
            if (wireNode.getSender() != null) {
                BlockPos senderPos = wireNode.getSender().getPos();
                srcVec = new Vec3d(senderPos).add(offset);
            } else if (!wireNode.srcQuads.isEmpty()) wireNode.srcQuads.clear();
            if (wireNode.getTarget() != null) {
                BlockPos targetPos = wireNode.getTarget().getPos();
                dstVec = new Vec3d(targetPos).add(offset);
            } else if (!wireNode.dstQuads.isEmpty()) wireNode.dstQuads.clear();

            TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(
                    "nyasamaelectricity:block/wire"
            );

            x = x - wireNode.getPos().getX();
            y = y - wireNode.getPos().getY();
            z = z - wireNode.getPos().getZ();

            if (srcVec != null) {
                if (wireNode.srcQuads.isEmpty()) {
                    RawQuadGroup group = isCatenary ? Wire.renderCatenary(srcVec, theVec, texture) : Wire.renderCable(srcVec, theVec, texture);
                    group.bake(wireNode.srcQuads);
                }
                render(buffer, x, y, z, wireNode.srcQuads);
            }

            if (dstVec != null) {
                if (wireNode.dstQuads.isEmpty()) {
                    RawQuadGroup group = isCatenary ? Wire.renderCatenary(theVec, dstVec, texture) : Wire.renderCable(theVec, dstVec, texture);
                    group.bake(wireNode.dstQuads);
                }
                render(buffer, x, y, z, wireNode.dstQuads);
            }

        }
    }

}
