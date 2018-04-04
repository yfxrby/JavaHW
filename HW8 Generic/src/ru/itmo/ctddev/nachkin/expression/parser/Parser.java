package ru.itmo.ctddev.nachkin.expression.parser;

import ru.itmo.ctddev.nachkin.expression.TripleExpression;

public interface Parser<T> {
    TripleExpression<T> parse(String expression) throws Exception;
}
