package app.exceptions;

public class FileIsEmptyException extends Exception{
    public FileIsEmptyException(String message){
        super(message);
    }
}
