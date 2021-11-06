package me.giverplay.grape;

public class Game {

  private final Loop loop;

  public Game() {
    this.loop = new Loop(60, this::tick);
  }

  public void start() {
    loop.start();
  }

  public void stop() {
    loop.stop();
  }

  public void update() {

  }

  public void draw() {

  }

  private void tick() {
    this.update();
    this.draw();
  }
}
