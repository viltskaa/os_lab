import java.util.ArrayList;
import java.util.Random;

public class Fiber {
  private final ArrayList<String> stack = new ArrayList<>();

  public void Push(String item){
    stack.add(item);
  }

  public String Pop() {
    return stack.remove(stack.size() - 1);
  }

  public int Size() {
    return stack.size();
  }

  public void Generate() {
    Random random = new Random();
    int RightWall = Commands.values().length;
    for (int i = 0; i < random.nextInt(1, 3); i++) {
      Push(String.valueOf(
          Commands.values()[random.nextInt(0, RightWall)]
      ));
    }
  }

  public String toString() {
    return stack.toString();
  }

  public String GetByIndex(int i) {
    return stack.get(i);
  }
}
