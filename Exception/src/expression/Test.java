package expression;

import expression.parser.*;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println((new ExpressionParser().parse("x/-2")).evaluate(2, 0, 0));
    }
}
