package edu.dnu.fpm.pz;

public class Validator {
    public final static int SECONDS_MIN_VALUE = 0;
    public final static int SECONDS_MAX_VALUE = 60;
    public final static int MINUTES_MIN_VALUE = 0;
    public final static int MINUTES_MAX_VALUE = 60;
    public final static int HOURS_MIN_VALUE = 0;
    public final static int HOURS_MAX_VALUE = 24;

    public static void validateSeconds(int seconds) throws MyException {
        if(seconds < SECONDS_MIN_VALUE || seconds > SECONDS_MAX_VALUE){
            throw new MyException("Incorrect seconds value: " + seconds);
        }
    }

    public static void validateMinutes(int minutes) throws MyException {
        if(minutes < MINUTES_MIN_VALUE || minutes > MINUTES_MAX_VALUE){
            throw new MyException("Incorrect minutes value: " + minutes);
        }
    }

    public static void validateHours(int hours) throws MyException {
        if(hours < HOURS_MIN_VALUE || hours > HOURS_MAX_VALUE){
            throw new MyException("Incorrect hours value: " + hours);
        }
    }
}
