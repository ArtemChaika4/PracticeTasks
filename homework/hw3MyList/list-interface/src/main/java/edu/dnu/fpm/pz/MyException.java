package edu.dnu.fpm.pz;

import java.time.LocalDate;
public class MyException extends Exception{
    public MyException(String message){
        super(message);
    }
    public void toLog(){
        System.out.printf("Дата: <%s> Місце: <%s> Тип: <%s> Опис: <%s>%n",
                LocalDate.now(), super.getStackTrace()[1], super.getClass(), super.getMessage());
    }
}
