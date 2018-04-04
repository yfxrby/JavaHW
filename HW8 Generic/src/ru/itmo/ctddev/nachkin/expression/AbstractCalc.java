package ru.itmo.ctddev.nachkin.expression;

public abstract class AbstractCalc<T> implements TripleExpression<T> {
    protected TripleExpression<T> tmpone;
    protected TripleExpression<T> tmptwo;

    Types<T> type;

    AbstractCalc(TripleExpression<T> firstNum, TripleExpression<T> secondNum, Types<T> myType) {
        type = myType;
        tmpone = firstNum;
        tmptwo = secondNum;
    }

    public T evaluate(T x, T y, T z) throws Exception {
        return evaluateImpl(tmpone.evaluate(x, y, z), tmptwo.evaluate(x, y, z));
    }

    protected abstract T evaluateImpl(T x, T y) throws Exception;
}
