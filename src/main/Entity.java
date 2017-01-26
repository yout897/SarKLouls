/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Graphics2D;
import java.awt.Image;

public class Entity {
    
    public int x,y;
    int health,damage;
    boolean alive;
    int[] damNum = new int[100];
    
    public Entity(int health,boolean alive,int damage){
        this.health = health;
        this.alive = alive;
        this.damage = damage;
        for(int i = 0;i < damage;i++){
            damNum[i] = i;
        }
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
    public boolean alive(){
        return alive;
    }
    public void damage(int d){
        this.health -= d;
    }
    public void render(Graphics2D g2d,Image i,int height,int width){
        g2d.drawImage(i, x + 1, y + 1,width,height, null);
    }
    public void swing(int eHealth,Enemy e){
        int pX = GamePanel.pl.x,eX = this.x;
        int rand = 0;
        
        rand = (int)(Math.random()*100);
        eHealth = eHealth - damNum[rand];
        e.damage(eHealth);
    }
}