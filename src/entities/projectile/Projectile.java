package entities.projectile;

import entities.Entity;
import factories.Sprite;
import tools.GraphicTools;
import views.ViewConstants;

public abstract class Projectile extends Entity{
	protected boolean isMovingRight;
	protected float horizontalSpeed;
	protected String direction;	
	protected float initialX;
	protected float initialY;
	protected float verticalSpeed;

	protected Projectile(Sprite sprite, float positionInX, float positionInY, String direction) {
		super(sprite, positionInX, positionInY);
		this.direction = direction;
		setInitialX(positionInX);
		setInitialY(positionInY);
		this.horizontalSpeed = ViewConstants.PROJECTILE_SPEED ;
		this.verticalSpeed = 0;
	}

    public void moveRight() {
		float projectileX = getX();
		setX(GraphicTools.round2Digits(projectileX + horizontalSpeed));
		observer.update();
    }

	public void moveLeft(){
		float projectileX = getX();
		setX(GraphicTools.round2Digits(projectileX - horizontalSpeed));
		observer.update();
	}

	public String getDirection() {
		return direction;
	}
	public void setInitialX(float initialX) {
		this.initialX = initialX;
	}

	public float getInitialX() {
		return initialX;
	}
	public void setInitialY(float initialY) {
		this.initialY = initialY;
	}

	public float getInitialY() {
		return initialY;
	}

	public float getVerticalSpeed() {
		return verticalSpeed;
	}

	public void setVerticalSpeed(float verticalSpeed) {
		this.verticalSpeed = verticalSpeed;
	}

}
