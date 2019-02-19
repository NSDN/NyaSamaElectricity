package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import club.nsdn.nyasamatelecom.api.render.AbsFastTESR;
import club.nsdn.nyasamatelecom.api.tileentity.TileEntityBase;
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

    public double getStep(Vec3d vec) {
        return 1.0 / MathHelper.floor(vec.lengthVector() / 0.0625);
    }

    public void calcWire(
            Vec3d vec, double progress,
            double x, double y, double z,
            BlockPos pos, BufferBuilder buffer
    ) {
        if (progress > 1) progress = 1;
        if (progress < 0) progress = 0;

        buffer.setTranslation(
                x - (double) pos.getX() + vec.x * progress,
                y - (double) pos.getY() + vec.y * progress,
                z - (double) pos.getZ() + vec.z * progress
        );
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

            IBlockState wire = state.withProperty(WireNode.SHAPE, WireNode.EnumShape.WIRE);
            IBlockState drop = state.withProperty(WireNode.SHAPE, WireNode.EnumShape.DROP);
            IBakedModel wireModel = dispatcher.getBlockModelShapes().getModelForState(wire);
            IBakedModel dropModel = dispatcher.getBlockModelShapes().getModelForState(drop);

            if (srcVec != null) {
                double step = getStep(srcVec);
                for (double i = step; i < 1; i += step) {
                    calcWire(srcVec, i, x, y, z, pos, buffer);
                    if ((((int) (i / step)) % 16) == 0)
                        renderer.renderModel(world, dropModel, drop, pos, buffer, false);
                    else
                        renderer.renderModel(world, wireModel, wire, pos, buffer, false);

                }
            }

            if (dstVec != null) {
                double step = getStep(dstVec);
                for (double i = step; i < 1; i += step) {
                    calcWire(dstVec, i, x, y, z, pos, buffer);
                    if ((((int) (i / step)) % 16) == 0)
                        renderer.renderModel(world, dropModel, drop, pos, buffer, false);
                    else
                        renderer.renderModel(world, wireModel, wire, pos, buffer, false);
                }
            }

        }
    }

}
