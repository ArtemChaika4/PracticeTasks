package edu.dnu.fpm.pz;

public class Application
{
    public static void main( String[] args )
    {
        if (args.length < 3) {
            System.err.println("Not enough parameters!");
            return;
        }

        Calculable calculator = new Calculator();

        double firstArgument;
        try {
            firstArgument = Double.parseDouble(args[0]);
        } catch (Exception exception) {
            System.err.println("Invalid first argument!");
            return;
        }

        double secondArgument;
        try {
            secondArgument = Double.parseDouble(args[1]);
        } catch (Exception e) {
            System.err.println("Invalid second argument!");
            return;
        }

        double result;
        switch (args[2]) {
            case "+":
                result = calculator.add(firstArgument, secondArgument);
                break;
            case "-":
                result = calculator.subtract(firstArgument, secondArgument);
                break;
            case "*":
                result = calculator.multiply(firstArgument, secondArgument);
                break;
            case "/":
                result = calculator.divide(firstArgument, secondArgument);
                break;
            default: {
                System.err.println("Invalid operator!");
                return;
            }
        }

        System.out.println("first number = " + firstArgument + " second number = " + secondArgument +
                " operator = " + args[2] + " result = " + result);
    }
}
