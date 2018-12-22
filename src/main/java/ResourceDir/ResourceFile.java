package ResourceDir;

import Logger.Log;

import java.io.*;
import java.util.Objects;

public class ResourceFile {
    File file = null;

    private ClassLoader classLoader = getClass().getClassLoader();

    public ResourceFile(){

    }

    public ResourceFile(String relative_location) {
        try {
            file = new File(Objects.requireNonNull(this.classLoader.getResource(relative_location)).getFile());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public File getFile(){
        return file;
    }


    public String getAbsolutePath(){
        String abs_path = null;
        try{
            abs_path = file.getAbsolutePath();
            Log.success("Absolute path of "+file.getName()+" is "+file.getAbsolutePath());
        }catch (NullPointerException e){
            Log.error(e.getMessage());
            e.printStackTrace();
        }
        return abs_path;
    }

    public String getResourseDirAbsPath(){
        //.toURI()
        try {
            return (ResourceFile.class.getResource("../../resources/").getFile());
        }catch(Exception e){
            Log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
