package ru.itmo.ctddev.nachkin.expression;

public interface Types<T> {
    T add(T x, T y) throws Exception;

    T subtract(T x, T y) throws Exception;

    T multiply(T x, T y) throws Exception;

    T divide(T x, T y) throws Exception;

    T negate(T x) throws Exception;

    T abs(T x) throws Exception;

    T square(T x) throws Exception;

    T mod(T x, T y) throws Exception;

    T parse(String line) throws Exception;
}
