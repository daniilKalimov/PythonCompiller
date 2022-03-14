package com.itransition.rpcserver.controller;

import com.itransition.rpcserver.CalculatorException;
import com.itransition.rpcserver.contract.RpcCalculator;
import com.itransition.rpcserver.dto.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/calculator")
@CrossOrigin("*")
@Api(description = "Calculator controller")
public class CalculatorController {

    private final RpcCalculator rpcCalculator;

    public CalculatorController(RpcCalculator rpcCalculator)  {
        this.rpcCalculator = rpcCalculator;
    }


    @GetMapping("/add")
    @ApiOperation("return the sum of numbers")
    public Wrapper<BigDecimal> add(@RequestParam BigDecimal left, @RequestParam BigDecimal right) throws CalculatorException{
            return new Wrapper<>(rpcCalculator.add(left, right));
    }

    @GetMapping("/subtract")
    @ApiOperation("return the subtract of numbers")
    public Wrapper<BigDecimal> subtract(@RequestParam BigDecimal from, @RequestParam BigDecimal subtract) throws CalculatorException{
            return new Wrapper<>(rpcCalculator.subtract(from, subtract));
    }

    @GetMapping("/multiply")
    @ApiOperation( "return the multiply of numbers")
    public Wrapper<BigDecimal> multiply(@RequestParam BigDecimal left, @RequestParam BigDecimal right) throws CalculatorException{
            return new Wrapper<>(rpcCalculator.multiply(left, right));
    }

    @GetMapping("/divide")
    @ApiOperation( "return the divide of  numbers")
    public Wrapper<BigDecimal> divide(@RequestParam BigDecimal dividend, @RequestParam BigDecimal divisor) throws CalculatorException{
            return new Wrapper<>(rpcCalculator.divide(dividend, divisor));
    }

    @GetMapping("/power")
    @ApiOperation("return the exponentiation")
    public Wrapper<BigDecimal> power(@RequestParam BigDecimal base, @RequestParam BigDecimal exponent) throws CalculatorException{
            return new Wrapper<>(rpcCalculator.power(base, exponent));
    }
}
