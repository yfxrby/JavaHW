package expression.exceptions;

public class ParsingException extends Exception {
    public ParsingException() { super("incorrect");}
    public ParsingException(String s) {
        super(s);
    }
}
