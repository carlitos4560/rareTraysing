
import colorfull.*;
import points.*;
class Sphere
  extends VisibleObject
{
  Point3 center;
  double radius;
  double r2;
  
  Sphere(Point3 center, double radius, OpticalProp pr)
  {
    super(pr);
    this.center = center;
    this.radius = radius;
    this.r2 = (radius * radius);
  }
  
  public final Point3 Center()
  {
    return this.center;
  }
  
  public Vector3 Normal(Point3 intersect)
  {
    Vector3 result = new Vector3(intersect, this.center);
    result.Normalize();
    return result;
  }
  
  public boolean Intersect(Ray ray)
  {
    Vector3 oc = new Vector3(this.center, ray.Origin());
    
    double oc2 = oc.dotProduct(oc);
    double dist2 = oc2 - this.r2;
    if ((dist2 <= 0.001D) && (dist2 >= -0.001D)) {
      return false;
    }
    double tca = oc.dotProduct(ray.Direction());
    if ((dist2 > 0.001D) && (tca < 0.0D)) {
      return false;
    }
    double hc2 = this.r2 - oc2 + tca * tca;
    if (hc2 < 0.0D) {
      return false;
    }
    if (dist2 > 0.001D) {
      ray.Record(tca - Math.sqrt(hc2), this);
    } else {
      ray.Record(tca + Math.sqrt(hc2), this);
    }
    return true;
  }
  
  public Shader CreateShader(Ray ray)
  {
    return new PhongShader(ray, this);
  }
}
