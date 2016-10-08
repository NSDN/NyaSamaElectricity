package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.4.7.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class CatenaryShelfInsulatorModel extends ModelBase {
    //fields

    ModelRenderer Shape48;
    ModelRenderer Shape49;
    ModelRenderer Shape50;
    ModelRenderer Shape51;
    ModelRenderer Shape52;
    ModelRenderer Shape53;
    ModelRenderer Shape54;
    ModelRenderer Shape55;
    ModelRenderer Shape56;
    ModelRenderer Shape57;
    ModelRenderer Shape58;
    ModelRenderer Shape60;
    ModelRenderer Shape62;

    public CatenaryShelfInsulatorModel() {
        textureWidth = 22;
        textureHeight = 17;

        Shape48 = new ModelRenderer(this, 0, 0);
        Shape48.addBox(0F, 0F, 0F, 5, 1, 5);
        Shape48.setRotationPoint(-2.5F, 13.5F, 13.5F);
        Shape48.setTextureSize(22, 17);
        Shape48.mirror = true;
        setRotation(Shape48, 0F, 0F, 0F);
        Shape49 = new ModelRenderer(this, 0, 0);
        Shape49.addBox(0F, 0F, 0F, 2, 2, 9);
        Shape49.setRotationPoint(-1F, 3.5F, 4F);
        Shape49.setTextureSize(22, 17);
        Shape49.mirror = true;
        setRotation(Shape49, 0F, 0F, 0F);
        Shape50 = new ModelRenderer(this, 0, 0);
        Shape50.addBox(0F, 0F, 0F, 2, 2, 4);
        Shape50.setRotationPoint(-1F, 3.5F, 13F);
        Shape50.setTextureSize(22, 17);
        Shape50.mirror = true;
        setRotation(Shape50, 0F, 0F, 0F);
        Shape51 = new ModelRenderer(this, 0, 0);
        Shape51.addBox(0F, 0F, 0F, 1, 2, 1);
        Shape51.setRotationPoint(-0.5F, 15.5F, 15.5F);
        Shape51.setTextureSize(22, 17);
        Shape51.mirror = true;
        setRotation(Shape51, 0F, 0F, 0F);
        Shape52 = new ModelRenderer(this, 0, 0);
        Shape52.addBox(0F, 0F, 0F, 1, 2, 1);
        Shape52.setRotationPoint(-0.5F, 5.5F, 15.5F);
        Shape52.setTextureSize(22, 17);
        Shape52.mirror = true;
        setRotation(Shape52, 0F, 0F, 0F);
        Shape53 = new ModelRenderer(this, 0, 0);
        Shape53.addBox(0F, 0F, 0F, 5, 1, 5);
        Shape53.setRotationPoint(-2.5F, 7.5F, 13.5F);
        Shape53.setTextureSize(22, 17);
        Shape53.mirror = true;
        setRotation(Shape53, 0F, 0F, 0F);
        Shape54 = new ModelRenderer(this, 0, 0);
        Shape54.addBox(0F, 0F, 0F, 2, 3, 2);
        Shape54.setRotationPoint(-1F, 6.5F, 15F);
        Shape54.setTextureSize(22, 17);
        Shape54.mirror = true;
        setRotation(Shape54, 0F, 0F, 0F);
        Shape55 = new ModelRenderer(this, 0, 0);
        Shape55.addBox(0F, 0F, 0F, 5, 1, 5);
        Shape55.setRotationPoint(-2.5F, 9.5F, 13.5F);
        Shape55.setTextureSize(22, 17);
        Shape55.mirror = true;
        setRotation(Shape55, 0F, 0F, 0F);
        Shape56 = new ModelRenderer(this, 0, 0);
        Shape56.addBox(0F, 0F, 0F, 2, 1, 2);
        Shape56.setRotationPoint(-1F, 10.5F, 15F);
        Shape56.setTextureSize(22, 17);
        Shape56.mirror = true;
        setRotation(Shape56, 0F, 0F, 0F);
        Shape57 = new ModelRenderer(this, 0, 0);
        Shape57.addBox(0F, 0F, 0F, 5, 1, 5);
        Shape57.setRotationPoint(-2.5F, 11.5F, 13.5F);
        Shape57.setTextureSize(22, 17);
        Shape57.mirror = true;
        setRotation(Shape57, 0F, 0F, 0F);
        Shape58 = new ModelRenderer(this, 0, 0);
        Shape58.addBox(0F, 0F, 0F, 2, 1, 2);
        Shape58.setRotationPoint(-1F, 12.5F, 15F);
        Shape58.setTextureSize(22, 17);
        Shape58.mirror = true;
        setRotation(Shape58, 0F, 0F, 0F);
        Shape60 = new ModelRenderer(this, 0, 0);
        Shape60.addBox(0F, 0F, 0F, 2, 1, 2);
        Shape60.setRotationPoint(-1F, 14.5F, 15F);
        Shape60.setTextureSize(22, 17);
        Shape60.mirror = true;
        setRotation(Shape60, 0F, 0F, 0F);
        Shape62 = new ModelRenderer(this, 0, 0);
        Shape62.addBox(0F, 0F, 0F, 1, 1, 10);
        Shape62.setRotationPoint(-0.5F, -1.5F, 4F);
        Shape62.setTextureSize(22, 17);
        Shape62.mirror = true;
        setRotation(Shape62, -0.5235988F, 0F, 0F);


    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);

        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.0F, -1.0F);
        GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        Shape48.render(f5);
        Shape49.render(f5);
        Shape50.render(f5);
        Shape51.render(f5);
        Shape52.render(f5);
        Shape53.render(f5);
        Shape54.render(f5);
        Shape55.render(f5);
        Shape56.render(f5);
        Shape57.render(f5);
        Shape58.render(f5);
        Shape60.render(f5);
        Shape62.render(f5);
        GL11.glPopMatrix();

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
