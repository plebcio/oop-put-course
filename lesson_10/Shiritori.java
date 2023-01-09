import java.sql.SQLData;
import java.util.ArrayList;

public class Shiritori {
    private boolean game_over;
    final private ArrayList<String> words;

    Shiritori(){
        words = new ArrayList<>();
        game_over = false;
    }

    void play(String word) {
        word = word.toLowerCase();

        if (game_over){
            System.out.println("Game Already ended");
            System.out.println("And was not restarted");
            return;
        }

        if (words.size() == 0){
            words.add(word);
            return;
        }


        if (words.contains(word)){
            System.out.println("'" + word + "' has already been said");
            game_over = true;
            return;
        }

        String lastWord = words.get(words.size() - 1);
        String lastChar = lastWord.substring(lastWord.length() - 1);

        if (!word.substring(0, 1).equals(lastChar)) {
            System.out.println("'" + word + "' does not start with '" + lastChar + "'");
            game_over = true;
            return;
        }

        // is ok
        words.add(word);
    }

    void restart(){
        words.clear();
        game_over = false;
    }

    void printWords(){
        System.out.println("Game so far: "  + words);
    }

    boolean restartNeeded(){
        return game_over;
    }
}


















