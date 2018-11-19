package points;

public class Point3
{
  protected double x;
  protected double y;
  protected double z;
  
  public Point3(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Point3(Point3 p)
  {
    this(p.x, p.y, p.z);
  }
  
  public final double X()
  {
    return this.x;
  }
  
  public final double Y()
  {
    return this.y;
  }
  
  public final double Z()
  {
    return this.z;
  }
  
  public final void Add(Vector3 v)
  {
    this.x += v.X();
    this.y += v.Y();
    this.z += v.Z();
  }
}
