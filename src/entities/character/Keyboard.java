package entities.character;
import java.awt.event.KeyEvent;
public class Keyboard extends java.awt.event.KeyAdapter {

    private String playerHorizontalDirection, playerVerticalDirection;
    private String previousDirection;
    private String spacebar;
    private boolean jumping;
    private boolean keyIsPressed;
    
    public Keyboard(){
        this.playerHorizontalDirection="None";
        this.playerVerticalDirection="None";
        this.spacebar = "None";
        this.previousDirection="Right";
        this.jumping = false;
        this.keyIsPressed = false;
    }
     
  
    public void keyPressed(KeyEvent pressedKey) {
    	if (pressedKey.getKeyCode() == KeyEvent.VK_W) 
                playerVerticalDirection = "Up";
        if (pressedKey.getKeyCode() == KeyEvent.VK_SPACE) 
                spacebar = "Space";
        if(!keyIsPressed){
            switch(pressedKey.getKeyCode()) {
                case KeyEvent.VK_D:
                    keyIsPressed = true;
                    playerHorizontalDirection="Right";
                    break;
                case KeyEvent.VK_A:
                	keyIsPressed = true;
                    playerHorizontalDirection="Left";
                    break;
            }
        }
    }

    public void keyReleased(KeyEvent releasedKey) {
        if(releasedKey.getKeyCode() == KeyEvent.VK_W) {
        	if(playerVerticalDirection.equals("Up") || playerVerticalDirection.equals("None"))
        		playerVerticalDirection="Down";
        }

        if(releasedKey.getKeyCode() == KeyEvent.VK_SPACE) {
        		spacebar = "None";
        }

    	if(keyIsPressed){
            switch(releasedKey.getKeyCode()) {
                case KeyEvent.VK_D:
                    previousDirection = playerHorizontalDirection;
                    playerHorizontalDirection = "None";
                    keyIsPressed = false;
                    break;
                case KeyEvent.VK_A:
                    previousDirection = playerHorizontalDirection;
                    playerHorizontalDirection = "None";
                    keyIsPressed = false;
                    break;
            }
        }
    }
    
    public String getPlayerHorizontalDirection() {
        return playerHorizontalDirection;
    }

    public String getPlayerVerticalDirection() {
    	return playerVerticalDirection;
    }
    public String getPreviousDirection() {
        return previousDirection;
    }
    
    public boolean isCharacterJumping() {
    	return jumping;
    }

    public String getSpacebar() {
        return spacebar;
    }
}
