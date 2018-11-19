import colorfull.*;
abstract class Shader
{
  Ray incidentRay;
  OpticalProp prop;
  int depth;
  
  Shader(Ray incident, VisibleObject obj)
  {
    this.prop = obj.OpticalProperty();
    this.incidentRay = incident;
    this.depth = incident.Depth();
  }
  
  public abstract void GetColor(color paramcolor);
}
