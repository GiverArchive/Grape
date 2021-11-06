package me.giverplay.grape;

public class Loop implements Runnable {

  private final Runnable callback;
  private final int tps;

  private Thread thread;

  private volatile boolean isRunning;
  private int currentTps = 0;

  public Loop(int tps, Runnable callback) {
    this.tps = tps;
    this.callback = callback;
  }

  @Override
  public void run() {
    double nsPerTick = 1_000_000_000.0 / tps;
    double unprocessed = 0;

    long timer = System.currentTimeMillis();
    long lastTime = System.nanoTime();
    long now;

    int updates = 0;

    while(isRunning) {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;

      while (unprocessed >= 1) {
        callback.run();
        ++updates;
        --unprocessed;
      }

      if(System.currentTimeMillis() - timer >= 1000) {
        currentTps = updates;
        updates = 0;
        timer = 0;
      }

      try {
        Thread.sleep(1);
      } catch (InterruptedException ignore) { }
    }
  }

  public synchronized void start() {
    isRunning = true;
    thread = new Thread(this, "Loop");
    thread.start();
  }

  public synchronized void stop() {
    isRunning = false;

    new Thread(() -> {
      try {
        thread.join();
      } catch (InterruptedException ignored) { }
    }, "Stop Loop").start();

    thread = null;
  }

  public synchronized boolean isRunning() {
    return isRunning;
  }

  public int getCurrentTps() {
    return currentTps;
  }
}
