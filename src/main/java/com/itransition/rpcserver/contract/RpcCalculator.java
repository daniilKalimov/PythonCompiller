
package com.itransition.rpcserver.contract;

import com.itransition.rpcserver.CalculatorException;

import java.math.BigDecimal;

public interface RpcCalculator {
    /**
     * Add numbers
     * @param left
     * @param right
     * @return left + right
     * @throws Exception
     */
    BigDecimal add(BigDecimal left, BigDecimal right) throws CalculatorException;

    /**
     * Subtract numbers
     * @param from - number from which we will subtract
     * @param subtractant - number, which will be subtracted
     * @return from - subtractant
     * @throws Exception
     */
    BigDecimal subtract(BigDecimal from, BigDecimal subtractant) throws CalculatorException;

    /**
     * Multiply numbers
     * @param left
     * @param right
     * @return left * right
     * @throws Exception
     */
    BigDecimal multiply(BigDecimal left, BigDecimal right) throws CalculatorException;

    /**
     * Divide numbers exactly
     * @param dividend
     * @param divisor
     * @return dividend / divisor
     * @throws Exception
     */
    BigDecimal divide(BigDecimal dividend, BigDecimal divisor) throws CalculatorException;

    /**
     * Exponentiate number
     * @param base - number which will be exponentiated
     * @param exponent - exponent to apply
     * @return base ^ exponent
     * @throws Exception
     */
    BigDecimal power(BigDecimal base, BigDecimal exponent) throws CalculatorException;
}

