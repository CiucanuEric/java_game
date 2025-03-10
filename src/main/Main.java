package main;

import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {

        JFrame window= new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Tales of the Wars");
        window.setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}