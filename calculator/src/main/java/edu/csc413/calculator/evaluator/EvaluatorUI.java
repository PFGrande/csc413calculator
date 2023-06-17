package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.Operator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EvaluatorUI extends JFrame implements ActionListener {

    private JTextField expressionTextField = new JTextField();
    private JPanel buttonPanel = new JPanel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
        "7", "8", "9", "+",
        "4", "5", "6", "-", 
        "1", "2", "3", "*", 
        "(", "0", ")", "/", 
        "C", "CE", "=", "^"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private JButton[] buttons = new JButton[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout()); //affects evaluatorUI, setLayout is a function of jFrame
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 24));
        this.expressionTextField.setHorizontalAlignment(JTextField.CENTER);

        add(expressionTextField, BorderLayout.NORTH); //add is a function of JFrame
        expressionTextField.setEditable(false); //can not be edited by the user

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        JButton tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new JButton(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 24));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) { //every time button is pressed
        //actionEventObject is the event triggered after pressing the button
        //toString method has 2 scenarios 1 for string the other for int?
        //object is parent of every object
        //cmd = button being pressed, .getActionCommand method returns the button being pressed
        //explore the API
        //String commandClicked = actionEventObject.getActionCommand();
        //this.expressionTextField.setText(this.expressionField.getText() + commandClicked);
        //getSource returns the object the button represents, can also be used to determine input

        System.out.println(actionEventObject.getActionCommand());
        // display current text + button pressed

        switch (actionEventObject.getActionCommand()) {
            case "=":
                try {
                    Evaluator evaluator = new Evaluator();
                    int actualResult = evaluator.evaluateExpression(expressionTextField.getText());
                    expressionTextField.setText(Integer.toString(actualResult));
                } catch (InvalidTokenException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case "CE":
                char availableOperators[] = {'+', '-', '*', '/', '^', '(', ')'};
                int lastOperIndex = -1;
                if (expressionTextField.getText().length() > 0) {
                    for (char operator:availableOperators) { // worst case: latest operator near front of expression
                        if ((lastOperIndex = expressionTextField.getText().lastIndexOf(operator)) != -1) {
                            break;
                        }
                    }

                    if (lastOperIndex != -1) { // delete up until latest operator
                        expressionTextField.setText(expressionTextField.getText().substring(0, lastOperIndex + 1));
                        // up until means after it?
                        // maybe deleting AT the operator would be more useful, otherwise,
                        // anything before the last operator unable to be deleted
                    }
                }
                break;
            case "C": // clear text field
                expressionTextField.setText("");
                break;
            default:
                expressionTextField.setText(expressionTextField.getText() + actionEventObject.getActionCommand());
                break;
        }


        //plan:
        /*
        * display expression on screen
        * as a string, save the expression then run it through the evaluator after = is pressed
        *
        *
        *
        * */

    }
}
