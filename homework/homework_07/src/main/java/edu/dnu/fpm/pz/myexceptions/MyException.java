package edu.dnu.fpm.pz.myexceptions;

import java.time.LocalDateTime;

public class MyException extends Exception {
    public MyException(String message, Throwable e) {
        super(message, e);
    }
    public MyException(String message) {
        super(message);
    }
    public void toLog() {
        String message = "Date: " + LocalDateTime.now() +
                " Class and method: " + getStackTrace()[getStackTrace().length - 1] +
                " Type: " + getClass() +
                " Text: " + getMessage();
        System.out.println(message);
    }
}
