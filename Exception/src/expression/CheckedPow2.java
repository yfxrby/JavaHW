package expression;

import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public class CheckedPow2 implements TripleExpression {
    private TripleExpression value;
    public CheckedPow2(TripleExpression val) {
        value = val;
    }

    private void check(int a) throws Exception {
        if (a < 0 || a > 31) {
            throw new OverflowException();
        }
    }

    public int evaluate(int x, int y, int z) throws Exception {
        int a = value.evaluate(x, y, z);
        check(a);
        final int help[] = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, 214748648};
        return help[a];
    }
}