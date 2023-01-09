import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Shiritori myGame = new Shiritori();

        while(true){
            System.out.println("Input a word: ");
            String input = reader.nextLine();

            myGame.play(input);

            if (myGame.restartNeeded()){

                System.out.println("Game ended with: ");
                myGame.printWords();
                System.out.println( input + " - Incorrect");


                System.out.println("Do you want to play again? [y/n]");
                String ans = reader.nextLine();
                if (ans.toLowerCase().charAt(0) != 'y'){
                    return;
                }

                myGame.restart();
                System.out.println("Game was restarted");

            }

        }
    }
}