import colorfull.color;

import points.*;
class PhongShader
  extends Shader
{
  Ray reflectedRay;
  ShadowRay shadowRay;
  Point3 intersect;
  Vector3 normal;
  double cosine;
  double light_cos;
  
  PhongShader(Ray incident, VisibleObject obj)
  {
    super(incident, obj);
    
    this.intersect = incident.Intersect();
    this.normal = obj.Normal(this.intersect);
    this.cosine = this.normal.dotProduct(incident.Direction());
    
    Vector3 light = new Vector3(Painter.escena.LightPoint(), this.intersect);
    light.Normalize();
    this.light_cos = light.dotProduct(this.normal);
    if ((this.prop.Diffusion() > 0.0D) && (this.light_cos > 0.0D)) {
      this.shadowRay = new ShadowRay(this.intersect, light, this.depth + 1);
    }
    if (this.prop.Reflectiveness() > 0.0D)
    {
      Vector3 ref = Reflect();
      this.reflectedRay = new Ray(this.intersect, ref, this.depth + 1);
    }

  }
  
  public void GetColor(color final_color)
  {
    color diffuse = new color();
    if (this.shadowRay != null)
    {
      this.shadowRay.Hit(diffuse);
      diffuse.AttenuateBy(this.shadowRay.Attenuation());
      diffuse.AttenuateBy(this.light_cos);
    }
    diffuse.AttenuateBy(this.prop.Diffusion());
    diffuse.CombineWith(Painter.escena.Ambient());
    diffuse.AttenuateBy(this.prop.GetColor());
    final_color.Init(diffuse);
    
    color specular = new color();
    if (this.reflectedRay != null) {
      this.reflectedRay.Hit(specular);
    }
    specular.AttenuateBy(this.prop.Reflectiveness());
    final_color.CombineWith(specular);
    
    color refr = new color();
    refr.AttenuateBy(this.prop.Transparency());
    final_color.CombineWith(refr);
  }
  
  Vector3 Reflect()
  {
    Vector3 result = new Vector3(this.normal);
    result.ScaleBy(-2.0D * this.cosine);
    result.Add(this.incidentRay.Direction());
    return result;
  }
  
}
