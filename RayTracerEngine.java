import colorfull.color;
import points.*;
class RayTracerEngine
{
  public void DoLine(int y, int[] imagePixels, int offset, int maxX)
  {
    for (int x = 0; x < maxX; x++)
    {
      Point3 pixel = new Point3(x, y, 0.0D);
      Vector3 direction = new Vector3(pixel, Painter.escena.ViewPoint());
      Ray Root = new Ray(Painter.escena.ViewPoint(), direction, 0);
      color shade = new color();
      Root.Hit(shade);
      imagePixels[(offset + x)] = shade.Rgb();
    }
  }
}
