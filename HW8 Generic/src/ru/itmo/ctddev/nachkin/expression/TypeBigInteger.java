package ru.itmo.ctddev.nachkin.expression;

import java.math.BigInteger;

public class TypeBigInteger implements Types<BigInteger> {
    public BigInteger add(BigInteger x, BigInteger y) throws Exception {
        return x.add(y);
    }

    public BigInteger subtract(BigInteger x, BigInteger y) throws Exception {
        return x.subtract(y);
    }

    public BigInteger multiply(BigInteger x, BigInteger y) throws Exception {
        return x.multiply(y);
    }

    public BigInteger divide(BigInteger x, BigInteger y) throws Exception {
        return x.divide(y);
    }

    public BigInteger negate(BigInteger x) throws Exception {
        return x.negate();
    }

    public BigInteger abs(BigInteger x) throws Exception {
        return x.abs();
    }

    public BigInteger square(BigInteger x) throws Exception {
        return x.multiply(x);
    }

    public BigInteger mod(BigInteger x, BigInteger y) throws Exception {
        return x.remainder(y);
    }

    public BigInteger parse(String line) throws Exception {
        return new BigInteger(line);
    }
}
