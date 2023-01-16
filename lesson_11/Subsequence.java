public class Subsequence implements Sequence {

    private final Sequence sequence;
    private final int begin;
    private final int end;


    public Subsequence(Sequence seq, int begin, int end){
        this.begin = begin;
        this.end = end;
        this.sequence = seq;
    }

    @Override
    public String printedSeq() {
        return sequence.printedSeq().substring(begin, end);
    }
}
