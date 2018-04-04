package ru.itmo.ctddev.nachkin.expression;

public class Multiply<T> extends AbstractCalc<T> implements TripleExpression<T> {
    public Multiply(TripleExpression<T> firstNum, TripleExpression<T> secondNum, Types<T> type) {
        super(firstNum, secondNum, type);
    }

    public T evaluateImpl(T x, T y) throws Exception {
        return type.multiply(x, y);
    }
}