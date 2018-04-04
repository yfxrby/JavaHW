package expression;

import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public class CheckedLog2 implements TripleExpression {
    private TripleExpression value;
    public CheckedLog2(TripleExpression val) {
        value = val;
    }

    private void check(int a) throws Exception {
        if (!(a > 0)) {
            throw new ParsingException("Error : negative number is in log2 " + a);
        }
    }

    public int evaluate(int x, int y, int z) throws Exception {
        int a = value.evaluate(x, y, z);
        check(a);
        return (int)(Math.log(a) / Math.log(2));
    }
}