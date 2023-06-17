package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer expressionTokenizer;
  private final String delimiters = " +/*-^()"; // added parentheses

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int evaluateExpression(String expression) throws InvalidTokenException {
    String expressionToken;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens too. But, we'll need to remember to filter out spaces.
    this.expressionTokenizer = new StringTokenizer( expression, this.delimiters, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators

    while ( this.expressionTokenizer.hasMoreTokens() ) {// runs out of tokens before finishing calculation
      // filter out spaces & move to next token
      if ( !( expressionToken = this.expressionTokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( expressionToken )) { // check if token is operand
          operandStack.push( new Operand( expressionToken ));
          
        } else {
          if (expressionToken.equals("(")) { // add parenthesis operator into operator stack
            operatorStack.push( Operator.getOperator(expressionToken));
            
            continue;
            
          } else if ( expressionToken.equals(")")) { // evaluate expression within parentheses
            while (!operatorStack.isEmpty() && operatorStack.peek().priority() != 0) { // priority of 0 indicates parentheses
              operandStack.push(evaluate());
              
            }
            operatorStack.pop(); // remove remaining left parenthesis
            
            continue;
            
          } else if ( ! Operator.check( expressionToken )) { // check if token is operator
            throw new InvalidTokenException(expressionToken); // not an operator, throw exception
          }

          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.
          Operator newOperator = Operator.getOperator(expressionToken); // gets operator object based on token

          // run while the operator at the top of the stack is larger than the newOperator. If the newOperator is smaller, place it
          // in the stack for its execution to be queued.

          // empty out stacks
          while (!operatorStack.isEmpty() && operatorStack.peek().priority() >= newOperator.priority() && operandStack.size() >= 2) { // at least 2 operands necessary
            // note that when we eval the expression 1 - 2 we will
            // push the 1 then the 2 and then do the subtraction operation
            // This means that the first number to be popped is the
            // second operand, not the first operand - see the following code
            operandStack.push( evaluate() );
          }

          operatorStack.push( newOperator ); //runs if operator is empty
        }
      }
    }

    // tokenizer is empty, stacks contain operands and operators
    while (!operatorStack.isEmpty() && operandStack.size() >= 2) {
      operandStack.push(evaluate());
    }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty;
    // Suggestion: create a method that processes the operator stack until empty.

    return operandStack.pop().getValue(); //returns the value calculated by the expression
  }

  public Operand evaluate() { // returns the calculation between two operands and an operator from the stack
    Operator operatorFromStack = operatorStack.pop();
    Operand operandTwo = operandStack.pop();
    Operand operandOne = operandStack.pop();
    return operatorFromStack.execute(operandOne, operandTwo);

  }
}
