import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

// String[] buttonValues = {
//     "AC",      // index 0
//     "+/-",     // index 1
//     "%",       // index 2
//     "÷",       // index 3
//     "7",       // index 4
//     "8",       // index 5
//     "9",       // index 6
//     "×",       // index 7
//     "4",       // index 8
//     "5",       // index 9
//     "6",       // index 10
//     "-",       // index 11
//     "1",       // index 12
//     "2",       // index 13
//     "3",       // index 14
//     "+",       // index 15
//     "0",       // index 16
//     ".",       // index 17
//     "√",       // index 18
//     "="        // index 19
// };

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customDarkBlue = new Color(42, 70, 119);
    Color customLightBlue = new Color(122, 179, 208);
    Color customMidBlue = new Color(51, 131, 177);
    Color customLightGrey = new Color(205, 205, 204);
    Color customTurquoise = new Color(115, 169, 181);

    String[]buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "=",
    };
    //Array
    String[] rightSymbols = {"÷","×","-","+","="};
    String[] topSymbols = {"AC", "+/-", "%"};


    JFrame frame = new JFrame("Calculator");
    JTextField displayLabel = new JTextField();
    JTextField historyLabel = new JTextField();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    //JTextField lets scroll so you can see more of history whereas JLabel only lets you see around 6 operators before cutting all the new stuff off with "..."

    //A+B, A-B, A*B, A/B
    //to save numbers that have been entered
    //keep trask of 2 numbers and operator
    String A = "0";
    String operator = null;
    String B = null;
    boolean startNewNumber = false;
    String history = "";
    boolean clearPressed = false;
    boolean skipZero = false;
    boolean justCleared = false;
    JButton[] buttons = new JButton[buttonValues.length];

    Calculator(){
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customDarkBlue);
        displayLabel.setForeground(customLightGrey);
        displayLabel.setFont(new Font("Comic Sans", Font.PLAIN, 50));
        displayLabel.setHorizontalAlignment(JTextField.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);
        displayLabel.setEditable(false);
        //displayLabel.setFocusable(false);
        //so you can't edit the JTextField since it's automatically editable but then it would take too long to link whatever the user inputs with the rest plus then they could type letters and that's just another headache


        historyLabel.setBackground(customTurquoise);
        historyLabel.setForeground(customLightGrey);
        historyLabel.setFont(new Font("Comic Sans", Font.PLAIN, 30));
        historyLabel.setHorizontalAlignment(JTextField.RIGHT);
        historyLabel.setText("");
        historyLabel.setOpaque(true);
        historyLabel.setEditable(false);
        //historyLabel.setFocusable(false);

        //so you can't edit the JTextField since it's automatically editable but then it would take too long to link whatever the user inputs with the rest plus then they could type letters and that's just another headache

        displayPanel.setLayout(new GridLayout(2, 1));

        displayPanel.add(historyLabel);
        displayPanel.add(displayLabel);

        frame.add(displayPanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customDarkBlue);
        frame.add(buttonsPanel, BorderLayout.CENTER);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            buttons[i] = button;

            String buttonValue = buttonValues[i];
            button.setFont(new Font("Comic Sans", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customDarkBlue));


            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightBlue);
                button.setForeground(customDarkBlue);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customMidBlue);
                button.setForeground(Color.black);
            }
            
            else {
                button.setBackground(customLightGrey);
                button.setForeground(Color.black);
            }


            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
            //action performed is mouse clicked and e refers to the action
                    JButton button = (JButton) e.getSource();
            //e is the action event and source is where the event comes from. event is a click and the source of the click is a JButton
                    String buttonValue = button.getText();

                    if (!buttonValue.equals("AC")) {
                        clearPressed = false;
                    }

                    System.out.println(buttonValue);
            //NUMBERS
            if ("0123456789".contains(buttonValue)){

                if (startNewNumber && operator == null) {
                    history = "";
                    historyLabel.setText("");
                }

                if (justCleared) {
                    displayLabel.setText(buttonValue);
                    justCleared = false;
                    startNewNumber = false;
                }

                            else if (startNewNumber) {
                                displayLabel.setText(buttonValue);
                                startNewNumber = false;
                            }

                            else if (displayLabel.getText().equals("0") || displayLabel.getText().equals("")) {
                                // || means OR in java
                                displayLabel.setText(buttonValue);
                                //you're not adding random stuff since its just 0 which has no added value
                            }

                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }

                        //DECIMAL
                        else if (buttonValue.equals(".")){
                            //don't want more than 1 decimal place, either 1 or none
                            if (!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                        
                        //OPERATORS
                        else if(Arrays.asList(rightSymbols).contains(buttonValue)){

                            if(buttonValue.equals("=")){

                                if(buttonValue.equals("=")) {
                                    if (operator == null) {
                                        return;
                                    }
                                }
                                B = displayLabel.getText();

                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                double result = calculate(numA, numB, operator);

                                if (Double.isNaN(result)) {
                                    displayLabel.setText("ERROR!");
                                    A = "0";
                                    operator = null;
                                    B = null;
                                    startNewNumber = true;
                                    return;
                                }

                               if (!skipZero) {
                                history += B + " = ";
                               }

                               else {
                                skipZero = false;
                               }

                                historyLabel.setText(history);

                                displayLabel.setText(removeZeroDecimal(result));

                                A = removeZeroDecimal(result);
                                B = null;
                                operator = null;
                                justCleared = false;
                                startNewNumber = true;

                            }

                            else {
                                //if there is an operator, calculate first
                            if(operator !=null && !startNewNumber){

                                B = displayLabel.getText();

                                //Save number before calculatins
                                //Only add user input

                                history += B + " " + buttonValue + " ";
                                justCleared = false;

                                historyLabel.setText(history);

                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                double result = calculate(numA, numB, operator);

                                A = removeZeroDecimal(result);
                                displayLabel.setText(A);
                            }

                            else {
                                if (!justCleared) {

                                A = displayLabel.getText();
                                history = A + " " + buttonValue + " ";
                                historyLabel.setText(history);

                                }

                            }

                                operator = buttonValue;
                                startNewNumber=true;
                            }
                        }

                        //TOP BUTTONS

                        else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                            if (buttonValue.equals("AC")) {
                            
                                if (!clearPressed) {
                                    //First AC press will only clear current number
                                    displayLabel.setText("0");

                                    history = "";
                                    historyLabel.setText("");

                                    A = "0";
                                    B = null;
                                    operator = null;

                                    justCleared = true;
                                    clearPressed = true;
                                    startNewNumber = true;
                                }

                            else {
                            //want to clear all if AC pressed twice
                            clearAll();
                            displayLabel.setText("0");
                            historyLabel.setText("");
                            clearPressed = false;
                            }
                        }
                            else if(buttonValue.equals("+/-")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                            else if (buttonValue.equals("%")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /=100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }    
                
                    //SQUARE ROOT
                    else if (buttonValue.equals("√")) {
                        double number = Double.parseDouble(displayLabel.getText());
                        //converts string into a decimal number (double)
                        double result = Math.sqrt(number);
                        //calculates square root
                        displayLabel.setText(removeZeroDecimal(result));
                        //displays number as text again 
                    }
                }

            // if ("+-×÷".contains(buttonValue)) {
            //         if (operator != null) {
            //                     B = displayLabel.getText();

            //                     double numA = Double.parseDouble(A);
            //                     double numB = Double.parseDouble(B);

            //                     double result = calculate(numA, numB, operator);

            //                     A = removeZeroDecimal(result);
            //                     displayLabel.setText(A);
            //                 }
            //                 else {
            //                     A = displayLabel.getText();
            //                 }
            //         }
            //     }
            });     
}

        frame.setFocusable(true);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());

                //Numbers
                for (int i = 0; i < buttonValues.length; i++) {
                    if (buttonValues[i].equals(key)) {
                        buttons[i].doClick();
                        return;
                    }
                }

                //Operators
                if (key.equals("*")) {
                    buttons[7].doClick(); //×
                }

                else if (key.equals("/")) {
                    buttons[3].doClick(); //÷
                }

                else if (key.equals("+")) {
                    buttons[15].doClick(); //+
                }

                else if (key.equals("-")) {
                    buttons[11].doClick(); //-
                }

                else if (key.equals(".")) {
                    buttons[17].doClick(); //.
                }

                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttons[19].doClick(); //=
                }
            
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    deleteLastDigit(); //AC
                }

                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttons[0].doClick();
                }

