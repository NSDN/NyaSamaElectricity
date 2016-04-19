package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.4.10.
 */

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ShelfQuadHeadModel extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape7;
    ModelRenderer Shape9;
    ModelRenderer Shape2;
    ModelRenderer Shape6;
    ModelRenderer Shape8;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
  
  public ShelfQuadHeadModel()
  {
    textureWidth = 22;
    textureHeight = 16;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 3, 3, 3);
      Shape1.setRotationPoint(5F, 21F, 5F);
      Shape1.setTextureSize(22, 16);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 3, 3, 3);
      Shape3.setRotationPoint(5F, 21F, -8F);
      Shape3.setTextureSize(22, 16);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, 0F, 3, 3, 3);
      Shape4.setRotationPoint(-8F, 21F, 5F);
      Shape4.setTextureSize(22, 16);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(0F, 0F, 0F, 3, 3, 3);
      Shape5.setRotationPoint(-1.5F, 8F, -1.5F);
      Shape5.setTextureSize(22, 16);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, 0F, 0F, 3, 3, 3);
      Shape7.setRotationPoint(-8F, 21F, -8F);
      Shape7.setTextureSize(22, 16);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 0);
      Shape9.addBox(0F, 0F, 0F, 1, 15, 1);
      Shape9.setRotationPoint(-1F, 10F, -1F);
      Shape9.setTextureSize(22, 16);
      Shape9.mirror = true;
      setRotation(Shape9, -0.4014257F, 0F, 0.4363323F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 1, 15, 1);
      Shape2.setRotationPoint(0.2F, 10.5F, -1F);
      Shape2.setTextureSize(22, 16);
      Shape2.mirror = true;
      setRotation(Shape2, -0.4014257F, 0F, -0.4363323F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, 0F, 0F, 1, 15, 1);
      Shape6.setRotationPoint(-1F, 10F, 0F);
      Shape6.setTextureSize(22, 16);
      Shape6.mirror = true;
      setRotation(Shape6, 0.4014257F, 0F, 0.4363323F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(0F, 0F, 0F, 1, 15, 1);
      Shape8.setRotationPoint(0.2F, 10.5F, 0F);
      Shape8.setTextureSize(22, 16);
      Shape8.mirror = true;
      setRotation(Shape8, 0.4014257F, 0F, -0.4363323F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(0F, 0F, 0F, 1, 1, 10);
      Shape10.setRotationPoint(6F, 22F, -5F);
      Shape10.setTextureSize(22, 16);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 0);
      Shape11.addBox(0F, 0F, 0F, 1, 1, 10);
      Shape11.setRotationPoint(-7F, 22F, -5F);
      Shape11.setTextureSize(22, 16);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 0, 0);
      Shape12.addBox(0F, 0F, 0F, 10, 1, 1);
      Shape12.setRotationPoint(-5F, 22F, 6F);
      Shape12.setTextureSize(22, 16);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 0);
      Shape13.addBox(0F, 0F, 0F, 10, 1, 1);
      Shape13.setRotationPoint(-5F, 22F, -7F);
      Shape13.setTextureSize(22, 16);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape7.render(f5);
    Shape9.render(f5);
    Shape2.render(f5);
    Shape6.render(f5);
    Shape8.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
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
