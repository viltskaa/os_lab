import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Process {
  private final String name;
  private int time;
  private final ArrayList<Thread> threads;

  private short state = 0;

  public Process() {
    this.name = RandomName.GetName();
    threads = new ArrayList<>();
    for (int i = 0; i < (new Random()).nextInt(2, 10); i++)
      AddThread(new Thread());
    Sort();
  }

  public int GetLength() {
    return threads.size();
  }

  public Thread GetThreadByIndex(int i) {
    return threads.get(i);
  }

  public int GetTime() {
    return time;
  }

  public short GetState() {
    return state;
  }

  public String GetName() {
    return name;
  }

  public void AddThread(Thread item) {
    threads.add(item);
    CheckThreads();
  }

  public void CheckThreads() {
    if (threads.size() == 0) return;
    time = threads.stream().mapToInt(Thread::getTime).sum();
  }

  private void Sort() {
    if (threads.size() <= 1) return;

    for (int i = 0; i < threads.size() - 1; i++) {
      for (int j = i + 1; j < threads.size(); j++) {
        if (threads.get(i).getTime() > threads.get(j).getTime()) {
          Collections.swap(threads, i, j);
        }
        else {
          if (threads.get(i).getTime() == threads.get(j).getTime()) {
            if (threads.get(i).getFiber().Size() > threads.get(j).getFiber().Size()) {
              Collections.swap(threads, i, j);
            }
          }
        }
      }
    }
  }

  public void ExecuteAll() {
    Sort();
    Exec();
  }

  public void Exec() {
    state = 1;
    for (Thread thread : threads) {
      thread.setState(true);
    }
    state = 2;
  }

  public String ToString() {
    String result = "";
    for (Thread thread : threads) {
      result += String.format("| %s | %d ms | %d |\n", thread.getName(), thread.getTime(), thread.getFiber().Size());
    }
    return result;
  }

  public String Information() {
    String result = "";
    for (Thread thread : threads) {
      result += String.format("    ↳| thread %s | %d ms | %d |\n", thread.getName(), thread.getTime(), thread.getFiber().Size());
      for (int i = 0; i < thread.getFiber().Size(); i++) {
        result += String.format("        ↳| fiber <%S> |\n", thread.getFiber().GetByIndex(i));
      }
    }
    return result;
  }
}
