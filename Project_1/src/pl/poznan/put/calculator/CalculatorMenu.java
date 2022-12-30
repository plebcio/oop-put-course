package pl.poznan.put.calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculatorMenu implements Menu {

    final private SYCalculator calculator;
    final private Scanner reader;

    @Override
    public void startMenu(){
        System.out.println("pl.poznan.put.calculator.Expression calculator");
        System.out.println("Usage:");
        System.out.println("    Type expression for evaluation");
        System.out.println("    Type !his for history of input");
        System.out.println("    Type !help for detailed instructions");
        System.out.println("    Type ans previous answer");
        System.out.println("    Type an empty line to exit");
    }

    @Override
    public void runLoop(){
        while (true) {
            // get input
            System.out.println("\nEnter expression to evaluation: ");
            System.out.print("> ");
            String input = reader.nextLine();

            if (input.isEmpty()){
                break;
            }
            else if (input.equals("!his")){
                ArrayList<String> history = calculator.expressionHistory();
                for (String line: history){
                    System.out.println(line);
                }
                continue;
            } else if (input.equals("!help")) {
                System.out.println("Supported Operators: ");
                System.out.println("    +, -, *, / , ^");
                System.out.println("Supported Single Argument Functions: ");
                System.out.println("    sin, cos, ln, exp, ");
                System.out.println("Function use syntax: ");
                System.out.println("    function('argument')");
                System.out.println("    or  function 'argument' ");
                System.out.println("    eg: sin(ln(13)) <=> sin ln 23");
                System.out.println("Supported Constants: ");
                System.out.println("    e, pi");
                continue;
            }

            try {
                double value = calculator.expressionValue(input);
                System.out.println(" = " + value);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public CalculatorMenu(SYCalculator calc, Scanner reader){
        this.calculator = calc;
        this.reader = reader;
    }


}
