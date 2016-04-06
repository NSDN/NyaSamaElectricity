package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class InsulatorSquareBigModel extends ModelBase {

    ModelRenderer Shape1;
    ModelRenderer Shape4;
    ModelRenderer Shape3;
    ModelRenderer Shape2;
    ModelRenderer Shape5;

    public InsulatorSquareBigModel()
    {
        textureWidth = 96;
        textureHeight = 28;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 4, 4, 4);
        Shape1.setRotationPoint(-2F, 20F, -2F);
        Shape1.setTextureSize(96, 28);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 8, 4, 8);
        Shape4.setRotationPoint(-4F, 8F, -4F);
        Shape4.setTextureSize(96, 28);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 24, 4, 24);
        Shape3.setRotationPoint(-12F, 12F, -12F);
        Shape3.setTextureSize(96, 28);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 8, 4, 8);
        Shape2.setRotationPoint(-4F, 16F, -4F);
        Shape2.setTextureSize(96, 28);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 4, 8, 4);
        Shape5.setRotationPoint(-2F, 0F, -2F);
        Shape5.setTextureSize(96, 28);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        Shape4.render(f5);
        Shape3.render(f5);
        Shape2.render(f5);
        Shape5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
