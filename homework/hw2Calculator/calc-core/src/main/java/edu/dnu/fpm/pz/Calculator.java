package edu.dnu.fpm.pz;

public class Calculator implements Calculable {
    @Override
    public double add(double value, double anotherValue) {
        return value + anotherValue;
    }
    @Override
    public double subtract(double value, double anotherValue) {
        return value - anotherValue;
    }
    @Override
    public double multiply(double value, double anotherValue) {
        return value * anotherValue;
    }
    @Override
    public double divide(double value, double anotherValue) {
        double result;
        try {
            result = value / anotherValue;
        }catch (ArithmeticException exception){
            System.out.println("Division by zero");
            result = 0;
        }

        return result;
    }
}
