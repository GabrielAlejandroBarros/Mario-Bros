package views;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class ViewConstants {
	
    public static final int WIN_HEIGHT = 600;
	public static final int WIN_WIDTH = 800;	
	
	public static final int PANEL_HEIGHT = 560;
	public static final int PANEL_WITDH = 800;

	public static final int CELL_SIZE = 43;

	public static final float CHARACTER_SPEED = 15/ 100.0f;
	public static final float CHARACTER_JUMP = 10.0f;
	public static final float WORLD_GRAVITY = -0.4f;
	public static final float MAX_FALL_SPEED = -15.0f;            

	public static final float ENEMY_SPEED= 5/100.0f;

	public static final float PROJECTILE_SPEED= 30/100.0f;
	
	public static final int MAP_CELLS=143;

	public static final int LEFT_CHARACTER_SPACE = 4;

    public static final int EGG_COOLDOWN = 8000;
	public static final int GAMETICK = 16;
	public static final Font font = setFont();
    public static final int MAX_LEVELS = 3;
    public static final int LEVEL_TIME_DURATION = 90;

	private static Font setFont() {
		Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src"+File.separator+"assets"+File.separator+"font"+File.separator+"smbfont.ttf"));
            font = font.deriveFont(Font.BOLD, 16f);           
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            font = new Font("Arial", Font.BOLD, 16);
        }
		return font;
    }


}
