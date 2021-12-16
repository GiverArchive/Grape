package me.giverplay.grape;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

  public static final int SCREEN_WIDTH = 720;
  public static final int SCREEN_HEIGHT = 460;

  private final List<Grape> grapes = new ArrayList<>();

  private final Canvas canvas;
  private final Loop loop;

  public Game() {
    loop = new Loop(60, this::tick);
    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

    JFrame frame = new JFrame("Grape");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.add(canvas);
    frame.pack();
    frame.setLocationRelativeTo(null);

    canvas.createBufferStrategy(3);

    frame.setVisible(true);
  }

  public void start() {
    Random random = new Random();
    int limit = random.nextInt(100);

    for(int i = 0; i < limit; i++) {
      grapes.add(new Grape(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
    }

    loop.start();
  }

  public void stop() {
    loop.stop();
  }

  public void update() {
    grapes.forEach(Grape::tick);
  }

  public void draw() {
    Graphics graphics = canvas.getBufferStrategy().getDrawGraphics();

    graphics.setColor(new Color(0xB1B1B1));
    graphics.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    grapes.forEach(grape -> grape.render(graphics));

    graphics.dispose();

    canvas.getBufferStrategy().show();
  }

  private void tick() {
    this.update();
    this.draw();
  }

  public Loop getLoop() {
    return loop;
  }
}
