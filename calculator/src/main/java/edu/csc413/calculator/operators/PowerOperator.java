package edu.csc413.calculator.operators;

import java.lang.Math;
import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    final int priority = 3;

    PowerOperator() {
    }

    @Override
    public int priority() {
        return this.priority;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        //converts Math.pow() result to int
        int operandResult = (int) Math.pow(operandOne.getValue(), operandTwo.getValue());
        return new Operand(operandResult);
    }
}
