package entities.projectile;
import java.util.HashMap;

import entities.enemies.Enemy;
import entities.platforms.Platform;
import entities.visitor.EnemyVisitor;
import factories.Sprite;
import tools.GraphicTools;

public class FireBall extends Projectile implements EnemyVisitor{
	protected String direction;
	protected HashMap<String,Sprite> sprites;
	protected boolean isExploding;

	public FireBall(Sprite sprite, float positionInX, float positionInY, String direction, HashMap<String,Sprite> sprites ) {
		super(sprite, positionInX, positionInY, direction);
		this.sprites = sprites;
		isExploding=false;
	}

	public void moveRight(int spriteNumber) {
		float projectileX = getX();
		setX(GraphicTools.round2Digits(projectileX + horizontalSpeed));
		verticalSpeed += 0.01f;     
    	setY(getY() - verticalSpeed);
		setSprite(sprites.get(""+spriteNumber));  
		observer.update();
    }

	public void rebound(){
		verticalSpeed = -verticalSpeed*0.4f;
		setY(getY()+0.5f);
	}

	public Sprite getSprite(String path){
		return sprites.get(path);
	}

	public void exploding(int count){
		sprite= sprites.get("blow"+ count);
		observer.update();
	}

	public void moveLeft(int spriteNumber){
		float projectileX = getX();
		setX(GraphicTools.round2Digits(projectileX - horizontalSpeed));
		verticalSpeed += 0.01f;      
    	setY(getY() - verticalSpeed);
		setSprite(sprites.get(""+spriteNumber));
		observer.update();
	}

	public boolean getIsExploding(){
		return isExploding;
	}

	public void setIsExploding(boolean burst){
		isExploding= burst;
	}

	public void visit(Enemy enemy) {
		enemy.dead();
		isExploding=true;
	}

	public void acceptVisit(Platform platform) {
		platform.visit(this);
	}
}
