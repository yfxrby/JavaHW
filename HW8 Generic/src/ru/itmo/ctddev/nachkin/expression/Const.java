package ru.itmo.ctddev.nachkin.expression;

public class Const<T> implements TripleExpression<T> {
    private T tmp;

    public Const(T x) {
        tmp = x;
    }

    public T evaluate(T x, T y, T z) {
        return tmp;
    }
}
