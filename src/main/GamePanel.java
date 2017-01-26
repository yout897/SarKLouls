/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
import java.util.TimerTask;
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
    public static Image p,pW1,pW2,chrE,chrER,pL;
    //Rendering
    private Graphics2D g2d;
    private BufferedImage image;
    //Entities
    public static Entity pl;
    private Enemy e1;
    public Stage bk1,bk2,bk3;
    //Variables
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private int dx=250, dy=250,stage = 1;
    private boolean left, right,idle = true,walking = false,rAtk,lAtk,eAttack = false,pAttack = false;
    public TimerTask timer;
    
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
        if (k == KeyEvent.VK_A) {
            left = true;
        }
        if (k == KeyEvent.VK_D) {
            right = true;
        }
        if (k == KeyEvent.VK_RIGHT) {
            rAtk = true;
        }
        if (k == KeyEvent.VK_LEFT) {
            lAtk = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_A) {
            left = false;
        }
        if (k == KeyEvent.VK_D) {
            right = false;
        }
        if (k == KeyEvent.VK_RIGHT) {
            rAtk = false;
        }
        if (k == KeyEvent.VK_LEFT) {
            lAtk = false;
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
        idle = true;
        walking = false;
        
        URL bck1 = getClass().getResource("back1.png");
        URL bck2 = getClass().getResource("back2.png");
        URL bck3 = getClass().getResource("back3.png");
        
        URL chr1 = getClass().getResource("char1.png");
        URL chr1L = getClass().getResource("char1L.png");
        URL chr1W1 = getClass().getResource("char1W1.png");
        URL chr1W2 = getClass().getResource("char1W2.png");
        URL chE = getClass().getResource("charE.png");
        URL chER = getClass().getResource("charER.png");
        
        File back1 = new File(bck1.getPath());
        File back2 = new File(bck2.getPath());
        File back3 = new File(bck3.getPath());
        
        File char1 = new File(chr1.getPath());
        File char1L = new File(chr1L.getPath());
        File char1W1 = new File(chr1W1.getPath());
        File char1W2 = new File(chr1W2.getPath());
        File charE = new File(chE.getPath());
        File charER = new File(chER.getPath());
        
        bk1 = new Stage(WIDTH,HEIGHT,back1);
        bk1.set();
        bk2 = new Stage(WIDTH,HEIGHT,back2);
        bk2.set();
        bk3 = new Stage(WIDTH,HEIGHT,back3);
        bk3.set();
        
        try {
            p = ImageIO.read(char1);
            p = p.getScaledInstance(50, 150, ERROR);
            pL = ImageIO.read(char1L);
            pL = pL.getScaledInstance(50, 150, ERROR);
            pW1 = ImageIO.read(char1W1);
            pW1 = pW1.getScaledInstance(50, 150, ERROR);
            pW2 = ImageIO.read(char1W2); 
            pW2 = pW2.getScaledInstance(50, 150, ERROR);
            chrE = ImageIO.read(charE);
            chrE = chrE.getScaledInstance(60,120,ERROR);
            chrER = ImageIO.read(charER);
            chrER = chrER.getScaledInstance(60,120,ERROR);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        
        running = true;

        pl = new Entity(100,true,5);
        e1 = new Enemy(100,true,5);
        e1.setPos(800, 330);
    }

    //Draws the background and places anything that needs to be rendered ontop
    private void requestRender() {
        render(g2d);
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
    }

    //Function to continuosly initialize anything that needs to be checked at all times
    private void update() {
        if(pl.alive == true){
            if (left) {
                dx -= 1 * 0.5;
            }
            if (right) {
                dx += 1 * 1.5;
            }
            if (dx > WIDTH - 50 && stage == 1) {
                stage = 2;
                dx = 250;
                dy = 250;
            }
            else if (dx > WIDTH - 50 && stage == 2) {
                stage = 3;
                dx = 100;
                dy = 250;
            }
            if (dx < 50 && stage == 2) {
                stage = 1;
                dx = 750;
                dy = 250;
            }
            else if (dx < 50 && stage == 3) {
                stage = 2;
                dx = 750;
                dy = 250;
            }
            if (dy > HEIGHT - 10) {
                dy -= 10;
            }           
            pl.setPos(dx, dy);
        }
    }
    
    //Rendering graphics
    public void render(Graphics2D g2d) {
        

        if(stage == 1){    
            bk1.render(g2d,0,0);
        }else if(stage == 2){
            bk2.render(g2d,0,0);
        }
        else if(stage == 3){
            bk3.render(g2d,0,0);
        }
        
        if(pl.alive == true){
            if(idle = true){
                if(right){
                    pl.render(g2d,p,150,50);
                }
                else if(!right){
                    pl.render(g2d,pL,150,50);
                }
            }
        }
        if(e1.alive && stage == 2)
            e1.render(g2d,chrE,chrER,70, 70);
    }
}
