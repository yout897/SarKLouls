/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Entity {
    
    private int x,y,size,size2;
    
    public Entity(int size,int size2){
        this.size = size;
        this.size2 = size2;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setPos(int x , int y){
        this.x = x;
        this.y = y;
    }
    public void move(int x, int y){
        x += x;
        y += y;
    }
    public Rectangle getBound(){
        return new Rectangle(x,y,size,size2);
    }
    public boolean colliding(Entity e){
        if(e == this)return false;
        return getBound().intersects(e.getBound());
    }
    public void render(Graphics2D g2d,Image i){
        //g2d.fillRect(x + 1, y + 1, size - 2, size2 - 2);
        g2d.drawImage(i, x + 1, y + 1,50,150, null);
    }
}