package colorfull;


public class OpticalProp
{
  color objColor;

  double transparency;
  double reflectiveness;
  double diffusion;
  boolean isLuminous;
  
  public OpticalProp(color c, double transparency, double reflectiveness, double diffusion)
  {
    this.objColor = new color(c);

    this.transparency = transparency;
    this.reflectiveness = reflectiveness;
    this.diffusion = diffusion;
    this.isLuminous = false;
  }
  public final color GetColor()
  {
    return this.objColor;
  }
  
  public final void MakeLuminous()
  {
    this.isLuminous = true;
  }
  
  public final boolean IsLuminous()
  {
    return this.isLuminous;
  }
  
  public final double Transparency()
  {
    return this.transparency;
  }
  
  public final double Reflectiveness()
  {
    return this.reflectiveness;
  }
  
  public final double Diffusion()
  {
    return this.diffusion;
  }
}
