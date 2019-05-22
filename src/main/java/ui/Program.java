package ui;

import game.Chess;

import javax.swing.*;

public class Program {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;


    public Program() {
        JFrame program = new JFrame("Chess");
        program.setVisible(true);
        program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        program.add(new Chess().getContent());
        program.setSize(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        new Program();
    }
}
