/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 *
 * @author aldic2547
 */
public class Player extends Entity{

    int health,damage;
    boolean alive;
    
    public Player(int size, int size2,int health,boolean alive,int damage) {
        super(size, size2);
        this.health = health;
        this.alive = alive;
        this.damage = damage;
    }
}
