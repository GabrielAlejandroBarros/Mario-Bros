package entities.state.buzzyBeetle;
import entities.enemies.BuzzyBeetle;
import tools.GraphicTools;
public class BuzzyBeetleNormalState implements BuzzyBeetleState {
    protected BuzzyBeetle buzzyBeetle;

    public BuzzyBeetleNormalState(BuzzyBeetle buzzyBeetle){
        this.buzzyBeetle = buzzyBeetle;
    }

    public void moveRight(int frame) {
		float enemyX = buzzyBeetle.getX();
		buzzyBeetle.setX(GraphicTools.round2Digits(enemyX + buzzyBeetle.getHorizontalSpeed()));
		buzzyBeetle.setSprite(buzzyBeetle.getSpritesMap().get("Right" + frame));
		buzzyBeetle.getObserver().update();
	}
	
	public void moveLeft(int frame) {
		float enemyX = buzzyBeetle.getX();
		buzzyBeetle.setX(GraphicTools.round2Digits(enemyX - buzzyBeetle.getHorizontalSpeed()));
		buzzyBeetle.setSprite(buzzyBeetle.getSpritesMap().get("Left" + frame));
		buzzyBeetle.getObserver().update();
	} 

    public int hit() {
        buzzyBeetle.shellMode();
		return 0;
    }
    
}
