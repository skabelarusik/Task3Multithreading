package by.epam.multithreading.exception;
import java.io.IOException;

public class DataFileException extends IOException {

    public DataFileException(){
        super();
    }

    public DataFileException(String message){
        super(message);
    }

    public DataFileException(String message, Throwable cause){
        super(message, cause);
    }

    public DataFileException(Throwable cause){
        super(cause);
    }
}
