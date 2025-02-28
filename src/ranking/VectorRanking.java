package ranking;
public class VectorRanking<K,V> {
    protected K name;
    protected V top;
    protected V score;

    public VectorRanking(K name,V top,V score){
        this.name = name;
        this.top = top;
        this.score = score;
    }
   
    public K getName(){
        return name;
    }
    public V getTop(){
        return top;
    }
    public V getScore(){
        return score;
    }

    public void setTop(V top) {
        this.top = top;
    }

    public String toString(){
        return name+","+score+","+top;
    }




}
