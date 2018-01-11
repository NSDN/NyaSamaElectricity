package club.nsdn.nyasamaelectricity.renderer.decoration.shelf;

/**
 * Created by drzzm32 on 2016.4.10.
 */

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ShelfTriangleHeadModel extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape4;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape14;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape42;
    ModelRenderer Shape43;
    ModelRenderer Shape2;

    public ShelfTriangleHeadModel() {
        textureWidth = 30;
        textureHeight = 15;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 3, 1, 3);
        Shape1.setRotationPoint(5F, 23F, 5F);
        Shape1.setTextureSize(30, 15);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 3, 1, 3);
        Shape4.setRotationPoint(-8F, 23F, 5F);
        Shape4.setTextureSize(30, 15);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 3, 2, 3);
        Shape7.setRotationPoint(-1.5F, 8F, -1.5F);
        Shape7.setTextureSize(30, 15);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 3, 1, 3);
        Shape8.setRotationPoint(-1.5F, 23F, -6.3F);
        Shape8.setTextureSize(30, 15);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(0F, 0F, 0F, 2, 5, 2);
        Shape9.setRotationPoint(5.5F, 18F, 5.5F);
        Shape9.setTextureSize(30, 15);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 2, 5, 2);
        Shape10.setRotationPoint(-7.5F, 18F, 5.5F);
        Shape10.setTextureSize(30, 15);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 0, 0);
        Shape11.addBox(0F, 0F, 0F, 2, 5, 2);
        Shape11.setRotationPoint(-1F, 18F, -5.8F);
        Shape11.setTextureSize(30, 15);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 0, 0);
        Shape14.addBox(0F, 0F, 0F, 1, 2, 13);
        Shape14.setRotationPoint(0F, 20F, -5F);
        Shape14.setTextureSize(30, 15);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0.5235988F, 0F);
        Shape16 = new ModelRenderer(this, 0, 0);
        Shape16.addBox(0F, 0F, 0F, 1, 2, 13);
        Shape16.setRotationPoint(-1F, 20F, -5F);
        Shape16.setTextureSize(30, 15);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, -0.5235988F, 0F);
        Shape17 = new ModelRenderer(this, 0, 0);
        Shape17.addBox(0F, 0F, 0F, 12, 2, 1);
        Shape17.setRotationPoint(-6F, 20F, 6F);
        Shape17.setTextureSize(30, 15);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape42 = new ModelRenderer(this, 0, 0);
        Shape42.addBox(0F, 0F, 0F, 2, 10, 2);
        Shape42.setRotationPoint(-1F, 9.1F, -1.3F);
        Shape42.setTextureSize(30, 15);
        Shape42.mirror = true;
        setRotation(Shape42, -0.4712389F, 0F, 0F);
        Shape43 = new ModelRenderer(this, 0, 0);
        Shape43.addBox(0F, 0F, 0F, 2, 13, 2);
        Shape43.setRotationPoint(-1F, 10F, -1F);
        Shape43.setTextureSize(30, 15);
        Shape43.mirror = true;
        setRotation(Shape43, 0.4886922F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 2, 11);
        Shape2.setRotationPoint(-0.5F, 18F, -4.5F);
        Shape2.setTextureSize(30, 15);
        Shape2.mirror = true;
        setRotation(Shape2, -0.1745329F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        Shape4.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape14.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);
        Shape42.render(f5);
        Shape43.render(f5);
        Shape2.render(f5);
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
