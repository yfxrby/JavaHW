package ru.itmo.ctddev.nachkin.expression;

public class Negate<T> implements TripleExpression<T> {
    private TripleExpression<T> tmp;
    private Types<T> type;

    public Negate(TripleExpression<T> Num, Types<T> myType) {
        type = myType;
        tmp = Num;
    }

    public T evaluate(T x, T y, T z) throws Exception {
        return type.negate(tmp.evaluate(x, y, z));
    }
}
