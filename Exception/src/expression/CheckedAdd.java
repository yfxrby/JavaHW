package expression;

import expression.exceptions.OverflowException;

public class CheckedAdd extends AbstractOperator implements TripleExpression {
    public CheckedAdd(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected void check(int a, int b) throws Exception {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException();
        }
        if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new OverflowException();
        }
    }

    protected int solveInt(int a, int b) throws Exception {
        check(a, b);
        return a + b;
    }
}
