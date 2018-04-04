package ru.itmo.ctddev.nachkin.expression;

public class ParserException extends Exception {
    public ParserException(String line) {
        super(line);
    }
}
