package ru.itmo.ctddev.nachkin.expression.parser;

import ru.itmo.ctddev.nachkin.expression.*;

public class ExpressionParser<T> implements Parser<T> {
    private int index = 0;
    private String expression;
    private Types<T> type;

    public ExpressionParser(Types<T> myType) {
        type = myType;
    }

    /*private void startCheck(String line) throws Exception {
        for (int i = 0; i < line.length() - 3; i++) {
            if (line.substring(i, i + 3).equals("abs") || line.substring(i, i + 3).equals("mod")) {
                char check = line.charAt(i + 3);
                if (!isCorrect(check)) {
                    throw new ParserException("Unexpected symbol");
                } else {
                    switch (check) {
                        case '+':
                        case '*':
                        case '/':
                        case ')':
                        case 'x':
                        case 'y':
                        case 'z':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            throw new ParserException("Unexpected symbol");
                    }
                }
            }
        }
    }*/

    private void check(char sign) throws Exception {
        if (!isCorrect(sign)) {
            if (index + 6 < expression.length()){
                if (!expression.substring(index, index + 6).equals("square") && !(expression.substring(index, index + 3).equals("mod") || expression.substring(index, index + 3).equals("abs"))) {
                    throw new ParserException("Unexpected symbol");
                }
            } else if (index + 3 < expression.length()) {
                if (!(expression.substring(index, index + 3).equals("mod") || expression.substring(index, index + 3).equals("abs"))) {
                    throw new ParserException("Unexpected symbol");
                }
            } else {
                throw new ParserException("Unexpected symbol");
            }
        }
    }

    private boolean isCorrect(char sign) {
        switch (sign) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
            case 'x':
            case 'y':
            case 'z':
            case ' ':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                return true;
            }
            default:
                return false;
        }
    }

    private TripleExpression<T> last_parse() throws Exception {
        char sign = expression.charAt(index);
        check(sign);
        switch (sign) {
            case '(': {
                index++;
                TripleExpression<T> line = shifts_parse();
                index++;
                return line;
            }
            case '-': {
                index++;
                if (index + 10 <= expression.length() && expression.substring(index, index + 10).equals("2147483648")) {
                    index += 10;
                    T num = type.parse("-2147483648");
                    return new Const<T>(num);
                } else {
                    T num = type.parse("0");
                    return new Subtract<>(new Const<T>(num), last_parse(), type);
                }
            }
            case 'x': {
                index++;
                return new Variable<>("x");
            }
            case 'y': {
                index++;
                return new Variable<>("y");
            }
            case 'z': {
                index++;
                return new Variable<>("z");
            }
            case 'a': {
                index += 3;
                return new Abs<>(last_parse(), type);
            }
            case 's': {
                index += 6;
                return new Square<>(last_parse(), type);
            }
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                int start = index;
                while (('0' <= expression.charAt(index)) && (expression.charAt(index) <= '9')) {
                    index++;
                }
                try {
                    T num = type.parse(expression.substring(start, index));
                    return new Const<>(num);
                } catch (Exception e){
                    throw new OverflowException();
                }
            }
            default: {
                throw new ParserException("Unexpected symbol");
            }
        }
    }

    private TripleExpression<T> mid_parse() throws Exception {
        TripleExpression<T> line = last_parse();
        char sign = expression.charAt(index);
        check(sign);
        while (sign == '*' || sign == '/' || sign == 'm') {
            switch (sign) {
                case '*': {
                    index++;
                    line = new Multiply<>(line, last_parse(), type);
                    break;
                }
                case '/': {
                    index++;
                    line = new Divide<>(line, last_parse(), type);
                    break;
                }
                case 'm': {
                    index += 3;
                    line = new Mod<>(line, last_parse(), type);
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression<T> first_parse() throws Exception {
        TripleExpression<T> line = mid_parse();
        char sign = expression.charAt(index);
        check(sign);
        while (sign == '+' || sign == '-') {
            switch (sign) {
                case '+': {
                    index++;
                    line = new Add<>(line, mid_parse(), type);
                    break;
                }
                case '-': {
                    index++;
                    line = new Subtract<>(line, mid_parse(), type);
                    break;
                }
            }
            sign = expression.charAt(index);
        }
        return line;
    }

    private TripleExpression<T> shifts_parse() throws Exception {
        TripleExpression<T> line = first_parse();
        char sign = expression.charAt(index);
        check(sign);
        return line;
    }

    public TripleExpression<T> parse(String startline) throws Exception {
        //startCheck(startline);
        expression = startline.replaceAll("\\p{javaWhitespace}+", "") + " ";
        //System.out.println(expression);
        int balance = 0;
        for (int i = 0; i < expression.length(); i++) {
            char sign = expression.charAt(i);
            if (sign == '(') {
                balance++;
            } else if (sign == ')') {
                balance--;
            }
            if (balance < 0) {
                throw new ParserException("Brackers error");
            }
        }
        if (balance != 0) {
            throw new ParserException("Brackets error");
        }
        index = 0;
        return shifts_parse();
    }
}