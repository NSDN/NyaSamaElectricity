package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.3.25.
 */

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class InsulatorSquareMediumModel extends ModelBase {

    ModelRenderer Shape10;
    ModelRenderer Shape8;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape9;

    public InsulatorSquareMediumModel() {
        textureWidth = 56;
        textureHeight = 16;

        Shape10 = new ModelRenderer(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 2, 4, 2);
        Shape10.setRotationPoint(-1F, 0F, -1F);
        Shape10.setTextureSize(56, 16);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 14, 2, 14);
        Shape8.setRotationPoint(-7F, 8F, -7F);
        Shape8.setTextureSize(56, 16);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 6, 2, 6);
        Shape1.setRotationPoint(-3F, 22F, -3F);
        Shape1.setTextureSize(56, 16);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 14, 2, 14);
        Shape2.setRotationPoint(-7F, 20F, -7F);
        Shape2.setTextureSize(56, 16);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 6, 2, 6);
        Shape3.setRotationPoint(-3F, 18F, -3F);
        Shape3.setTextureSize(56, 16);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 14, 2, 14);
        Shape4.setRotationPoint(-7F, 16F, -7F);
        Shape4.setTextureSize(56, 16);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 6, 2, 6);
        Shape5.setRotationPoint(-3F, 14F, -3F);
        Shape5.setTextureSize(56, 16);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 14, 2, 14);
        Shape6.setRotationPoint(-7F, 12F, -7F);
        Shape6.setTextureSize(56, 16);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 6, 2, 6);
        Shape7.setRotationPoint(-3F, 10F, -3F);
        Shape7.setTextureSize(56, 16);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(0F, 0F, 0F, 6, 4, 6);
        Shape9.setRotationPoint(-3F, 4F, -3F);
        Shape9.setTextureSize(56, 16);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Shape10.render(f5);
        Shape8.render(f5);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape9.render(f5);
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
