package entities.visitor;
import entities.enemies.Enemy;
public interface EnemyVisitor {
	public void visit(Enemy enemy);
}
