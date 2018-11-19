
import colorfull.*;
import points.*;
abstract class VisibleObject
{
  protected OpticalProp opticalProp;
  
  VisibleObject(OpticalProp prop)
  {
    this.opticalProp = prop;
  }
  
  public final boolean IsLuminous()
  {
    return this.opticalProp.IsLuminous();
  }
  
  public final OpticalProp OpticalProperty()
  {
    return this.opticalProp;
  }
  
  public final color GetColor()
  {
    return this.opticalProp.GetColor();
  }
  
  public abstract Vector3 Normal(Point3 paramPoint3);
  
  public abstract boolean Intersect(Ray paramRay);
  
  public color GetPixel(Point3 intersect)
  {
    return this.opticalProp.GetColor();
  }
  
  public abstract Shader CreateShader(Ray paramRay);
}
