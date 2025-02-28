package entities.enemies;
import entities.LogicalEntity;
import entities.visitor.EnemyVisitor;
public interface EnemyEntity extends LogicalEntity{
	public void moveRight();
	public void moveLeft();
	public int getPointsOnDeath();
	public int getPointsOnKill();
	public void acceptVisit(EnemyVisitor enemy);
}
