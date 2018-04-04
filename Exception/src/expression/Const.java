package expression;

public class Const implements TripleExpression {
    private int xInt;

    public Const(int x) {
        xInt = x;
    }

    public int evaluate(int x, int y, int z) {
        return xInt;
    }
}
