import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Scheduler {
  private final ArrayList<Process> processes;

  public Scheduler() {
    processes = new ArrayList<>();
    for (int i = 0; i < (new Random()).nextInt(3, 5); i++) {
      AddProcess(new Process());
    }
    Sort();
  }

  public int Length() {
    return processes.size();
  }

  public int GetAllTime() {
    int timeAll = 0;
    for (Process pr:
         processes) {
      timeAll += pr.GetTime();
    }
    return timeAll;
  }

  public Process GetProcessByIndex(int i) {
    if (i < processes.size())
      return processes.get(i);
    return null;
  }

  public Process GetProcessByName(String name) {
    for (Process process : processes) {
      if (process.GetName().equals(name)) return process;
    }
    return null;
  }

  public void AddProcess(Process item) {
    processes.add(item);
  }

  public void Sort() {
    if (processes.size() <= 1) return;
    for (int i = 0; i < processes.size() - 1; i++) {
      for (int j = i + 1; j < processes.size(); j++) {
        if (processes.get(i).GetTime() > processes.get(j).GetTime()) {
          Collections.swap(processes, i, j);
        }
      }
    }
  }

  public ArrayList<Thread> getThreadsTray() {
    ArrayList<Thread> threads = new ArrayList<>();
    for (Process pr:
         processes) {
      for (int j = 0; j < pr.GetLength(); j++) {
        threads.add(pr.GetThreadByIndex(j));
      }
    }

    for (int i = 0; i < threads.size() - 1; i++) {
      for (int j = i + 1; j < threads.size(); j++) {
        if (threads.get(i).getTime() > threads.get(j).getTime()) {
          Collections.swap(threads, i, j);
        }
      }
    }

    return threads;
  }

  public int getIndexProcessByName(String name) {
    for (int i = 0; i < processes.size(); i++) {
      if (processes.get(i).GetName().equals(name)) return i;
    }
    return -1;
  }

  public String ToString() {
    String result = "";
    for (Process process:
         processes) {
      result += String.format("| %s | %03d ms | state %d |\n", process.GetName(), process.GetTime(), process.GetState());
    }
    return result;
  }

  public String Information() {
    String result = "";
    for (Process process:
        processes) {
      result += String.format("| process %s | %d ms| state %d |\n", process.GetName(), process.GetTime(), process.GetState());
      result += process.Information();
    }
    return result;
  }
}
