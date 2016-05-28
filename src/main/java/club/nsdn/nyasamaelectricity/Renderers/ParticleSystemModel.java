package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.4.10.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ParticleSystemModel extends ModelBase {
    private static final int LIST_ID = 32323;

    public ParticleSystemModel() {
        GL11.glNewList(LIST_ID, GL11.GL_COMPILE);

        GL11.glScalef(0.1F, 0.1F, 0.1F);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(-0.5F, -0.5F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(-0.5F, 0.5F, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(0.5F, 0.5F, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(0.5F, -0.5F, 0.0F);
        GL11.glEnd();

        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(-0.5F, -0.5F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(-0.5F, 0.5F, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(0.5F, 0.5F, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(0.5F, -0.5F, 0.0F);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(-0.5F, -0.5F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(-0.5F, 0.5F, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(0.5F, 0.5F, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(0.5F, -0.5F, 0.0F);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEndList();
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);

        double light = 0.25;

        for (float i = 0.0F; i < Math.PI * 2; i += Math.PI / 8 + Math.signum(Math.random() - 0.5) * Math.random()) {
            for (float j = 0.0F; j < Math.PI * 2; j += Math.PI / 8 + Math.signum(Math.random() - 0.5) * Math.random()) {
                for (float k = 0.0F; k < Math.PI * 2; k += Math.PI / 8 + Math.signum(Math.random() - 0.5) * Math.random()) {
                    GL11.glColor4d(
                            light + (1.0 - light) * Math.random(),
                            light + (1.0 - light) * Math.random(),
                            light + (1.0 - light) * Math.random(),
                            light + (1.0 - light)
                    );
                    GL11.glPushMatrix();
                    GL11.glTranslated(
                            2.0 * Math.cos(i) * Math.sin(k),
                            2.0 * Math.sin(j) * Math.sin(k),
                            2.0 * Math.cos(k)
                    );
                    GL11.glCallList(LIST_ID);
                    GL11.glPopMatrix();
                }
            }
        }

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
