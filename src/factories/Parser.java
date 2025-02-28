package factories;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Parser{
    protected LinkedList<Vector<Integer>> levelContent;    
    protected String path;    
    protected int numberLevel;

    public Parser(){
        this.levelContent = new LinkedList<>();
    }
    
    private void getLevelContent(){        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {            
            String currentRow = reader.readLine();
            while (currentRow != null) {   
                String[] curretRowReading = currentRow.split(","); 
                traduceAndAdd(curretRowReading);  
                currentRow = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }      
    }
    
    private void traduceAndAdd(String[] curretRowReading){
        int cycle = 1;
        Vector<Integer> numbersTrio = new Vector<Integer>();
        for (String element : curretRowReading) {                    
            Integer number = Integer.parseInt(element);
            addInListByCycle(cycle,number,numbersTrio);
            cycle++; 
        } 
        levelContent.addLast(numbersTrio);
    }
    private void addInListByCycle(int cycle, Integer number, Vector<Integer> numbersTrio) {
        if(cycle == 1) 
        	numbersTrio.setType(number);
        if(cycle == 2) 
        	numbersTrio.setX(number);
        if(cycle == 3) 
        	numbersTrio.setY(number);
    }
   
    public int getType(){
        int type= levelContent.peekFirst().getType();
        checkRemove();
        return type;
    }
    
    public int getPositionX(){
        int position = levelContent.peekFirst().getPositionX();
        checkRemove();
        return  position;
    }
   
    public int getPositionY(){
        int position = levelContent.peekFirst().getPositionY();
        checkRemove();
        return  position;
    }
    
    private void checkRemove(){
        if(levelContent.peek().isEmpty())
            levelContent.removeFirst();
    }

    public boolean hasToRead(){
        return !levelContent.isEmpty();
    }
    
    public void setLevel(int levelNumber) {
        this.numberLevel = levelNumber;
        this.path = ("src"+File.separator + "levels" + File.separator + "level"+numberLevel+".txt");
        getLevelContent();
    }         
}