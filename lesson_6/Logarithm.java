public class Logarithm implements Number   {
    private final double base, argument;

    @Override
    public double doubleValue() throws Exception {
        if (base <= 0 || base == 1){
            throw new Exception("Log base must be a positive number, not equal to 0 or 1");
        }
        if (argument <= 0){
            throw new Exception("Log argument must be a positive nonzero number");
        }

        return Math.log(this.argument) / Math.log(this.base);
    }

    public Logarithm(double inputBase, double inputArgument) {
        this.base = inputBase;
        this.argument = inputArgument;
    }
}