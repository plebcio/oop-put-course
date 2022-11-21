public class Square implements Figure {
    private final int sideLen;

    @Override
    public int cirumference(){
        return 4*this.sideLen;
    }

    @Override
    public int area(){
        return this.sideLen * this.sideLen;
    }

    @Override
    public Square scaleX(int x){
        return new Square(this.sideLen * x);
    }

    public Square(int sideLen){
        this.sideLen = sideLen;
    }
}
