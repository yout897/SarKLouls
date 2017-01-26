/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author aldic2547
 */
public class Enemy{
    
    public int x,y;
    int health,damage;
    boolean alive,right,left;
    int[] damNum = new int[5];
    
    Enemy(int health,boolean alive,int damage){
        this.health = health;
        this.alive = alive;
        this.damage = damage;
        int rand = 0;
        for(int i = 0;i < damage;i++){
            rand = (int)(Math.random()*12);
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
        this.health = health - d;
    }
    public void render(Graphics2D g2d,Image iL,Image iR,int height,int width){
        if(left)
            g2d.drawImage(iL, x + 1, y + 1,width,height, null);
        else if(right)
            g2d.drawImage(iR, x + 1, y + 1,width,height, null);
    }
    
    public void moveE(){
        int pX = GamePanel.pl.x,eX = this.x;
        
        if(eX > pX + 150){
            right = false;
            setX(getX() - 1);
            left = true;
        }
        if(eX < pX - 150){
            left = false;
            setX(getX() + 1);
            right = true;
        }
    }
    
    public void swing(int pHealth,Entity e){
        int pX = GamePanel.pl.x,eX = this.x;
        int rand = 0;
        
        if(eX > pX + 150){
            rand = (int)(Math.random()*5);
            pHealth = pHealth - damNum[rand];
            e.damage(pHealth);
        }
        else if(eX < pX - 150){
            rand = (int)(Math.random()*5);
            pHealth = pHealth - damNum[rand];
            e.damage(pHealth);
        }
    }
}