import java.util.Random;

public class Thread {
  private final String name;
  private final int time;
  private final Fiber fiber = new Fiber();
  private boolean state = false;
  private final String processName;

  public Thread(String procName) {
    name = RandomName.GetName();
    fiber.Generate();
    Random random = new Random();
    time = random.nextInt(10, 200);
    processName = procName;
  }
  public String getProcessName() {
    return processName;
  }

  public String getName() {
    return name;
  }

  public int getTime() {
    return time;
  }

  public Fiber getFiber() {
    return fiber;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }
}
