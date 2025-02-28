package factories;
import java.io.File;

public class Custom extends SpriteFactory{
    protected String path;
    public Custom(String path){
        super("/assets"+File.separator+"sprites"+File.separator+"custom");
    }
}