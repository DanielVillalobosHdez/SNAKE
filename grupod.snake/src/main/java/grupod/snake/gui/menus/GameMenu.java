package grupod.snake.gui.menus;

import grupod.snake.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class GameMenu  extends JPanel {

    private ImageIcon MainSprite;
    private Image [] SpriteHead;
    private Image [] SpriteBody;
    private Image [] SpriteTail;
    private Image [] SpriteFood;

    Image image;

     private image_setup(){
         //draw title
        MainSprite = new ImageIcon(getClass().getClassLoader().getResource("sprites/snake-graphics.png"));
        image = MainSprite.getImage();
        image = createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0,0,100,100)));
        ImageIcon temp = new ImageIcon(image);

        SpriteBody[0] = new ImageIcon(createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0,0,))))

     }

    public void paint(Graphics g)
    {
        // draw border
        g.setColor(Color.GREEN);
        g.drawRect(24, 10, 851, 55);

        //draw title
        MainSprite = new ImageIcon(getClass().getClassLoader().getResource("sprites/snake-graphics.png"));
        image = MainSprite.getImage();
        image = createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0,0,100,100)));
        ImageIcon temp = new ImageIcon(image);

        temp.paintIcon(this , g, 0, 0);



    }
}
