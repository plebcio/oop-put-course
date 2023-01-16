import java.util.ArrayList;
import java.util.Arrays;

public class Characters implements Sequence {
    private final String seq;


    @Override
    public String printedSeq() {
        return seq;
    }

    public Characters(String input){
        this.seq = input;
    }
}
