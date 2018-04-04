package ru.itmo.ctddev.nachkin.expression;

public class TypeInteger implements Types<Integer> {
    private void checkAdd(Integer x, Integer y) throws Exception {
        if ((x > 0 && y > Integer.MAX_VALUE - x) || (x < 0 && y < Integer.MIN_VALUE - x)) {
            throw new OverflowException();
        }
    }

    public Integer add(Integer x, Integer y) throws Exception {
        checkAdd(x, y);
        return x + y;
    }

    private void checkSubtract(Integer x, Integer y) throws Exception {
        if ((y > 0 && x < Integer.MIN_VALUE + y) || (y < 0 && x > Integer.MAX_VALUE + y)) {
            throw new OverflowException();
        }
    }

    public Integer subtract(Integer x, Integer y) throws Exception {
        checkSubtract(x, y);
        return x - y;
    }

    private void checkMultiply(Integer x, Integer y) throws Exception {
        if (x > 0 && y > 0) {
            if (x > Integer.MAX_VALUE / y || y > Integer.MAX_VALUE / x) {
                throw new OverflowException();
            }
        } else if (x < 0 && y < 0) {
            if (x < Integer.MAX_VALUE / y || y < Integer.MAX_VALUE / x) {
                throw new OverflowException();
            }
        } else if (x > 0 && y < 0) {
            if (y < Integer.MIN_VALUE / x) {
                throw new OverflowException();
            }
        } else if (x < 0 && y > 0) {
            if (x < Integer.MIN_VALUE / y) {
                throw new OverflowException();
            }
        }
    }

    public Integer multiply(Integer x, Integer y) throws Exception {
        checkMultiply(x, y);
        return x * y;
    }

    private void checkDivide(Integer x, Integer y) throws Exception {
        if (y == 0) {
            throw new DivideByZeroException();
        } else if (y == -1 && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public Integer divide(Integer x, Integer y) throws Exception {
        checkDivide(x, y);
        return x / y;
    }

    private void checkNegate(Integer x) throws Exception {
        if (x <= Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public Integer negate(Integer x) throws Exception {
        checkNegate(x);
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
        checkMultiply(x, x);
        return multiply(x, x);
    }

    private void checkMod(Integer x, Integer y) throws Exception {
        if (y == 0) {
            throw new OverflowException();
        }
    }

    public Integer mod(Integer x, Integer y) throws Exception {
        checkMod(x, y);
        return x % y;
    }

    public Integer parse(String line) throws Exception {
        return Integer.parseInt(line);
    }
}
