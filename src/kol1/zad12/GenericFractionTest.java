package kol1.zad12;

import java.util.Scanner;

class ZeroDenominatorException extends Exception {
    public ZeroDenominatorException(String message) {
        super(message);
    }
}

class GenericFraction<T extends Number, U extends Number> {

    public static final double EPSILON = 1e-9;

    private T numerator;
    private U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if (denominator.doubleValue() == 0) {
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public T getNumerator() {
        return numerator;
    }

    public U getDenominator() {
        return denominator;
    }

    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf)
            throws ZeroDenominatorException {
        double n1 = numerator.doubleValue();
        double d1 = denominator.doubleValue();
        double n2 = gf.getNumerator().doubleValue();
        double d2 = gf.getDenominator().doubleValue();
        return new GenericFraction<>(n1 * d2 + n2 * d1, d1 * d2);
    }

    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    // greatest common divisor of two (possibly non-integer) values
    private double gcd(double a, double b) {
        if (b < EPSILON) {
            return a;
        }
        return gcd(b, a % b);
    }

    @Override
    public String toString() {
        double n = numerator.doubleValue();
        double d = denominator.doubleValue();
        // keep the sign in the numerator
        if (d < 0) {
            n = -n;
            d = -d;
        }
        double divisor = gcd(Math.abs(n), d);
        return String.format("%.2f / %.2f", n / divisor, d / divisor);
    }
}

public class GenericFractionTest {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
        	GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
        	GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
        	GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }

}
