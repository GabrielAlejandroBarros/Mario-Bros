package entities.character;
import entities.LogicalEntity;
public interface CharacterEntity extends LogicalEntity {
    public int getCoins();
    public void addCoins(int coins);
    public int getScore();
    public int getLives();
    public boolean isInvencible();
    public float getSpeed();
    public boolean isMovingRight();
}

