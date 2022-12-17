import pl.poznan.put.calculator.SYCalculator;
import pl.poznan.put.calculator.CalculatorMenu;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        SYCalculator calculator = new SYCalculator();
        CalculatorMenu menu = new CalculatorMenu(calculator, reader);

        menu.startMenu();

        menu.runLoop();

        reader.close();

    }
}
