
package sta;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class STA_Design {
    
    //frame size
    public static int frameWidth = 1561;
    public static int frameHeight = 876;
    
      //setIcon at button based on the given size 
        public void scaleIcon(JButton jbutton, String iconPath, int width, int height){
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jbutton.setIcon(scaledIcon);
        jbutton.setIconTextGap(10);
    }
}
