package expression;

public abstract class AbstractOperator implements TripleExpression {
    protected TripleExpression firstExpression;
    protected TripleExpression secondExpression;

    public AbstractOperator(TripleExpression a, TripleExpression b) {
        firstExpression = a;
        secondExpression = b;
    }

    public int evaluate(int x, int y, int z) throws Exception {
        return solveInt(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
    }

    protected abstract int solveInt(int a, int b) throws Exception;
}
