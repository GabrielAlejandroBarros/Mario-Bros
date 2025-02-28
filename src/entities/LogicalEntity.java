package entities;
import factories.Sprite;
import observer.GraphicObserver;

public interface LogicalEntity{	
    public Sprite getSprite();
    public float getX();
    public float getY();
    public BoundingBox getBoundingBox();
    public void setBoundingBox(BoundingBox boundingBox);
    public GraphicObserver getGraphicObserver();    
}
