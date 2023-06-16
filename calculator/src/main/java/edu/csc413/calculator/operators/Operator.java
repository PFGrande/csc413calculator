package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;
import java.util.HashMap;

public abstract class Operator {
    //
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators. MISSING

    // ALL subclasses of operator MUST be in their own file.

    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    //Static to not have to create multiple instances of the hashmap containing the different operators.
    private static HashMap<String, Operator> operators = new HashMap<>(); //Token, operator
    static {
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
        //Pontentially a class deadlock, will look into it. Believe it's because
        //the parent class is needed to initialize the children class
    }


    /**
     * retrieve the priority of an Operator
     * @return priority of an Operator as an int
     */
    public abstract int priority();

    /**
     * Abstract method to execute an operator given two operands.
     * @param operandOne first operand of operator
     * @param operandTwo second operand of operator
     * @return an operand of the result of the operation.
     */
    public abstract Operand execute(Operand operandOne, Operand operandTwo);

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token) {
        return Operator.operators.get(token);
    }

    
     /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {
        //UI does not allow characters other than the operators and operands to be used.
        //This means there is no need to check if the token is a character that is not an int or operator.
        try {
            Integer.parseInt(token);
            return false; // token is integer
        } catch (NumberFormatException invalidOperand) { //token not integer
//            if (token.length() > 1) {
//                return false;
//            }

            // check if token is an operator
            // token is an invalid character
            return operators.containsKey(token); // token is an operator, might cause problem with parentheses


        }

    }
}
