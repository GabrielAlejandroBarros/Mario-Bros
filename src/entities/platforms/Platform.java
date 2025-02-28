package entities.platforms;

import entities.Entity;
import entities.enemies.Enemy;
import entities.projectile.FireBall;
import entities.visitor.EnemyVisitor;
import entities.visitor.PlatformsVisitor;
import entities.visitor.VisitedElement;
import factories.Sprite;

public abstract class Platform extends Entity implements VisitedElement, EnemyVisitor{

	protected boolean isBreakeable;
	
	public Platform(Sprite sprite, int positionInX, int positionInY, boolean isBreakeable) {
		super(sprite, positionInX, positionInY);
		this.isBreakeable = isBreakeable;
	}
	
	public boolean isBreakeable() {
		return isBreakeable;
	}
	
    public void acceptVisit(PlatformsVisitor visitor){
		visitor.visit(this);
	}
	
    public void visit(Enemy enemy) {}

	public void visit(FireBall fireBall){
		fireBall.rebound();
	}
    
}
