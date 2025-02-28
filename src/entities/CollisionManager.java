package entities;
public interface CollisionManager<T extends LogicalEntity> {    
    public void enemiesCollisions(T entity);
    public void platformsCollisions(T entity);
    public void powerUpsCollisions(T entity);
}
