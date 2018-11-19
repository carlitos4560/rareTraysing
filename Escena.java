import java.awt.Image;
import java.awt.image.PixelGrabber;
import colorfull.*;
import points.*;
import colorfull.OpticalProp;
class Escena
{
  static final int MAX_OBJ = 6;
  Point3 viewPoint;
  Point3 lightPoint;
  color ambient;
  int maxDepth;
  VisibleObject[] objects;
  int objCount;
  
  public Escena(Image fondo)
  {
    this.maxDepth = 4;
    this.objects = new VisibleObject[6];
    this.objCount = 0;
    this.viewPoint = new Point3(250.0D, 150.0D, 600.0D);
    this.lightPoint = new Point3(-500.0D, -100.0D, 200.0D);
    this.ambient = new color(0.2D, 0.2D, 0.2D);

    AddSphere1();
    AddSphere2();
    AddSphere3();
    AddSphere4();
    AddLight();
  }
  void AddSphere1()
  {
    Point3 center = new Point3(359.0D, 149.0D, -300.0D);
    double radius = 200.0D;
    
    color c = new color(1.0D, 1.0D, 1.0D);
    double refr = 1.0D;
    double refl = 1.0D;
    double diffusion = 0.0D;
    OpticalProp prop = new OpticalProp(c, refr, refl, diffusion);
    this.objects[(this.objCount++)] = new Sphere(center, radius, prop);
  }
  
  void AddSphere2()
  {
    Point3 center = new Point3(150.0D, 310.0D, -108.0D);
    double radius = 84.0D;
    
    color c = new color(0.4D, 0.95D, 0.3D);
    double refr = 0.0D;
    double refl = 0.5D;
    double diffusion = 0.4D;
    
    OpticalProp prop = new OpticalProp(c, refr, refl, diffusion);
    this.objects[(this.objCount++)] = new Sphere(center, radius, prop);
  }
  
  void AddSphere3()
  {
    Point3 center = new Point3(50.0D, 120.0D, -208.0D);
    double radius = 110.0D;
    
    color c = new color(0.4D, 0.0D, 0.3D);
    double refr = 0.0D;
    double refl = 0.5D;
    double diffusion = 0.4D;
    
    OpticalProp prop = new OpticalProp(c, refr, refl, diffusion);
    this.objects[(this.objCount++)] = new Sphere(center, radius, prop);
  }
  void AddSphere4()
  {
    Point3 center = new Point3(300.0D, 120.0D, -80.0D);
    double radius = 20.0D;
    
    color c = new color(4.D, 0.D, 0.D);
    double refr = 0.0D;
    double refl = 0.5D;
    double diffusion = 0.4D;
    
    OpticalProp prop = new OpticalProp(c, refr, refl, diffusion);
    this.objects[(this.objCount++)] = new Sphere(center, radius, prop);
  }
  
  void AddLight()
  {
    Point3 center = new Point3(this.lightPoint);
    double radius = 120.0D;
    
    color c = new color(1.1D, 1.1D, 0.9D);
    double refr = 0.0D;
    double refl = 0.5D;
    double diffusion = 0.5D;
    
    OpticalProp prop = new OpticalProp(c, refr, refl, diffusion);
    prop.MakeLuminous();
    this.objects[(this.objCount++)] = new Sphere(center, radius, prop);
  }
  
  public final int MaxDepth()
  {
    return this.maxDepth;
  }
  
  public final color Ambient()
  {
    return this.ambient;
  }
  
  public final int ObjectCount()
  {
    return this.objCount;
  }
  
  public final VisibleObject GetObject(int i)
  {
    return this.objects[i];
  }
  
  public final Point3 ViewPoint()
  {
    return this.viewPoint;
  }
  
  public final Point3 LightPoint()
  {
    return this.lightPoint;
  }
}
