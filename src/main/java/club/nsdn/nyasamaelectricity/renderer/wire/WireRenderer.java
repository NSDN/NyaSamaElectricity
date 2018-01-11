package club.nsdn.nyasamaelectricity.renderer.wire;

import club.nsdn.nyasamaelectricity.renderer.RendererHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.WavefrontObject;
import org.lwjgl.opengl.GL11;

/**
 * Created by drzzm32 on 2017.9.9.
 */
public class WireRenderer extends TileEntitySpecialRenderer {

    private static final int STRAIGHT = 0, SLOPE = 1, TURNED = 2;
    private final WavefrontObject[] model;
    private final ResourceLocation[] textures;

    public WireRenderer() {
        this.model = new WavefrontObject[]{
                new WavefrontObject(new ResourceLocation("nyasamaelectricity", "models/blocks/wire_straight.obj")),
                new WavefrontObject(new ResourceLocation("nyasamaelectricity", "models/blocks/wire_slope.obj")),
                new WavefrontObject(new ResourceLocation("nyasamaelectricity", "models/blocks/wire_turned.obj"))
        };
        this.textures = new ResourceLocation[]{
                new ResourceLocation("nyasamaelectricity", "textures/blocks/catenary_wire.png"),
                new ResourceLocation("nyasamaelectricity", "textures/blocks/catenary_wire.png"),
                new ResourceLocation("nyasamaelectricity", "textures/blocks/catenary_wire.png")
        };
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

        switch (te.getBlockMetadata()) {
            case 0: //N=S
                RendererHelper.renderWithResourceAndRotation(this.model[STRAIGHT], 0.0F, textures[STRAIGHT]);
                break;
            case 1: //W=E
                RendererHelper.renderWithResourceAndRotation(this.model[STRAIGHT], 90.0F, textures[STRAIGHT]);
                break;
            case 2: //E
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], -90.0F, textures[SLOPE]);
                break;
            case 3: //W
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], 90.0F, textures[SLOPE]);
                break;
            case 4: //N
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], 180.0F, textures[SLOPE]);
                break;
            case 5: //S
                RendererHelper.renderWithResourceAndRotation(this.model[SLOPE], 0.0F, textures[SLOPE]);
                break;
            case 6: //S-E
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], 180.0F, textures[TURNED]);
                break;
            case 7: //S-W
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], -90.0F, textures[TURNED]);
                break;
            case 8: //N-W
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], 0.0F, textures[TURNED]);
                break;
            case 9: //N-E
                RendererHelper.renderWithResourceAndRotation(this.model[TURNED], 90.0F, textures[TURNED]);
                break;
        }

        RenderHelper.enableStandardItemLighting();

        GL11.glPopMatrix();
    }

}

