package expression.parser;

import expression.*;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;

public class ExpressionParser implements expression.exceptions.Parser {
    private int cur;

    private void checker(char c, String help) throws ParsingException, OverflowException {
        if (Character.isDigit(c) || c == 'x' || c == 'y' || c == 'z') {
            throw new ParsingException("Error : incorrect expression: " + help + c);
        }
    }

    public TripleExpression parse(String expression) throws ParsingException, OverflowException {
        for (int i = 0; i < expression.length() - 2; i++) {
            String help = "";
            if (i + 3 < expression.length()) {
                help = expression.substring(i, i + 4);
            }
            if (help.equals("pow2") || help.equals("log2")) {
                if (i + 4 < expression.length()) {
                    checker(expression.charAt(i + 4), expression);
                }
            }
            if (i + 2 < expression.length()) {
                help = expression.substring(i, i + 3);
            }
            if (help.equals("min") || help.equals("max")) {
                if (i + 3 < expression.length()) {
                    checker(expression.charAt(i + 3), expression);
                }
            }
        }
        expression = expression.replaceAll("\\p{javaWhitespace}+", "");
        cur = 0;
        TripleExpression res = parseMinMax(expression);
        if (cur < expression.length()) {
            throw new ParsingException("Error : can't full parse since index : " + cur + ". " + expression.substring(cur, expression.length()));
        }
        return res;
    }

    private TripleExpression parseMinMax(String s) throws ParsingException, OverflowException {
        TripleExpression res = parseShift(s);
        while (cur + 2 < s.length() && (s.substring(cur, cur + 3).equals("min") || s.substring(cur, cur + 3).equals("max"))) {
            char c = s.charAt(++cur);
            cur += 2;
            TripleExpression val = parseShift(s);
            if (c == 'i') {
                res = new CheckedMin(res, val);
            } else {
                res = new CheckedMax(res, val);
            }
        }
        return res;
    }

    private TripleExpression parseShift(String s) throws ParsingException, OverflowException {
        TripleExpression res = parseExpr(s);
        while (cur + 1 < s.length() && (s.substring(cur, cur + 2) == "<<" || s.substring(cur, cur + 2) == ">>")) {
            cur++;
            char c = s.charAt(cur++);
            TripleExpression val = parseExpr(s);
            if (c == '<') {
                res = new LeftShift(res, val);
            } else {
                res = new RightShift(res, val);
            }
        }
        return res;
    }

    private TripleExpression parseExpr(String s) throws ParsingException, OverflowException {
        TripleExpression res = parseSlog(s);
        while (cur < s.length() && (s.charAt(cur) == '+' || s.charAt(cur) == '-')) {
            char c = s.charAt(cur++);
            TripleExpression val = parseSlog(s);
            if (c == '-') {
                res = new CheckedSubtract(res, val);
            } else {
                res = new CheckedAdd(res, val);
            }
        }
        return res;
    }

    private TripleExpression parseSlog(String s) throws ParsingException, OverflowException {
        TripleExpression res = parseBrackets(s);
        while (cur < s.length() && ((s.charAt(cur) == '*') || (s.charAt(cur) == '/'))) {
            char c = s.charAt(cur++);
            TripleExpression val = parseBrackets(s);
            if (c == '*') {
                res = new CheckedMultiply(res, val);
            } else {
                res = new CheckedDivide(res, val);
            }
        }
        return res;
    }

    private TripleExpression parseBrackets(String s) throws ParsingException, OverflowException {
        if (cur < s.length() && s.charAt(cur) == '(') {
            cur++;
            TripleExpression r = parseMinMax(s);
            if (cur < s.length() && s.charAt(cur) == ')') {
                cur++;
            } else {
                if (cur >= s.length()) {
                    throw new ParsingException("Error: not close bracket at position. The end of expression was met");
                } else {
                    throw new ParsingException("Error: not close bracket at position " + cur + ". Met '" + s.charAt(cur) + "'. " + s.substring(cur, s.length()));
                }
            }
            return r;
        }
        return parseMnozh(s);
    }

    private TripleExpression parseMnozh(String s) throws ParsingException, OverflowException {
        if (cur >= s.length()) {
            throw new ParsingException("Error: incorrect expression. The end of expression was met");
        }
        if (s.charAt(cur) == '-') {
            cur++;
            if (cur < s.length()) {
                if (!(Character.isDigit(s.charAt(cur)))) {
                    return new CheckedNegate(parseBrackets(s));
                } else {
                    return getNumber(s, 1);
                }
            } else {
                throw new ParsingException("incorrect expression. The end of expression was met");
            }
        } else if (cur + 3 < s.length() && s.substring(cur, cur + 4).equals("log2")) {
            cur += 4;
            return new CheckedLog2(parseBrackets(s));
        } else if (cur + 3 < s.length() && s.substring(cur, cur + 4).equals("pow2")) {
            cur += 4;
            return new CheckedPow2(parseBrackets(s));
        } else if (s.charAt(cur) == 'x') {
            cur++;
            return new Variable("x");
        } else if (s.charAt(cur) == 'y') {
            cur++;
            return new Variable("y");
        } else if (s.charAt(cur) == 'z') {
            cur++;
            return new Variable("z");
        } else {
            return getNumber(s, 0);
        }
    }

    private TripleExpression getNumber(String s, int check) throws ParsingException, OverflowException {
        if (cur >= s.length()) {
            throw new ParsingException("Error: incorrect expression. The end of expression was met");
        }
        int idx = 0, help;
        boolean f = false;
        while (cur + idx < s.length() && Character.isDigit(s.charAt(cur + idx))) {
            idx++;
            f = true;
        }
        String tmp;
        if (!f) {
            throw new ParsingException("Error : unexpected symbol at position: " + cur + ". " + s.substring(cur, s.length()));
        } else {
            tmp = s.substring(cur, cur + idx);
        }
        cur += idx;
        if (check == 1) {
            tmp = "-" + tmp;
        }
        try {
            help = Integer.parseInt(tmp);
        } catch (Exception e) {
            throw new OverflowException(tmp);
        }
        return new Const(help);
    }
}
