package entities.state.spiny;
import entities.enemies.Spiny;
import tools.GraphicTools;
import views.ViewConstants;
public class SpinyNormalState extends SpinyState {   
	public SpinyNormalState(Spiny spiny){
        super(spiny);
    }

    public void moveRight(int frame) {
		float enemyX = spiny.getX();
		spiny.setX(GraphicTools.round2Digits(enemyX + spiny.getHorizontalSpeed()));
		spiny.setSprite(spiny.getSpritesMap().get("Right" + frame));
		spiny.getObserver().update();
	}

	public void moveLeft(int frame) {
		float enemyX = spiny.getX();
		spiny.setX(GraphicTools.round2Digits(enemyX - spiny.getHorizontalSpeed()));
		spiny.setSprite(spiny.getSpritesMap().get("Left" + frame));
		spiny.getObserver().update();
	}

	public void applyGravity() {
		if (spiny.isInAir() && !spiny.getFlies()){ 
			spiny.setVerticalSpeed(spiny.getVerticalSpeed() + ViewConstants.WORLD_GRAVITY);
			if(spiny.getVerticalSpeed() <= ViewConstants.MAX_FALL_SPEED){
				spiny.setVerticalSpeed( ViewConstants.MAX_FALL_SPEED);
			}
			float worldY = spiny.getY();
			spiny.setY(worldY + (spiny.getVerticalSpeed() * 0.04f));
		}
	}

	public void visitPlatform() {
	}
    
}