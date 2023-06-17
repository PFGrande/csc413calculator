package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class ParenthesesOperator extends Operator {
    final int priority = 0;

    public ParenthesesOperator() {
    }

    @Override
    public int priority() {
        return this.priority;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return null;
    }
}
