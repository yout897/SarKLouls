/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

    //Game Loop
    private boolean running;
    private Thread thread;
    private long targetT;
    public static Image u;
    public static Image p;
    //Rendering
    private Graphics2D g2d;
    private BufferedImage image;
    //Entities
    private final int SIZE = 20;
    private Entity pl;
    //Variables
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private int dx=250, dy=250;
    private boolean grounded = true,up, down, left, right;
    
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

    //Do I even have to say what this does
    @Override
    public void keyTyped(KeyEvent e) {
        int k = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

//        if (k == KeyEvent.VK_W) {
//            
//        }
//        if (k == KeyEvent.VK_S) {
//            down = true;
//        }
        if (k == KeyEvent.VK_A) {
            left = true;
        }
        if (k == KeyEvent.VK_D) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();

//        if (k == KeyEvent.VK_W) {
//            up = false;
//        }
//        if (k == KeyEvent.VK_S) {
//            down = false;
//        }
        if (k == KeyEvent.VK_A) {
            left = false;
        }
        if (k == KeyEvent.VK_D) {
            right = false;
        }
    }

    //Game Loop
    @Override
    public void run() {
        if (running) {
            return;
        }
        initialize();
        long startT;
        long elapsed;
        long wait;
        while (running) {
            startT = System.nanoTime();
            update();
            requestRender();
            elapsed = System.nanoTime() - startT;
            wait = targetT - elapsed / 1000000;
            if (wait > 0) {
                try {
                    Thread.sleep(wait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Function to initialize anything that gets added once
    public void initialize() {
        
        URL url = getClass().getResource("back1.png");
        URL url1 = getClass().getResource("char1.png");
        
        File file = new File(url.getPath());
        File file2 = new File(url1.getPath());
        try {
            u = ImageIO.read(file);
            p = ImageIO.read(file2);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        
        running = true;

        pl = new Entity(SIZE, SIZE);
    }

    //Draws the background and places anything that needs to be rendered ontop
    private void requestRender() {
        render(g2d);
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
    }

    //Function to continuosly initialize anything that needs to be checked at all times
    private void update() {

//        if (up) {
//            dy -= 1 * 1.5;
//        }
//        if (down) {
//            dy += 1 * 0.5;
//        }
        if (left) {
            dx -= 1 * 0.5;
        }
        if (right) {
            dx += 1 * 1.5;
        }

        if (dx > WIDTH - 10) {
            dx -= 10;
        }
        if (dy > HEIGHT - 10) {
            dy -= 10;
        }
        
        pl.setPos(dx, dy);
    }

    //Rendering graphics
    public void render(Graphics2D g2d) {
            g2d.clearRect(0, 0, WIDTH, HEIGHT);
            g2d.setColor(Color.BLUE);
            g2d.drawImage(u, 0, 0,WIDTH,HEIGHT, null);
            pl.render(g2d,p);
            
    }
    
}
