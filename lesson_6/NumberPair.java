public class NumberPair {
    private final int a;
    private final int b;

    public NumberPair(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int max(){
        return a > b ? a : b;
    }

    public int min(){
        return a < b ? a : b;
    }

    public float avg(){
        return ( (float)(a+b) )/2;
    }
}
