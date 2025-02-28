package entities.state.question;
import factories.Sprite;
import observer.EntityObserver;
import observer.SoundObserver;
public class WithCoin extends QuestionState{

	public WithCoin(Sprite sprite) {
		super(3,sprite);		
	}

	public int damaged(SoundObserver soundObserver) {
		hits--;
		soundObserver.reproduceSound("coin");
		((EntityObserver)question.getGraphicObserver()).spawnCoin(question.coinAnimationSprites.get("1"));
		if(hits==0)
		   question.changeEmptyQuestionState();
		return powerUpOn.getPoints();
	}

}
