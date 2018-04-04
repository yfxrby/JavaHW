package expression.exceptions;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("division by zero");
    }
}
