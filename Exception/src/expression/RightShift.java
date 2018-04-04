package expression;

public class RightShift extends AbstractOperator implements TripleExpression {
    public RightShift(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    protected int solveInt(int a, int b) {
        return a >> b;
    }
}
