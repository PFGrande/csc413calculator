package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class DivideOperator extends Operator {
    final int priority = 2;

    public DivideOperator() {
    }

    @Override
    public int priority() {
        return this.priority;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int operandResult = operandOne.getValue() / operandTwo.getValue();
        return new Operand(operandResult);
    }
}
