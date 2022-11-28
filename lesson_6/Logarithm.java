public class Logarithm implements Number   {
    private final double base, argument;

    @Override
    public double doubleValue() {
        return Math.log(this.argument) / Math.log(this.base);
    }

    public Logarithm(double inputBase, double inputArgument) throws Exception{
        if (inputBase <= 0 || inputBase == 1){
            throw new Exception("Log base must be a positive number, not equal to 0 or 1");
        }
        if (inputArgument <= 0){
            throw new Exception("Log argument must be a positive nonzero number");
        }

        this.base = inputBase;
        this.argument = inputArgument;
    }
}