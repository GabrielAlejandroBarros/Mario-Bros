package factories;
public class Vector<E> {
    protected E positionX;
    protected E positionY;
    protected E type;

    public Vector(){
        this.positionX = null;
        this.positionY = null;
        this.type = null;
    }
    public boolean isEmpty(){
        return positionX == null && positionY == null && type == null;
    }
    public void setX(E position){
        this.positionX = position;
    }
    public void setY(E position){
        this.positionY = position;
    }
    public void setType(E type){
        this.type = type;
    }
    public E getPositionX(){
        E posX = positionX;
        positionX = null;
        return posX;
    }
    public E getPositionY(){
        E posY = positionY;
        positionY = null;
        return posY;
    }
    public E getType(){
        E typee = type;
        type = null;
        return typee;
    }




}
