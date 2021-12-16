package me.giverplay.grape;

import java.awt.Color;
import java.awt.Graphics;

public class Grape {

  private int x;
  private int y;

  public Grape(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void tick() {

  }

  public void render(Graphics graphics) {
    graphics.setColor(new Color(0x620E62));
    graphics.fillOval(x, y, 32, 32);
    graphics.setColor(new Color(0x3D093D));
    graphics.drawOval(x, y, 32, 32);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
