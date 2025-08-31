package edu.dnu.fpm.pz.dao;

import edu.dnu.fpm.pz.entity.Employer;
import edu.dnu.fpm.pz.entity.Vacancy;
import edu.dnu.fpm.pz.myexceptions.MyException;

public class Validator {
    final private static String PHONE_REGEX = "^(\\+?\\d{2})?\\d{10}$";
    final private static String NAME_REGEX = "^([A-Za-zА-Яа-яЇїІіЄєҐґ]\\'?){2,30}$";
    final private static int MIN_POSITION_LENGTH = 3;
    final private static int MAX_POSITION_LENGTH = 100;
    final private static int MIN_SALARY = 2000;
    final private static int MAX_SALARY = 1000000;
    private static boolean validateLength(int length, int lowerBound, int upperBound) {
        return length > upperBound || length < lowerBound;
    }
    private static boolean validateMatching(String value, String regex){
        return !value.matches(regex);
    }
    public static void validateNumber(Employer employer) throws MyException {
        if (validateMatching(employer.getPhone(), PHONE_REGEX)) {
            throw new MyException("Incorrect phone number: " + employer.getPhone());
        }
    }
    public static void validateName(Employer employer) throws MyException {
        if (validateMatching(employer.getName(), NAME_REGEX)) {
            throw new MyException("Incorrect name: " + employer.getName());
        }
    }
    public static void validateSurname(Employer employer) throws MyException {
        if (validateMatching(employer.getSurname(), NAME_REGEX)) {
            throw new MyException("Incorrect surname: " + employer.getSurname());
        }
    }
    public static void validatePosition(Vacancy vacancy) throws MyException {
        if (validateLength(vacancy.getPosition().length(),
                MIN_POSITION_LENGTH, MAX_POSITION_LENGTH)) {
            throw new MyException("Incorrect position: " + vacancy.getPosition());
        }
    }
    public static void validateSalary(Vacancy vacancy) throws MyException {
        if (vacancy.getSalary() > MAX_SALARY || vacancy.getSalary() < MIN_SALARY) {
            throw new MyException("Incorrect salary: " + vacancy.getSalary());
        }
    }
}
