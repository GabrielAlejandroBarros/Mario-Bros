package entities.platforms;
import entities.character.CharacterVisitor;
import factories.Sprite;
import observer.GraphicObserver;

public class MastEnd extends Platform{
    static final private boolean isBreakeable = false;

    public MastEnd(Sprite sprite, int positionInX, int positionInY) {
        super(sprite, positionInX, positionInY, isBreakeable);
    }
    public void acceptVisit(CharacterVisitor visitor) {
        visitor.visit(this);
    }
    public void setObserver(GraphicObserver observer) {
        this.observer = observer;
    }
}

