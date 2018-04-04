package expression.exceptions;

public class OverflowException extends Exception {
    public OverflowException() {
        super("overflow");
    }

    public OverflowException(String s) {
        super("overflow : " + s);
    }
}
