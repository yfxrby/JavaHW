package ru.itmo.ctddev.nachkin.expression;

public class NegativeLogException extends Exception {
    public NegativeLogException() {
        super("Negative number on log");
    }
}