package ru.itmo.ctddev.nachkin.expression;

public interface TripleExpression<T> {
    T evaluate(T x, T y, T z) throws Exception;
}