//CAPITALS ARE CASE-SENSITIVE, MAKE SURE THEY'RE RIGHT!!!
            }
        });

        frame.setFocusable(true);
        frame.setVisible(true);
        frame.requestFocusInWindow();

                } //closes Calculator()
                double calculate(double numA, double numB, String operator) {
                    if (operator == null) {
                        return numA;
                    }
                    else if (operator.equals("+")) {
                        return numA+numB;
                    }
                    else if (operator.equals("-")) {
                        return numA-numB;
                    }
                    else if (operator.equals("×")) {
                        return numA*numB;
                    }
                    else if (operator.equals("÷")) {
                        if (numB == 0) {
                        return Double.NaN;

                        } 
                        else {
                        return numA/numB;
                        }
                    }
                        return Double.NaN;

                }
    void clearAll() {
        A = "0";
        operator = null;
        B = null;
        startNewNumber = false;
        history = "";
    }

    void deleteLastDigit(){
                    String current = displayLabel.getText();

                    if(current.length() > 1) {
                        displayLabel.setText(current.substring(0,current.length()-1));
                    }

                    else {
                        displayLabel.setText("0");
                    }

                    //refresh history
                    if(operator !=null) {
                        history = A + " " + operator + " ";
                        historyLabel.setText(history);
                    }
                }

    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
