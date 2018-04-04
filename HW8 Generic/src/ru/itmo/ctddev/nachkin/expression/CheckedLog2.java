package ru.itmo.ctddev.nachkin.expression;

/*
public class CheckedLog2 implements TripleExpression {
    private TripleExpression tmp;
    public CheckedLog2(TripleExpression firstNum) {
        tmp = firstNum;
    }

    private void check(int x) throws Exception {
        if (x <= 0) {
            throw new NegativeLogException();
        }
    }

    private int evaluateImpl(int x) {
        int value = 1, ans = 0;
        while (x > value && ans < 31) {
            value *= 2;
            ans++;
        }
        if (ans > 30) {
            ans = 30;
        }
        if (value > x) {
            return ans - 1;
        } else {
            return ans;
        }
    }

    public int evaluate(int x, int y, int z) throws Exception {
        check(tmp.evaluate(x, y, z));
        return evaluateImpl(tmp.evaluate(x, y, z));
    }
}
*/