package points;

public class Vector3
  extends Point3
{

  
  public Vector3(Point3 endpoint, Point3 origin)
  {
    super(endpoint.X() - origin.X(), endpoint
      .Y() - origin.Y(), endpoint
      .Z() - origin.Z());
  }
  
  public Vector3(double x, double y, double z)
  {
    super(x, y, z);
  }
  
  public Vector3(Vector3 v)
  {
    super(v.x, v.y, v.z);
  }
  
  boolean NonZero()
  {

    return (this.x != 0.0D ? true : false) | (this.y != 0.0D ? true : false) | (this.z != 0.0D ? true : false);

  }
  
  public final double Len()
  {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }
  
  public final void ScaleBy(double a)
  {
    this.x *= a;
    this.y *= a;
    this.z *= a;
  }
  
  public final double dotProduct(Vector3 v)
  {
    return this.x * v.x + this.y * v.y + this.z * v.z;
  }
  
  public final void Normalize()
  {
    double len = Len();
    if (len > 0.001D)
    {
      double invLen = 1.0D / len;
      ScaleBy(invLen);
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 0.0D;
    }
  }
}
