package entities.powerUps;
import entities.Entity;
import entities.visitor.VisitedElement;
import factories.Sprite;
import observer.EntityObserver;
import observer.Observer;

public abstract class PowerUp extends Entity implements VisitedElement{

	protected int points;
	protected boolean isActive;

    public PowerUp(Sprite sprite, int positionInX, int positionInY, int points) {
        super(sprite, positionInX, positionInY);
        this.points = points;
        this.isActive = false;
    }
    
    public Observer getObserver() {
    	return observer;
    }
    
    public int getPoints() {
    	return points;
    }

    public void activatePowerUp(){
        if(!isActive){
            isActive = true;
            EntityObserver entityObserver = (EntityObserver) observer;
            entityObserver.activateObserver();
        }
    }
    public boolean isActive(){
        return isActive;
    }
}