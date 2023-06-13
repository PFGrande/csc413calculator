package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    private int operandValue;
    /**
     * construct operand from string token.
     */
    public Operand(String token) {
        if (Operand.check(token)) {
            this.operandValue = Integer.parseInt(token);
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        this.operandValue = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return this.operandValue;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        //convert string to integer, exception indicated invalid operand
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException invalidOperand) {
            return false;
        }
    }
}
