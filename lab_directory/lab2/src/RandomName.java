import java.util.Random;

public class RandomName {
  private static final Random random = new Random(3739812);
  public static String GetName() {
    String name = "";
    for (int i = 0; i < 16; i++) name += Character.toString(
        (char) random.nextInt(97, 123)
    );
    return name;
  }
}
