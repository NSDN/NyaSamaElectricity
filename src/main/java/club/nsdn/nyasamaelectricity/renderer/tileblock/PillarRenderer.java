package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.tileblock.PillarNode;
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
 * Created by drzzm32 on 2020.3.1
 */
public class PillarRenderer extends AbsFastTESR {

    public PillarRenderer() {
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
        if (te instanceof PillarNode.TileEntityPillarNode) {
            PillarNode.TileEntityPillarNode pillarNode = (PillarNode.TileEntityPillarNode) te;

            Vec3d offset = new Vec3d(0.5, 0.5, 0.5);

            if (pillarNode.srcQuads == null)
                pillarNode.srcQuads = new LinkedList<>();
            if (pillarNode.dstQuads == null)
                pillarNode.dstQuads = new LinkedList<>();

            Vec3d theVec = new Vec3d(pillarNode.getPos()).add(offset);
            Vec3d srcVec = null, dstVec = null;
            if (pillarNode.getSender() != null) {
                BlockPos senderPos = pillarNode.getSender().getPos();
                srcVec = new Vec3d(senderPos).add(offset);
            } else if (!pillarNode.srcQuads.isEmpty()) pillarNode.srcQuads.clear();
            if (pillarNode.getTarget() != null) {
                BlockPos targetPos = pillarNode.getTarget().getPos();
                dstVec = new Vec3d(targetPos).add(offset);
            } else if (!pillarNode.dstQuads.isEmpty()) pillarNode.dstQuads.clear();

            TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(
                    "nyasamaelectricity:block/pillar"
            );

            x = x - pillarNode.getPos().getX();
            y = y - pillarNode.getPos().getY();
            z = z - pillarNode.getPos().getZ();

            if (srcVec != null) {
                if (pillarNode.srcQuads.isEmpty()) {
                    RawQuadGroup group = Wire.renderPillar(srcVec, theVec, texture);
                    group.bake(pillarNode.srcQuads);
                }
                render(buffer, x, y, z, pillarNode.srcQuads);
            }

            if (dstVec != null) {
                if (pillarNode.dstQuads.isEmpty()) {
                    RawQuadGroup group = Wire.renderPillar(theVec, dstVec, texture);
                    group.bake(pillarNode.dstQuads);
                }
                render(buffer, x, y, z, pillarNode.dstQuads);
            }

        }
    }

}
