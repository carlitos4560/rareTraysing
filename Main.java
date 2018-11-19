import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.io.PrintStream;

public class Main
  extends Frame
{
  static final int MaxX = 640;//640
  static final int MaxY = 480;//480
  Image logo;
  Image picture;
  Painter painter;
 
  boolean logoNotFound;
  boolean logoLoaded;
  Thread watcher;
  
  public Main()
  {
    super("Trazado de esferas");
    
    constructor();
    init();
  }
  
  public void init()
  {
        this.painter = new Painter(640, 480, this.logo);//640 480 frame dibujable
    this.picture = createImage(this.painter);

    prepareImage(this.picture, this);
  }
  
  public void paint(Graphics g)
  {
    update(g);
  }
  
  public void update(Graphics g)
  {
    g.drawImage(this.picture, 0, 0, this);
  }
  
  public boolean imageUpdate(Image img, int status, int x, int y, int width, int height)
  {
    if ((status & 0x30) != 0) {
      repaint();
    }
    return true;
  }
  
  public static void main(String[] args)
  {
    Main app = new Main();
    app.show();

  }
  
  private final void constructor()
  {
    setResizable(false);
    setCursor(0);
    setLayout(null);
    addNotify();
    resize(insets().left + insets().right + 650, insets().top + insets().bottom + 480);
  }
  
  private final boolean SuperCedeEvent(Event event)
  {
    if ((event.target == this) && (event.id == 201))
    {
      System.exit(0);
      return true;
    }
    return super.handleEvent(event);
  }
  
  private final void SuperCedeStart() {}
  
  private final void SuperCedeStop() {}
}
