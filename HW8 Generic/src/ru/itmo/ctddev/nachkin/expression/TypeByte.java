package ru.itmo.ctddev.nachkin.expression;

public class TypeByte implements Types<Byte> {
    public Byte add(Byte x, Byte y) throws Exception {
        return (byte) ((int) x + (int) y);
    }

    public Byte subtract(Byte x, Byte y) throws Exception {
        return (byte) ((int) x - (int) y);
    }

    public Byte multiply(Byte x, Byte y) throws Exception {
        return (byte) ((int) x * (int) y);
    }

    public Byte divide(Byte x, Byte y) throws Exception {
        return (byte) ((int) x / (int) y);
    }

    public Byte negate(Byte x) throws Exception {
        return (byte) ((int) -x);
    }

    public Byte abs(Byte x) throws Exception {
        if (x >= 0) {
            return x;
        } else {
            return (byte) ((int) -x);
        }
    }

    public Byte square(Byte x) throws Exception {
        return multiply(x, x);
    }

    public Byte mod(Byte x, Byte y) throws Exception {
        return (byte) ((int) x % (int) y);
    }

    public Byte parse(String line) throws Exception {
        return Byte.parseByte(line);
    }
}
