package factories;

import java.io.File;

public class Original extends SpriteFactory {
	
    protected String path;
    
    public Original(String path) {
        super("/assets"+File.separator+"sprites"+File.separator+"original");
    }
    
}