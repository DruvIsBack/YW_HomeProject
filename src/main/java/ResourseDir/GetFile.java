package ResourseDir;

import java.io.*;

public class GetFile {

    private ClassLoader classLoader = getClass().getClassLoader();

    File getFileFromResourse(String relative_location){
        File file;
        try {
            file = new File(this.classLoader.getResource(relative_location).getFile());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return file;
    }

}
