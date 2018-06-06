package grupod.snake.gui.menus;

import grupod.snake.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class GameMenu  extends JPanel {

    private ImageIcon MainSprite;
    private ImageIcon [] SpriteHead;
    private ImageIcon [] SpriteBody;
    private ImageIcon [] SpriteTail;
    private ImageIcon [] SpriteFood;

    Image image;

     private void image_setup(){
         //draw title
        MainSprite = new ImageIcon(getClass().getClassLoader().getResource("sprites/snake-graphics.png"));
        image = MainSprite.getImage();
        image = createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0,0,100,100)));
        ImageIcon temp = new ImageIcon(image);

/*        SpriteBody[0] = new ImageIcon(
                createImage(new FilteredImageSource(image.getSource(),
                new CropImageFilter(0,0,50,50)))
        );
*/
     }

    public void paint(Graphics g)
    {
        image_setup();
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

        SpriteBody[0].paintIcon(this, g, 50,50);



    }
}
