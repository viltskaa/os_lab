import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Graphic extends JFrame {
  private final Color[] colors = new Color[]{
      Color.BLUE,
      Color.DARK_GRAY,
      Color.GREEN,
      Color.MAGENTA,
      Color.PINK
  };
  private final Scheduler scheduler = new Scheduler();

  public Graphic() {
    setBounds(100, 100, 1500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setResizable(false);
    setVisible(true);

    //System.out.println(scheduler.Information());
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    ArrayList<Thread> tray = scheduler.getThreadsTray();
    int height = getHeight() / (tray.size() + 1),
        X = 0,
        Y = getHeight() - height;
    double bufferedWidth = ((double)getWidth() / scheduler.GetAllTime());

    for (Thread object : tray) {
      scheduler.GetProcessByName(object.getProcessName()).ExecuteThread(object);
      graphics.setColor(
          colors[scheduler.getIndexProcessByName(object.getProcessName())]
      );
      double width = Math.floor(bufferedWidth * object.getTime());
      graphics.fillRect(
          X,
          Y,
          (int)width,
          height
      );
      X += width;
      Y -= height;
    }
  }
}
