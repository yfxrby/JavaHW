package expression;

import expression.exceptions.OverflowException;

public class CheckedMultiply extends AbstractOperator implements TripleExpression {
    public CheckedMultiply(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected void check(int a, int b) throws Exception {
        if (a > b) {
            check(b, a);
        } else {
            if (a < 0) {
                if (b < 0 && a < Integer.MAX_VALUE / b) {
                    throw new OverflowException();
                } else if (b > 0 && Integer.MIN_VALUE / b > a) {
                    throw new OverflowException();
                }
            } else if (a > 0 && a > Integer.MAX_VALUE / b) {
                throw new OverflowException();
            }
        }
    }

    protected int solveInt(int a, int b) throws Exception {
        check(a, b);
        return a * b;
    }
}
