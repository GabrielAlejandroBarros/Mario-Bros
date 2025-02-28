package entities.state.question;
import factories.Sprite;
import observer.SoundObserver;

public class WithPowerUp extends QuestionState {

	public WithPowerUp(Sprite sprite) {
		super(1, sprite);
	}

	public int damaged(SoundObserver soundObserver) {
	    hits--;
	    powerUpOn.activatePowerUp();
		soundObserver.reproduceSound("powerUpAppears");
		question.changeEmptyQuestionState();
		return 0;
	}

}
