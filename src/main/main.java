/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author aldic2547
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("TestWindow");
        frame.setContentPane(new GamePanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(GamePanel.WIDTH,GamePanel.HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
