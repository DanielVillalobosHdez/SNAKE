package grupod.snake.gui.menus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class  MainMenu extends JPanel {

    private ImageIcon titleImage;

    public void paint(Graphics g)
    {
        // draw border
        g.setColor(Color.PINK);
        g.drawRect(24, 10, 851, 55);

        //draw title
        titleImage = new ImageIcon(getClass().getClassLoader().getResource("sprites/snake-graphics.png"));


    }
}
