package expression;

import expression.exceptions.OverflowException;

public class CheckedSubtract extends AbstractOperator implements TripleExpression {
    public CheckedSubtract(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected void check(int a, int b) throws Exception {
        if(b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        }
        if(b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
    }

    protected int solveInt(int a, int b) throws Exception {
        check(a, b);
        return a - b;
    }
}
