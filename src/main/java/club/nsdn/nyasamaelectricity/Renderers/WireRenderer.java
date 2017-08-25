package club.nsdn.nyasamaelectricity.Renderers;

import club.nsdn.nyasamaelectricity.TileEntities.TileEntityWire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


/**
 * Created by drzzm on 2017.4.30.
 */
public class WireRenderer extends TileEntitySpecialRenderer {

    public ResourceLocation texture;
    public double thickness;
    public ModelBase model;

    /**
     * 1. 若 nextNode 为 null，则仅在此位置渲染一个单元
     * 2. thisNode -> nextNode，计算向量和此向量在 xOy 平面的投影长度 L，则可得到渲染次数为 L / thickness
     * 3. 在此向量所属且垂直于 xOy 平面的面上计算曲线方程，并转换到游戏坐标系进行渲染
     * 4. 由 y = ax^2 + bx + c 得，至少需要三个点才可唯一确定一条抛物线，因此需要添加约束条件
     * 5. 如添加此条件：曲线最低点到最低端点的垂直距离为两端点距离的。。。
     * 6. 这个待定。。。感觉最好还是做曲线积分然后用曲线长度进行约束
     **/

    public WireRenderer(String texture, double thickness, ModelBase model) {
        this.texture = new ResourceLocation("nyasamaelectricity", texture);
        this.thickness = thickness;
        this.model = model;
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        TileEntityWire.WireNode thisNode = (TileEntityWire.WireNode) te;
        TileEntityWire.WireNode nextNode = null;
        if (thisNode.nextNode != null) {
            if (thisNode.getWorldObj().getTileEntity(
                    (int) thisNode.nextNode.xCoord, (int) thisNode.nextNode.yCoord, (int) thisNode.nextNode.zCoord
            ) != null) {
                nextNode = (TileEntityWire.WireNode) thisNode.getWorldObj().getTileEntity(
                        (int) thisNode.nextNode.xCoord, (int) thisNode.nextNode.yCoord, (int) thisNode.nextNode.zCoord
                );
            }
        }

        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

            Tessellator.instance.setColorOpaque_F(1.0F, 1.0F, 1.0F);

            RenderHelper.disableStandardItemLighting();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);

            if (Minecraft.isAmbientOcclusionEnabled())
            {
                GL11.glShadeModel(GL11.GL_SMOOTH);
            }
            else
            {
                GL11.glShadeModel(GL11.GL_FLAT);
            }

            GL11.glPushMatrix();
            {
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
                model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

            }
            GL11.glPopMatrix();

            RenderHelper.enableStandardItemLighting();
        }
        GL11.glPopMatrix();
    }

}
