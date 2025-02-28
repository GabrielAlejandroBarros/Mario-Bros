package entities.platforms;

import entities.character.CharacterVisitor;
import entities.enemies.Enemy;
import entities.projectile.FireBall;
import factories.Sprite;

public class VoidBlock extends Platform{
	
	static final private int pointsOnKill = -15;
	static final private boolean isBreakeable = false;
	
	public VoidBlock(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY, isBreakeable); 
	}

	public int getPointOnKill() {
		return pointsOnKill;
	}
	
	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }
	
	public void visit(Enemy enemy) {
    	enemy.dead();
    }

	public void visit(FireBall fireBall){
		fireBall.setIsExploding(true);
	}
	
}