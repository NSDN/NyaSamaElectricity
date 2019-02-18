package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.block.BlockLoader;
import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import club.nsdn.nyasamaelectricity.util.Catenary;
import club.nsdn.nyasamatelecom.api.render.AbsFastTESR;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;

import javax.annotation.Nonnull;

/**
 * Created by drzzm32 on 2019.2.17.
 */
public class WireRenderer extends AbsFastTESR {

    public WireRenderer() {

    }

    public double getStep(Vec3d vec, Block block) {
        if (block == BlockLoader.catenaryNode) {
            return 1.0 / MathHelper.floor(vec.lengthVector() / 0.0625);
        } else if (block == BlockLoader.wireNode) {
            return 0.5 / MathHelper.floor(vec.lengthVector() / 0.0625);
        }
        return 1.0 / MathHelper.floor(vec.lengthVector() / 0.0625);
    }

    public void calcWire(Vec3d vec, double progress, double x, double y, double z, Block block, BlockPos pos, BufferBuilder buffer) {
        if (progress > 1) progress = 1;
        if (progress < 0) progress = 0;

        if (block == BlockLoader.catenaryNode) {
            buffer.setTranslation(
                    x - (double) pos.getX() + vec.x * progress,
                    y - (double) pos.getY() + vec.y * progress,
                    z - (double) pos.getZ() + vec.z * progress
            );
        } else if (block == BlockLoader.wireNode) {
            Vec3d v = Catenary.calc(vec, progress);
            buffer.setTranslation(
                    x - (double) pos.getX() + v.x,
                    y - (double) pos.getY() + v.y,
                    z - (double) pos.getZ() + v.z
            );
        } else {
            buffer.setTranslation(
                    x - (double) pos.getX(),
                    y - (double) pos.getY(),
                    z - (double) pos.getZ()
            );
        }
    }

    @Override
    public void renderTileEntityFast(
            @Nonnull TileEntityBase te,
            double x, double y, double z,
            float partialTicks, int destroyStage, float partial,
            @Nonnull BufferBuilder buffer
    ) {
        if (te instanceof WireNode.TileEntityWireNode) {
            WireNode.TileEntityWireNode wireNode = (WireNode.TileEntityWireNode) te;

            Vec3d srcVec = null, dstVec = null;
            if (wireNode.getSender() != null) {
                BlockPos senderPos = wireNode.getSender().getPos();
                srcVec = new Vec3d(senderPos.subtract(wireNode.getPos()));
            }
            if (wireNode.getTarget() != null) {
                BlockPos targetPos = wireNode.getTarget().getPos();
                dstVec = new Vec3d(targetPos.subtract(wireNode.getPos()));
            }

            BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
            BlockModelRenderer renderer = dispatcher.getBlockModelRenderer();

            BlockPos pos = wireNode.getPos();
            IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
            IBlockState state = world.getBlockState(pos);
            Block block = wireNode.getBlockType();

            IBlockState wire = state.withProperty(WireNode.SHAPE, WireNode.EnumShape.WIRE);
            IBlockState drop = state.withProperty(WireNode.SHAPE, WireNode.EnumShape.DROP);
            IBakedModel wireModel = dispatcher.getBlockModelShapes().getModelForState(wire);
            IBakedModel dropModel = dispatcher.getBlockModelShapes().getModelForState(drop);

            if (srcVec != null) {
                double step = getStep(srcVec, block);
                for (double i = step; i < 1; i += step) {
                    calcWire(srcVec, i, x, y, z, block, pos, buffer);
                    if ((((int) (i / step)) % 16) == 0)
                        renderer.renderModel(world, dropModel, drop, pos, buffer, false);
                    else
                        renderer.renderModel(world, wireModel, wire, pos, buffer, false);
                }
            }

            if (dstVec != null) {
                double step = getStep(dstVec, block);
                for (double i = step; i < 1; i += step) {
                    calcWire(dstVec, i, x, y, z, block, pos, buffer);
                    if ((((int) (i / step)) % 16) == 0)
                        renderer.renderModel(world, dropModel, drop, pos, buffer, false);
                    else
                        renderer.renderModel(world, wireModel, wire, pos, buffer, false);
                }
            }

        }
    }

}
