package club.nsdn.nyasamaelectricity.Renderers;

/**
 * Created by drzzm32 on 2016.4.5.
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CatenaryShelfHeadModel extends ModelBase
{
  //fields
    ModelRenderer Shape2;
    ModelRenderer Shape1;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
  
  public CatenaryShelfHeadModel()
  {
    textureWidth = 36;
    textureHeight = 17;
    
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 2, 4, 10);
      Shape2.setRotationPoint(6F, 20F, -5F);
      Shape2.setTextureSize(36, 17);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 2, 4, 10);
      Shape1.setRotationPoint(-8F, 20F, -5F);
      Shape1.setTextureSize(36, 17);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 12, 4, 6);
      Shape3.setRotationPoint(-6F, 20F, -3F);
      Shape3.setTextureSize(36, 17);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, 0F, 4, 4, 4);
      Shape4.setRotationPoint(-2F, 8F, -2F);
      Shape4.setTextureSize(36, 17);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(0F, 0F, 0F, 2, 13, 4);
      Shape5.setRotationPoint(-2F, 8F, -2F);
      Shape5.setTextureSize(36, 17);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0.4014257F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, 0F, 0F, 2, 10, 4);
      Shape6.setRotationPoint(0F, 12F, -2F);
      Shape6.setTextureSize(36, 17);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, -0.4014257F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    Shape2.render(f5);
    Shape1.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
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
