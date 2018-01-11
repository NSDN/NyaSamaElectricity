package club.nsdn.nyasamaelectricity.renderer.decoration.shelf;

/**
 * Created by drzzm32 on 2016.4.10.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ShelfQuadConverterModel extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;

    public ShelfQuadConverterModel() {
        textureWidth = 22;
        textureHeight = 17;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 8, 1, 1);
        Shape1.setRotationPoint(-4F, 8F, -4F);
        Shape1.setTextureSize(22, 17);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 15, 1);
        Shape2.setRotationPoint(-4F, 8F, -4F);
        Shape2.setTextureSize(22, 17);
        Shape2.mirror = true;
        setRotation(Shape2, -0.2181662F, 0F, 0.2181662F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 3, 3, 3);
        Shape3.setRotationPoint(-8F, 21F, -8F);
        Shape3.setTextureSize(22, 17);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 1, 1, 10);
        Shape4.setRotationPoint(-7F, 22F, -5F);
        Shape4.setTextureSize(22, 17);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        for (int i = 0; i < 4; i++) {
            GL11.glPushMatrix();
            GL11.glRotatef(i * 90.0F, 0.0F, 1.0F, 0.0F);
            Shape1.render(f5);
            Shape2.render(f5);
            Shape3.render(f5);
            Shape4.render(f5);
            GL11.glPopMatrix();
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
