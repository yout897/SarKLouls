/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author aldic2547
 */
public class Stage {
    
    public Image back;
    public File bk;
    public int WIDTH,HEIGHT;
    
    Stage(int WIDTH,int HEIGHT, File back){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.bk = back;
        
        
    }
    
    public void set(){
        try {
            back = ImageIO.read(bk);
        } catch (IOException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
        back = back.getScaledInstance(WIDTH, HEIGHT, 1);
    }
    
    public void render(Graphics2D g){
        g.drawImage(back, 0, 0, null);
    }
}
