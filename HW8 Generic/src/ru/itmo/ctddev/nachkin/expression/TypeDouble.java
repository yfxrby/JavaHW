package ru.itmo.ctddev.nachkin.expression;

public class TypeDouble implements Types<Double> {
    public Double add(Double x, Double y) throws Exception {
        return x + y;
    }

    public Double subtract(Double x, Double y) throws Exception {
        return x - y;
    }

    public Double multiply(Double x, Double y) throws Exception {
        return x * y;
    }

    public Double divide(Double x, Double y) throws Exception {
        return x / y;
    }

    public Double negate(Double x) throws Exception {
        return -x;
    }

    public Double abs(Double x) throws Exception {
        if (x >= 0) {
            return x;
        } else {
            return -x;
        }
    }

    public Double square(Double x) throws Exception {
        return x * x;
    }

    public Double mod(Double x, Double y) throws Exception {
        return x % y;
    }

    public Double parse(String line) throws Exception {
        return Double.parseDouble(line);
    }
}
