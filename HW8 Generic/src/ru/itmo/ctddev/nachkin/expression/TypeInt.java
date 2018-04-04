package ru.itmo.ctddev.nachkin.expression;

public class TypeInt implements Types<Integer> {
    public Integer add(Integer x, Integer y) throws Exception {
        return x + y;
    }

    public Integer subtract(Integer x, Integer y) throws Exception {
        return x - y;
    }

    public Integer multiply(Integer x, Integer y) throws Exception {
        return x * y;
    }

    public Integer divide(Integer x, Integer y) throws Exception {
        return x / y;
    }

    public Integer negate(Integer x) throws Exception {
        return -x;
    }

    public Integer abs(Integer x) throws Exception {
        if (x >= 0) {
            return x;
        } else {
            return -x;
        }
    }

    public Integer square(Integer x) throws Exception {
        return multiply(x, x);
    }

    public Integer mod(Integer x, Integer y) throws Exception {
        return x % y;
    }

    public Integer parse(String line) throws Exception {
        return Integer.parseInt(line);
    }
}
