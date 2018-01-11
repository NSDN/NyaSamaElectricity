package club.nsdn.nyasamaelectricity.renderer.decoration.shelf;

/**
 * Created by drzzm32 on 2016.4.10.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ShelfQuadThinModel extends ModelBase {
    //fields
    ModelRenderer Piece1;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;

    public ShelfQuadThinModel() {
        textureWidth = 18;
        textureHeight = 17;
        setTextureOffset("Piece1.Shape1", 0, 0);
        setTextureOffset("Piece1.Shape2", 0, 0);
        setTextureOffset("Piece1.Shape3", 0, 0);
        setTextureOffset("Piece1.Shape4", 0, 0);
        setTextureOffset("Piece1.Shape5", 0, 0);
        setTextureOffset("Piece1.Shape6", 0, 0);
        setTextureOffset("Piece1.Shape7", 0, 0);
        setTextureOffset("Piece1.Shape8", 0, 0);
        setTextureOffset("Piece1.Shape9", 0, 0);
        setTextureOffset("Piece1.Shape10", 0, 0);
        setTextureOffset("Piece1.Shape11", 0, 0);
        setTextureOffset("Piece1.Shape12", 0, 0);

        Piece1 = new ModelRenderer(this, "Piece1");
        Piece1.setRotationPoint(0F, 8F, 0F);
        setRotation(Piece1, 0F, 0F, 0F);
        Piece1.mirror = true;
        Piece1.addBox("Shape1", -4F, 0F, -4F, 1, 16, 1);
        Piece1.addBox("Shape2", -4F, 0F, 3F, 1, 16, 1);
        Piece1.addBox("Shape3", 3F, 0F, -4F, 1, 16, 1);
        Piece1.addBox("Shape4", 3F, 0F, 3F, 1, 16, 1);
        Piece1.addBox("Shape5", -4F, 0F, -4F, 8, 1, 1);
        Piece1.addBox("Shape6", -4F, 15F, -4F, 8, 1, 1);
        Piece1.addBox("Shape7", -4F, 0F, 3F, 8, 1, 1);
        Piece1.addBox("Shape8", -4F, 15F, 3F, 8, 1, 1);
        Piece1.addBox("Shape9", -4F, 0F, -4F, 1, 1, 8);
        Piece1.addBox("Shape10", -4F, 15F, -4F, 1, 1, 8);
        Piece1.addBox("Shape11", 3F, 0F, -4F, 1, 1, 8);
        Piece1.addBox("Shape12", 3F, 15F, -4F, 1, 1, 8);
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape1.setRotationPoint(-0.5F, 9F, -4F);
        Shape1.setTextureSize(18, 17);
        Shape1.mirror = true;
        setRotation(Shape1, 0.4581489F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape2.setRotationPoint(-0.5F, 8.7F, 3F);
        Shape2.setTextureSize(18, 17);
        Shape2.mirror = true;
        setRotation(Shape2, -0.4537856F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape3.setRotationPoint(-4F, 8.7F, 3F);
        Shape3.setTextureSize(18, 17);
        Shape3.mirror = true;
        setRotation(Shape3, -0.4537856F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape4.setRotationPoint(3F, 8.7F, 3F);
        Shape4.setTextureSize(18, 17);
        Shape4.mirror = true;
        setRotation(Shape4, -0.4537856F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape5.setRotationPoint(-4F, 9F, -4F);
        Shape5.setTextureSize(18, 17);
        Shape5.mirror = true;
        setRotation(Shape5, 0.4581489F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape6.setRotationPoint(3F, 9F, -4F);
        Shape6.setTextureSize(18, 17);
        Shape6.mirror = true;
        setRotation(Shape6, 0.4581489F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape7.setRotationPoint(-4F, 9F, -0.5F);
        Shape7.setTextureSize(18, 17);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, -0.4581489F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 1, 16, 1);
        Shape8.setRotationPoint(4F, 9F, 0.5F);
        Shape8.setTextureSize(18, 17);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 3.141593F, 0.4537856F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Piece1.render(f5);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        GL11.glPushMatrix();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        GL11.glPopMatrix();
        Shape7.render(f5);
        Shape8.render(f5);
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
