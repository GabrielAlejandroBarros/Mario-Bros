package tools;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import views.ViewConstants;

public class GraphicTools {
	
    // Relative position methods
    
    public static int transformX(float x, JLabel observer){
        return Math.round( x * ViewConstants.CELL_SIZE );
    }

    public static int transformY(float y , JLabel observer ){
        return Math.round((ViewConstants.PANEL_HEIGHT - (85 + observer.getIcon().getIconHeight() + y * ViewConstants.CELL_SIZE)));
    }


    //Scaling methods

    public static ImageIcon scaleEntityImage(ImageIcon imageIcon) {
    	Image image=imageIcon.getImage();
    	int width= imageIcon.getIconWidth();
    	int height=imageIcon.getIconHeight();
    	image=image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	return new ImageIcon(image);
    }
    
    public static ImageIcon scaleImage(float origin, float destination, ImageIcon imageIcon){
        float scale = calculateScale(origin, destination);
        Image image = imageIcon.getImage();
        int width = Math.round(imageIcon.getIconWidth() * scale);
        int height = Math.round(imageIcon.getIconHeight() * scale);
        image = image.getScaledInstance(width,  height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private static float calculateScale(float origin, float destination){
        return destination / origin;
    }

	public static float round2Digits(float number){
		return Math.round(number * 100.0) / 100.0f;
	}

    public static float roundInt(float number){
       return Math.round(number) / 1.0f;
    }
    
}
