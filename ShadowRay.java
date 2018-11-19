import colorfull.color;
import points.*;
class ShadowRay
  extends Ray
{
  double attenuation;
  
  ShadowRay(Point3 origin, Vector3 dir, int depth)
  {
    super(origin, dir, depth);
    this.attenuation = 1.0D;
  }
  
  public final double Attenuation()
  {
    return this.attenuation;
  }
  
  public void Record(double distance, VisibleObject object)
  {
    if ((distance < this.dist) && (distance > 0.001D)) {
      if (object.OpticalProperty().Transparency() != 0.0D)
      {
        this.attenuation *= object.OpticalProperty().Transparency();
      }
      else
      {
        this.dist = distance;
        this.object = object;
      }
    }
  }
  
  void DoShade(color c)
  {
    if ((this.object != null) && (this.object.IsLuminous())) {
      c.Init(this.object.GetColor());
    } else {
      c.Init(0.0D, 0.0D, 0.0D);
    }
  }
}
