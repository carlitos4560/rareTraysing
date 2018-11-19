import colorfull.color;
import points.*;
class Ray
{
  static final double HUGE_DIST = 1000000.0D;
  Point3 origin;
  Vector3 dir;
  int depth;
  double dist;
  VisibleObject object;
  
  public Ray(Point3 origin, Vector3 dir, int depth)
  {
    this.origin = origin;
    this.dir = new Vector3(dir);
    this.dir.Normalize();
    this.depth = depth;
    this.dist = 1000000.0D;
  }
  
  public final int Depth()
  {
    return this.depth;
  }
  
  public final double Distance()
  {
    return this.dist;
  }
  
  public final Point3 Origin()
  {
    return this.origin;
  }
  
  public final Vector3 Direction()
  {
    return this.dir;
  }
  
  public final VisibleObject GetObject()
  {
    return this.object;
  }
  
  public void Hit(color shade)
  {
    for (int i = 0; i < Painter.escena.ObjectCount(); i++) {
      Painter.escena.GetObject(i).Intersect(this);
    }
    DoShade(shade);
  }
  
  public void Record(double distance, VisibleObject object)
  {
    if ((distance < this.dist) && (distance > 0.001D))
    {
      this.dist = distance;
      this.object = object;
    }
  }
  
  Point3 Intersect()
  {
    Vector3 tmp = new Vector3(this.dir);
    tmp.ScaleBy(this.dist);
    Point3 intersect = new Point3(this.origin);
    intersect.Add(tmp);
    return intersect;
  }
  
  void DoShade(color shade)
  {
    if ((this.object == null) || (this.depth > Painter.escena.MaxDepth()))
    {
      shade.Init(0.0D, 0.0D, 0.0D);
      return;
    }
    if (this.object.OpticalProperty().IsLuminous())
    {
      shade.Init(this.object.GetColor());
      return;
    }
    Shader shader = this.object.CreateShader(this);
    
    shader.GetColor(shade);
  }
}
