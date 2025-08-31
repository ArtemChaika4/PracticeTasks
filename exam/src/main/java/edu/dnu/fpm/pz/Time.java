package edu.dnu.fpm.pz;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    private void validateTime(int hours, int minutes, int seconds) throws MyException {
        Validator.validateHours(hours);
        Validator.validateMinutes(minutes);
        Validator.validateSeconds(seconds);
    }

    public void setTime(int hours, int minutes, int seconds) throws MyException {
        validateTime(hours, minutes, seconds);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void shiftHours(int hours){
        int period = Validator.HOURS_MAX_VALUE;
        int newHours = (this.hours + hours) % period;
        if(newHours < 0){
            newHours += period;
        }
        this.hours = newHours;
    }

    public void shiftMinutes(int minutes){
        int period = Validator.MINUTES_MAX_VALUE;
        int hoursShift = (this.minutes + minutes) / period;
        int newMinutes = (this.minutes + minutes) % period;
        if(newMinutes < 0){
            newMinutes += period;
            hoursShift--;
        }
        shiftHours(hoursShift);
        this.minutes = newMinutes;
    }

    public void shiftSeconds(int seconds){
        int period = Validator.SECONDS_MAX_VALUE;
        int minutesShift = (this.seconds + seconds) / period;
        int newSeconds = (this.seconds + seconds) % period;
        if(newSeconds < 0){
            newSeconds += period;
            minutesShift--;
        }
        shiftMinutes(minutesShift);
        this.seconds = newSeconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) throws MyException {
        Validator.validateHours(hours);
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) throws MyException {
        Validator.validateMinutes(minutes);
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) throws MyException {
        Validator.validateSeconds(seconds);
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Time{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
