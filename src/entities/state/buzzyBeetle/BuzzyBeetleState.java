package entities.state.buzzyBeetle;

public interface BuzzyBeetleState {
    public void moveLeft(int frame);
    public void moveRight(int frame);
    public int hit();
}
