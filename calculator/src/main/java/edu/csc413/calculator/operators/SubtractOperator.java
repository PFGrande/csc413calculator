package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator {
    final int priority = 1;

    public SubtractOperator() {
    }
    @Override
    public int priority() {
        return this.priority;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int operandResult = operandOne.getValue() - operandTwo.getValue();
        return new Operand(operandResult);
    }
}
