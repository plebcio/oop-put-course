public class Concatination implements  Sequence{

    private final Sequence sequence1;
    private final Sequence sequence2;


    public Concatination(Sequence seq1, Sequence seq2){

        this.sequence1 = seq1;
        this.sequence2 = seq2;

    }

    @Override
    public String printedSeq() {
        return sequence1.printedSeq() + sequence2.printedSeq();
    }
}
