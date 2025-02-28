package entities.state.question;
import factories.Sprite;
import observer.SoundObserver;

public class QuestionBlockEmpty extends QuestionState{

	public QuestionBlockEmpty(Sprite newSprite) {
		super(0, newSprite);
	}

	public int damaged(SoundObserver soundObserver) {
		return 0;
	}

}
