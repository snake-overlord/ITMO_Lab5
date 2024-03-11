package app.config;

import app.exceptions.ScriptRecursionException;

import java.util.ArrayList;
import java.util.List;

public class OpenUniqueFile {
    List<String> fileNames;
    public OpenUniqueFile(){
        fileNames = new ArrayList<>();
    }
    public void check(String fileName){
        if(fileNames.contains(fileName)){
            try {
                throw new ScriptRecursionException("Script recursion!");
            } catch (ScriptRecursionException e) {
                System.err.print("Script recursion!\n");
                throw new RuntimeException();
            }
        }
        else{
            fileNames.add(fileName);
        }
    }
}
