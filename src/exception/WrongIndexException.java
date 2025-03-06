package exception;

public class WrongIndexException extends Exception{
    public WrongIndexException(){
        super("Indice incorrecto");
    }
}
