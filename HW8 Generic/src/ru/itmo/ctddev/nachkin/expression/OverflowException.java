package ru.itmo.ctddev.nachkin.expression;

public class OverflowException extends Exception {
    public OverflowException() {
        super("overflow");
    }
}
