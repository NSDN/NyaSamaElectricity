package club.nsdn.nyasamaelectricity.renderer.wire;

import club.nsdn.nyasamaelectricity.renderer.RendererHelper;
import club.nsdn.nyasamaelectricity.tileblock.wire.BlockWire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.WavefrontObject;
import org.lwjgl.opengl.GL11;

/**
 * Created by drzzm32 on 2018.1.11.
 */
public class WireNodeRenderer extends TileEntitySpecialRenderer {

                                     /* 0  1  2  3  4  5  6  7  8  9 */
    private static final int[] CONV = { 0, 1, 1, 1, 0, 0, 8, 9, 6, 7 };
    private static final int STRAIGHT = 0, SLOPE = 1, TURNED = 2;
    private final WavefrontObject[] model;
    private final ResourceLocation texture;

    public WireNodeRenderer() {
        this.model = new WavefrontObject[]{
                new WavefrontObject(new ResourceLocation("nyasamaelectricity", "models/blocks/wire_straight.obj")),
                new WavefrontObject(new ResourceLocation("nyasamaelectricity", "models/blocks/wire_slope.obj")),
                new WavefrontObject(new ResourceLocation("nyasamaelectricity", "models/blocks/wire_turned.obj"))
        };
        this.texture = new ResourceLocation("nyasamaelectricity", "textures/blocks/catenary_node.png");
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        RenderHelper.disableStandardItemLighting();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        if (Minecraft.isAmbientOcclusionEnabled()) {
            GL11.glShadeModel(GL11.GL_SMOOTH);
        } else {
            GL11.glShadeModel(GL11.GL_FLAT);
        }

        Tessellator.instance.setColorOpaque_F(1.0F, 1.0F, 1.0F);

        if (!nearbyIsMe(te, 1, 0) && !nearbyIsMe(te, 0, -1) &&
            !nearbyIsMe(te, -1, 0) && !nearbyIsMe(te, 0, 1)) {
            renderByMeta(te.getBlockMetadata());
        }

        if (nearbyIsMe(te, 1, 0)) {
            renderByMeta(CONV[getNearbyMeta(te, 1, 0)]);
        }
        if (nearbyIsMe(te, 0, -1)) {
            renderByMeta(CONV[getNearbyMeta(te, 0, -1)]);
        }
        if (nearbyIsMe(te, -1, 0)) {
            renderByMeta(CONV[getNearbyMeta(te, -1, 0)]);
        }
        if (nearbyIsMe(te, 0, 1)) {
            renderByMeta(CONV[getNearbyMeta(te, 0, 1)]);
        }

        RenderHelper.enableStandardItemLighting();

        GL11.glPopMatrix();
    }

    private boolean nearbyIsMe(TileEntity te, int dx, int dz) {
        World world = te.getWorldObj();
        int x = te.xCoord, y = te.yCoord, z = te.zCoord;
        boolean result = false;
        if (world != null)
            result = world.getBlock(x + dx, y, z + dz) instanceof BlockWire;
        return result;
    }

    private int getNearbyMeta(TileEntity te, int dx, int dz) {
        World world = te.getWorldObj();
        int x = te.xCoord, y = te.yCoord, z = te.zCoord;
        int meta = 0;
        if (world != null)
            meta = world.getBlockMetadata(x + dx, y, z + dz);
        return meta % 10;
    }

    private void renderByMeta(int meta) {
        switch (meta) {
            case 0: //N=S
                RendererHelper.renderWithResourceAndRotation(this.model[STRAIGHT], 0.0F, texture);
                break;
            case 1: //W=E
                RendererHelper.renderWithResourceAndRotation(this.model[STRAIGHT], 90.0F, texture);
                break;
            case 2: //E
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], -90.0F, texture);
                break;
            case 3: //W
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], 90.0F, texture);
                break;
            case 4: //N
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], 180.0F, texture);
                break;
            case 5: //S
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], 0.0F, texture);
                break;
            case 6: //S-E
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], 180.0F, texture);
                break;
            case 7: //S-W
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], -90.0F, texture);
                break;
            case 8: //N-W
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], 0.0F, texture);
                break;
            case 9: //N-E
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], 90.0F, texture);
                break;
        }
    }

}

