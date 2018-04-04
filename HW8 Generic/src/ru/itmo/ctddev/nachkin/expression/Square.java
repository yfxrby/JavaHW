package ru.itmo.ctddev.nachkin.expression;

public class Square<T> implements TripleExpression<T> {
    private TripleExpression<T> tmp;
    private Types<T> type;

    public Square(TripleExpression<T> Num, Types<T> myType) {
        type = myType;
        tmp = Num;
    }

    public T evaluate(T x, T y, T z) throws Exception {
        return type.square(tmp.evaluate(x, y, z));
    }
}
