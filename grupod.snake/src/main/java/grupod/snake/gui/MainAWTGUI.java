package grupod.snake.gui;

import grupod.snake.Main;
import grupod.snake.gui.menus.GameMenu;
import grupod.snake.gui.menus.MainMenu;

import javax.swing.*;
import java.awt.Color;
import java.util.concurrent.TimeUnit;


public class MainAWTGUI {

    public static void main(String[] args) {
        JFrame obj = new JFrame();

        MainMenu MM= new MainMenu();
        //GameMenu GM= new GameMenu();
        obj.setBounds(10,10,905,700);
        obj.setBackground(Color.GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        obj.add(MM);
        //obj.remove(MM);
        //obj.add(GM);

    }
}
