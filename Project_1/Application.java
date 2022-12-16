import pl.poznan.put.calculator.Calculator;
import pl.poznan.put.calculator.CalculatorMenu;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Calculator calculator = new Calculator();
        CalculatorMenu menu = new CalculatorMenu(calculator, reader);

        menu.startMenu();

        menu.runLoop();

        reader.close();

    }
}
