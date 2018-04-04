package ru.itmo.ctddev.nachkin.expression;

public class Variable<T> implements TripleExpression<T> {
    private String line;

    public Variable(String Var) {
        line = Var;
    }

    public T evaluate(T x, T y, T z) {
        if (line.equals("x")) {
            return x;
        } else if (line.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
