package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.4.7.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CatenaryShelfHeadModel extends ModelBase {
    //fields
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;

    ModelRenderer Piece1;
    ModelRenderer Piece2;

    public CatenaryShelfHeadModel() {
        textureWidth = 22;
        textureHeight = 17;
        setTextureOffset("Piece1.Shape1", 0, 0);
        setTextureOffset("Piece1.Shape14", 0, 0);
        setTextureOffset("Piece1.Shape2", 0, 0);
        setTextureOffset("Piece1.Shape13", 0, 0);
        setTextureOffset("Piece1.Shape3", 0, 0);
        setTextureOffset("Piece1.Shape4", 0, 0);
        setTextureOffset("Piece1.Shape5", 0, 0);
        setTextureOffset("Piece1.Shape10", 0, 0);
        setTextureOffset("Piece1.Shape6", 0, 0);
        setTextureOffset("Piece1.Shape7", 0, 0);
        setTextureOffset("Piece1.Shape8", 0, 0);
        setTextureOffset("Piece2.Shape1", 0, 0);
        setTextureOffset("Piece2.Shape14", 0, 0);
        setTextureOffset("Piece2.Shape2", 0, 0);
        setTextureOffset("Piece2.Shape13", 0, 0);
        setTextureOffset("Piece2.Shape3", 0, 0);
        setTextureOffset("Piece2.Shape12", 0, 0);
        setTextureOffset("Piece2.Shape4", 0, 0);
        setTextureOffset("Piece2.Shape11", 0, 0);
        setTextureOffset("Piece2.Shape5", 0, 0);
        setTextureOffset("Piece2.Shape10", 0, 0);
        setTextureOffset("Piece2.Shape6", 0, 0);
        setTextureOffset("Piece2.Shape9", 0, 0);
        setTextureOffset("Piece2.Shape7", 0, 0);
        setTextureOffset("Piece2.Shape8", 0, 0);

        Shape15 = new ModelRenderer(this, 0, 0);
        Shape15.addBox(0F, 0F, 0F, 2, 4, 2);
        Shape15.setRotationPoint(-1F, -8F, -1F);
        Shape15.setTextureSize(22, 17);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new ModelRenderer(this, 0, 0);
        Shape16.addBox(0F, 0F, 0F, 2, 2, 4);
        Shape16.setRotationPoint(-1F, -4F, -3F);
        Shape16.setTextureSize(22, 17);
        Shape16.mirror = true;
        setRotation(Shape16, 1.047198F, 0F, 0F);
        Shape17 = new ModelRenderer(this, 0, 0);
        Shape17.addBox(0F, 0F, 0F, 2, 2, 4);
        Shape17.setRotationPoint(-1F, -3F, 1.2F);
        Shape17.setTextureSize(22, 17);
        Shape17.mirror = true;
        setRotation(Shape17, 2.094395F, 0F, 0F);

        Piece1 = new ModelRenderer(this, "Piece1");
        Piece1.setRotationPoint(0F, -8F, 0F);
        setRotation(Piece1, 0F, 0F, 0F);
        Piece1.mirror = true;
        Piece1.addBox("Shape1", -2F, 4F, -4F, 4, 12, 1);
        Piece1.addBox("Shape14", -0.5F, 15F, -3.5F, 1, 1, 2);
        Piece1.addBox("Shape2", -2F, 4F, 3F, 4, 12, 1);
        Piece1.addBox("Shape13", -0.5F, 15F, 1.5F, 1, 1, 2);
        Piece1.addBox("Shape3", -1.5F, 4F, -3F, 3, 1, 6);
        Piece1.addBox("Shape4", -1.5F, 12F, -3F, 3, 1, 6);
        Piece1.addBox("Shape5", -0.5F, 10F, -3F, 1, 2, 6);
        Piece1.addBox("Shape10", -0.5F, 13F, -3F, 1, 2, 6);
        Piece1.addBox("Shape6", -0.5F, 5F, -3F, 1, 2, 6);
        Piece1.addBox("Shape7", -0.5F, 7F, 1.5F, 1, 3, 2);
        Piece1.addBox("Shape8", -0.5F, 7F, -3.5F, 1, 3, 2);
        Piece2 = new ModelRenderer(this, "Piece2");
        Piece2.setRotationPoint(0F, 8F, 0F);
        setRotation(Piece2, 0F, 0F, 0F);
        Piece2.mirror = true;
        Piece2.addBox("Shape1", -2F, 0F, -4F, 4, 16, 1);
        Piece2.addBox("Shape14", -0.5F, 15F, -3.5F, 1, 1, 2);
        Piece2.addBox("Shape2", -2F, 0F, 3F, 4, 16, 1);
        Piece2.addBox("Shape13", -0.5F, 15F, 1.5F, 1, 1, 2);
        Piece2.addBox("Shape3", -1.5F, 4F, -3F, 3, 1, 6);
        Piece2.addBox("Shape12", -0.5F, 0F, 1.5F, 1, 2, 2);
        Piece2.addBox("Shape4", -1.5F, 12F, -3F, 3, 1, 6);
        Piece2.addBox("Shape11", -0.5F, 0F, -3.5F, 1, 2, 2);
        Piece2.addBox("Shape5", -0.5F, 10F, -3F, 1, 2, 6);
        Piece2.addBox("Shape10", -0.5F, 13F, -3F, 1, 2, 6);
        Piece2.addBox("Shape6", -0.5F, 5F, -3F, 1, 2, 6);
        Piece2.addBox("Shape9", -0.5F, 2F, -3F, 1, 2, 6);
        Piece2.addBox("Shape7", -0.5F, 7F, 1.5F, 1, 3, 2);
        Piece2.addBox("Shape8", -0.5F, 7F, -3.5F, 1, 3, 2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Shape15.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);

        Piece1.render(f5);
        Piece2.render(f5);
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
