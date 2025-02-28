package entities.state.question;
import java.util.HashMap;
import entities.character.CharacterVisitor;
import entities.platforms.Platform;
import entities.powerUps.Coin;
import entities.powerUps.PowerUp;
import factories.Sprite;
import observer.SoundObserver;

public class Question extends Platform {

	private static final boolean isBreakeable = false;
	
	protected QuestionState actualState;
	protected HashMap<String,QuestionState> questionStates;
	protected HashMap<String,Sprite> coinAnimationSprites;
	
	public Question(Sprite sprite, int positionInX, int positionInY,  HashMap<String,Sprite> coinAnimation) {
		super(sprite, positionInX, positionInY, isBreakeable);
		this.coinAnimationSprites = coinAnimation;
		this.actualState =null;
	}

	protected void changeEmptyQuestionState(){
		actualState= questionStates.get("EmptyQuestion");
		sprite= actualState.getSprite();
		observer.update();
	}

	public void setStates(HashMap<String,QuestionState> states){
		questionStates = states;
	}

	public void setPowerUp(PowerUp powerUp){
		actualState= questionStates.get("WithOtherPowerUp");
		actualState.setPowerUp(powerUp);
	}
	
	public void setPowerUp(Coin coin){
		actualState= questionStates.get("WithCoin");
		actualState.setPowerUp(coin);
	}
	
	public int damage(SoundObserver soundObserver){
		return actualState.damaged(soundObserver); 
	}

    public boolean isEmpty(){
        return actualState.isEmpty();
	}

	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }
	
	public PowerUp getPowerUp() {
		return actualState.getPowerUp();
	}
}
