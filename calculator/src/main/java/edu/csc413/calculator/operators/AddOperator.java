package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;
public class AddOperator extends Operator{
    final int priority = 1;

    public AddOperator() {
    }

    @Override
    public int priority() {
        return this.priority;
    }

    //returns new operand with value of two added operands
    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int operandResult = operandOne.getValue() + operandTwo.getValue();
        return new Operand(operandResult);
    }



}
