import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

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
    setBounds(100, 100, 1200, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setResizable(false);
    setVisible(true);

    System.out.println(scheduler.Information());
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    int count = scheduler.Length();
    int StartX = 0;
    for (int i = 0; i < count; i++) {
      Process object = scheduler.GetProcessByIndex(i);
      graphics.setColor(colors[i]);
      double width = Math.floor(((double)getWidth() / scheduler.GetAllTime()) * object.GetTime());
      graphics.fillRect(StartX, 0, (int)width, getHeight());

      Font font = graphics.getFont();
      AffineTransform fontAT = new AffineTransform();
      fontAT.rotate(Math.PI / 2);
      graphics.setColor(Color.white);
      graphics.setFont(font.deriveFont(fontAT));
      graphics.drawString(object.GetName(), StartX + 10, 40);
      graphics.setFont(font);

      int StartXforThreads = StartX;
      int countOnY = getHeight() / object.GetLength();
      graphics.setColor(Color.BLACK);
      for (int j = 0; j < object.GetLength(); j++) {
        double widthThread = Math.floor((width / object.GetTime()) * object.GetThreadByIndex(j).getTime());
        graphics.drawRect(
            StartXforThreads,
            countOnY * j,
            (int)widthThread,
            countOnY
        );
        StartXforThreads += widthThread;
      }

      StartX += width;
    }
  }
}