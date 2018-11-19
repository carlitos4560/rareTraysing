package colorfull;



public class color
{
  static final double normFactor = 0.00392156862745098D;
  double red;
  double green;
  double blue;
  
  public color()
  {
    Init(0.0D, 0.0D, 0.0D);
  }
  
  public color(double red, double green, double blue)
  {
    Init(red, green, blue);
  }
  
  public color(color c)
  {
    Init(c);
  }
  
  public color(int rgb)
  {
    Init((rgb >> 16 & 0xFF) * 0.00392156862745098D, (rgb >> 8 & 0xFF) * 0.00392156862745098D, (rgb & 0xFF) * 0.00392156862745098D);
  }
  
  public final void Init(color c)
  {
    Init(c.red, c.green, c.blue);
  }
  
  public final void Init(double red, double green, double blue)
  {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  public final void AttenuateBy(double a)
  {
    this.red *= a;
    this.green *= a;
    this.blue *= a;
  }
  
  public final void AttenuateBy(color c)
  {
    this.red *= c.red;
    this.green *= c.green;
    this.blue *= c.blue;
  }
  
  public final void CombineWith(color c)
  {
    this.red += c.red;
    this.green += c.green;
    this.blue += c.blue;
  }
  
  public final int Rgb()
  {
    int r = (int)Math.ceil(this.red * 255.0D);
    int g = (int)Math.ceil(this.green * 255.0D);
    int b = (int)Math.ceil(this.blue * 255.0D);
    
    return 0xFF000000 | (r > 255 ? 255 : r) << 16 | (g > 255 ? 255 : g) << 8 | (b > 255 ? 255 : b);
  }
}
