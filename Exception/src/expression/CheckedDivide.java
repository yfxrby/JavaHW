package expression;

import expression.exceptions.OverflowException;
import expression.exceptions.DivisionByZeroException;

public class CheckedDivide extends AbstractOperator implements TripleExpression {
    public CheckedDivide(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected void check(int a, int b) throws Exception {
        if(a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
        if (b == 0) {
            throw new DivisionByZeroException();
        }
    }

    protected int solveInt(int a, int b) throws Exception {
        check(a, b);
        return a / b;
    }
}
