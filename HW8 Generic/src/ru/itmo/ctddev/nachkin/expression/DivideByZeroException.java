package ru.itmo.ctddev.nachkin.expression;

public class DivideByZeroException extends Exception {
    public DivideByZeroException() {
        super("divide by zero");
    }
}
