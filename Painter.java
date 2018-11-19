import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

class Painter
  implements ImageProducer, Runnable
{
  public static Escena escena;
  Thread thread;
  RayTracerEngine engine;
  ImageConsumer consumer;
  int[] imagePixels;
  int linesConsumed;
  int linesProduced;
  int maxX;
  int maxY;
  
  public Painter(int MaxX, int MaxY, Image tile)
  {
    escena = new Escena(tile);
    this.engine = new RayTracerEngine();
    this.linesProduced = 0;
    this.linesConsumed = 0;
    this.maxX = MaxX;
    this.maxY = MaxY;
    this.imagePixels = new int[MaxX * MaxY];
    this.thread = new Thread(this, "Ray Tracing");
    this.thread.start();
  }
  
  public void run()
  {
    for (int y = 0; y < this.maxY; y++)
    {
      this.engine.DoLine(y, this.imagePixels, y * this.maxX, this.maxX);
      synchronized (this)
      {
        this.linesProduced += 1;
        if (this.consumer != null)
        {
          this.consumer.setPixels(0, this.linesConsumed, this.maxX, this.linesProduced - this.linesConsumed, 
            ColorModel.getRGBdefault(), this.imagePixels, this.linesConsumed * this.maxX, this.maxX);
          
          this.linesConsumed = this.linesProduced;
          this.consumer.imageComplete(2);
        }
      }
    }
    synchronized (this)
    {
      if (this.consumer != null) {
        this.consumer.imageComplete(3);
      }
    }
  }
  
  public synchronized void addConsumer(ImageConsumer ic)
  {
    this.consumer = ic;
    this.consumer.setColorModel(ColorModel.getRGBdefault());
    this.consumer.setDimensions(this.maxX, this.maxY);
    this.consumer.setHints(6);
  }
  
  public synchronized boolean isConsumer(ImageConsumer ic)
  {
    return ic == this.consumer;
  }
  
  public synchronized void removeConsumer(ImageConsumer ic)
  {
    this.consumer = null;
  }
  
  public synchronized void requestTopDownLeftRightResend(ImageConsumer ic)
  {
    this.consumer.setPixels(0, 0, this.maxX, this.linesProduced, ColorModel.getRGBdefault(), this.imagePixels, 0, this.maxX);
    
    this.linesConsumed = this.linesProduced;
    if (this.linesProduced == this.maxY) {
      this.consumer.imageComplete(3);
    }
  }
  
  public synchronized void startProduction(ImageConsumer ic)
  {
    addConsumer(ic);
    if (this.linesProduced == this.maxY) {
      requestTopDownLeftRightResend(this.consumer);
    }
  }
}
