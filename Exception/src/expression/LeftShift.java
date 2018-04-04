package expression;

public class LeftShift extends AbstractOperator implements TripleExpression {
    public LeftShift(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected int solveInt(int a, int b) {
        return a << b;
    }
}
