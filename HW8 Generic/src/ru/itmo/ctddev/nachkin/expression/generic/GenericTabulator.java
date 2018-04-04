package ru.itmo.ctddev.nachkin.expression.generic;

import java.math.BigInteger;

import ru.itmo.ctddev.nachkin.expression.*;
import ru.itmo.ctddev.nachkin.expression.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] ans = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        TypeInteger type_i = new TypeInteger();
        TypeDouble type_d = new TypeDouble();
        TypeBigInteger type_bi = new TypeBigInteger();
        TypeInt type_u = new TypeInt();
        TypeByte type_b = new TypeByte();
        TypeFloat type_f = new TypeFloat();
        ExpressionParser<Integer> expression_i = new ExpressionParser<>(type_i);
        ExpressionParser<Double> expression_d = new ExpressionParser<>(type_d);
        ExpressionParser<BigInteger> expression_bi = new ExpressionParser<>(type_bi);
        ExpressionParser<Integer> expression_u = new ExpressionParser<>(type_u);
        ExpressionParser<Byte> expression_b = new ExpressionParser<>(type_b);
        ExpressionParser<Float> expression_f = new ExpressionParser<>(type_f);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        switch (mode) {
                            case "i": {
                                ans[i - x1][j - y1][k - z1] = expression_i.parse(expression).evaluate(i, j, k);
                                break;
                            }
                            case "d": {
                                ans[i - x1][j - y1][k - z1] = expression_d.parse(expression).evaluate(type_d.parse(String.valueOf(i)), type_d.parse(String.valueOf(j)), type_d.parse(String.valueOf(k)));
                                break;
                            }
                            case "bi": {
                                ans[i - x1][j - y1][k - z1] = expression_bi.parse(expression).evaluate(type_bi.parse(String.valueOf(i)), type_bi.parse(String.valueOf(j)), type_bi.parse(String.valueOf(k)));
                                break;
                            }
                            case "u": {
                                ans[i - x1][j - y1][k - z1] = expression_u.parse(expression).evaluate(type_u.parse(String.valueOf(i)), type_u.parse(String.valueOf(j)), type_u.parse(String.valueOf(k)));
                                break;
                            }
                            case "b": {
                                ans[i - x1][j - y1][k - z1] = expression_b.parse(expression).evaluate(type_b.parse(String.valueOf(i)), type_b.parse(String.valueOf(j)), type_b.parse(String.valueOf(k)));
                                break;
                            }
                            case "f": {
                                ans[i - x1][j - y1][k - z1] = expression_f.parse(expression).evaluate(type_f.parse(String.valueOf(i)), type_f.parse(String.valueOf(j)), type_f.parse(String.valueOf(k)));
                                break;
                            }
                        }
                    } catch (Exception e) {
                        ans[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return ans;
    }
}