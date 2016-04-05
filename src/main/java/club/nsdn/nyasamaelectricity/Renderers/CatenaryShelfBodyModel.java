package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CatenaryShelfBodyModel extends ModelBase
{
  //fields
    ModelRenderer Shape2;
    ModelRenderer Shape1;
    ModelRenderer Shape4;
    ModelRenderer Shape3;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
  
  public CatenaryShelfBodyModel()
  {
    textureWidth = 36;
    textureHeight = 26;
    
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 2, 16, 10);
      Shape2.setRotationPoint(6F, 8F, -5F);
      Shape2.setTextureSize(36, 26);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 2, 16, 10);
      Shape1.setRotationPoint(-8F, 8F, -5F);
      Shape1.setTextureSize(36, 26);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, 0F, 12, 2, 6);
      Shape4.setRotationPoint(-6F, 8F, -3F);
      Shape4.setTextureSize(36, 26);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 12, 2, 6);
      Shape3.setRotationPoint(-6F, 22F, -3F);
      Shape3.setTextureSize(36, 26);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(0F, 0F, 0F, 2, 10, 2);
      Shape5.setRotationPoint(-6F, 10F, -1F);
      Shape5.setTextureSize(36, 26);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, 0F, 0F, 10, 2, 2);
      Shape6.setRotationPoint(-4F, 10F, -1F);
      Shape6.setTextureSize(36, 26);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, 0F, 0F, 2, 10, 2);
      Shape7.setRotationPoint(4F, 12F, -1F);
      Shape7.setTextureSize(36, 26);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(0F, 0F, 0F, 10, 2, 2);
      Shape8.setRotationPoint(-6F, 20F, -1F);
      Shape8.setTextureSize(36, 26);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    Shape2.render(f5);
    Shape1.render(f5);
    Shape4.render(f5);
    Shape3.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
