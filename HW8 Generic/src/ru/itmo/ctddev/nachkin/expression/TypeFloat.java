package ru.itmo.ctddev.nachkin.expression;

public class TypeFloat implements Types<Float> {
    public Float add(Float x, Float y) throws Exception {
        return x + y;
    }

    public Float subtract(Float x, Float y) throws Exception {
        return x - y;
    }

    public Float multiply(Float x, Float y) throws Exception {
        return x * y;
    }

    public Float divide(Float x, Float y) throws Exception {
        return x / y;
    }

    public Float negate(Float x) throws Exception {
        return -x;
    }

    public Float abs(Float x) throws Exception {
        if (x >= 0) {
            return x;
        } else {
            return -x;
        }
    }

    public Float square(Float x) throws Exception {
        return multiply(x, x);
    }

    public Float mod(Float x, Float y) throws Exception {
        return x % y;
    }

    public Float parse(String line) throws Exception {
        return Float.parseFloat(line);
    }
}
