public class LowerCased implements Sequence {

    private final Sequence sequence;

    public LowerCased(Sequence seq){
        this.sequence = seq;
    }

    @Override
    public String printedSeq() {
        return sequence.printedSeq().toLowerCase();
    }
}




