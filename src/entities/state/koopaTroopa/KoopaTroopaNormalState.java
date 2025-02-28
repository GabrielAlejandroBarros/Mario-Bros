package entities.state.koopaTroopa;

import entities.character.Character;
import entities.enemies.Enemy;
import entities.enemies.KoopaTroopa;
import tools.GraphicTools;

public class KoopaTroopaNormalState implements KoopaTroopaState {
	
    protected KoopaTroopa koopaTroopa;

    public KoopaTroopaNormalState(KoopaTroopa koopaTroopa){
        this.koopaTroopa = koopaTroopa;
    }

	public void move(int frame){
		switch (koopaTroopa.getDirection()) {
            case "Left":
                moveLeft(frame);
                break;
            case "Right":
                moveRight(frame);
                break;
            case "None":
            	break;
        }
	}
	
	private void moveLeft(int frame) {
		float enemyX = koopaTroopa.getX();
		koopaTroopa.setX(GraphicTools.round2Digits(enemyX - koopaTroopa.getHorizontalSpeed()));
		koopaTroopa.setSprite(koopaTroopa.getSpritesMap().get("Left" + frame));
		koopaTroopa.getObserver().update();
	}

	private void moveRight(int frame) {
		float enemyX = koopaTroopa.getX();
		koopaTroopa.setX(GraphicTools.round2Digits(enemyX + koopaTroopa.getHorizontalSpeed()));
		koopaTroopa.setSprite(koopaTroopa.getSpritesMap().get("Right" + frame));
		koopaTroopa.getObserver().update();
	}

	public void hit(Character character){
		koopaTroopa.shellMode();
	}

	public void hitEnemy(Enemy enemy) {
		String newDirection= koopaTroopa.getDirection()=="Left" ? "Right" : "Left";
		koopaTroopa.setDirection(newDirection);
	} 
}
