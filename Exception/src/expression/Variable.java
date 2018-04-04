package expression;

public class Variable implements TripleExpression {
    private String s;
    public Variable(String s) {
        this.s = s;
    }

    public int evaluate(int x, int y, int z) {
        if (s == "x") {
            return x;
        } else if (s == "y") {
            return y;
        } else {
            return z;
        }
    }
}
