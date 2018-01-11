package club.nsdn.nyasamaelectricity.renderer.decoration.catenary;

/**
 * Created by drzzm32 on 2016.4.7.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CatenaryShelfBodyHModel extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape9;
    ModelRenderer Shape10;

    public CatenaryShelfBodyHModel() {
        textureWidth = 18;
        textureHeight = 17;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 4, 16, 1);
        Shape1.setRotationPoint(-2F, 8F, -3F);
        Shape1.setTextureSize(18, 17);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 4, 16, 1);
        Shape2.setRotationPoint(-2F, 8F, 2F);
        Shape2.setTextureSize(18, 17);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 1, 3, 4);
        Shape5.setRotationPoint(-0.5F, 18F, -2F);
        Shape5.setTextureSize(18, 17);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 1, 5, 4);
        Shape6.setRotationPoint(-0.5F, 13F, -2F);
        Shape6.setTextureSize(18, 17);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(0F, 0F, 0F, 1, 5, 4);
        Shape9.setRotationPoint(-0.5F, 8F, -2F);
        Shape9.setTextureSize(18, 17);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 1, 3, 4);
        Shape10.setRotationPoint(-0.5F, 21F, -2F);
        Shape10.setTextureSize(18, 17);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
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
