/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Graphics2D;
import java.awt.Image;


public class Enemy extends Entity{

    int health,damage;
    boolean alive;
    
    public Enemy(int size, int size2,int health,boolean alive,int damage) {
        super(size, size2);
        this.health = health;
        this.alive = alive;
        this.damage = damage;
    }
    
    public void move(){
        int pX = Player.getX();
        int pY = Player.getY();
        
        while(Enemy.getX() > pX)
            Enemy.x--;
        while(Enemy.getY() > pY)
            Enemy.y--;
        while(Enemy.getX() < pX)
            Enemy.x++;
        while(Enemy.getY() < pY)
            Enemy.y++;
    }
//    @Override
//    public void render(Graphics2D g2d,Image i){
//        g2d.drawImage(i, Enemy.x + 1, Enemy.y + 1, null);
//    }
}
