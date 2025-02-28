package entities.state.koopaTroopa;
import entities.character.Character;
import entities.enemies.Enemy;
public interface KoopaTroopaState {
    public void move(int frame);
    public void hit(Character character);
    public void hitEnemy(Enemy enemy);
}