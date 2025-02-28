package views;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;

public class InformationPanel extends JLabel {
	
    protected int score;
    protected int coins;
    protected int lives; 
    protected int time;

    public InformationPanel() {
        this.score = 0;
        this.coins = 0;
        this.lives = 3;
        this.time = ViewConstants.LEVEL_TIME_DURATION;
        configureFontAndBackground();
        updateInformation();
    }

    private void configureFontAndBackground() {
        setOpaque(false);               
        setOpaque(false);          
        setForeground(Color.WHITE);
        setBackground(new Color(0x6D6AFF, false)); 
        setOpaque(true);
        setFont(ViewConstants.font);
    }

    public void updateInformation() {
        String separator = "    ";
        setText("  SCORE:"+score+separator+"TIME:"+time+separator+"COINS:"+coins+separator+"LIVES:"+lives);   
        int panelHeight = (int) (ViewConstants.PANEL_HEIGHT * 0.075);
        int panelWidth = ViewConstants.PANEL_WITDH;        
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBounds(0, 0, panelWidth, panelHeight);         
        revalidate();
        repaint();
    }

    public void updateScore(int score) {
        this.score = score;
    }
    
    public void updateCoins(int coins) {
        this.coins = coins;
    }
    
    public void updateLives(int lives) {
        this.lives = lives;
    }
    
    public void updateTime(int time){
        this.time = time;
    }
    
    
}
