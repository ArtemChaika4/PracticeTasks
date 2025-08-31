package edu.dnu.fpm.pz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class ValidatorTest {
    Employer employer;
    Vacancy vacancy;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        employer = new Employer();
        vacancy = new Vacancy();
    }

    @Test
    public void validateNumberTestWithCountryCode() throws MyException {
        String phone = "+380666666666";
        employer.setPhone(phone);

        Validator.validateNumber(employer);
    }

    @Test
    public void validateNumberTestNormal() throws MyException {
        String phone = "0666666666";
        employer.setPhone(phone);

        Validator.validateNumber(employer);
    }

    @Test
    public void validateNumberTestNotMatched() throws MyException {
        String phone = "666666666a";
        employer.setPhone(phone);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect phone number: " + phone);

        Validator.validateNumber(employer);
    }

    @Test
    public void validateNameTestWithLettersOnly() throws MyException {
        String name = "Артем";
        employer.setName(name);

        Validator.validateName(employer);
    }

    @Test
    public void validateNameTestWithApostrophe() throws MyException {
        String name = "Ім'я";
        employer.setName(name);

        Validator.validateName(employer);
    }

    @Test
    public void validateNameTestMinLength() throws MyException {
        String name = "Ян";
        employer.setName(name);

        Validator.validateName(employer);
    }

    @Test
    public void validateNameTestMaxLength() throws MyException {
        String name = "Едуардолеонардоконстантинослав";
        employer.setName(name);

        Validator.validateName(employer);
    }

    @Test
    public void validateNameTestLongerThanMaxLength() throws MyException {
        String name = "Едуардолеонардоконстантинослава";
        employer.setName(name);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect name: " + name);

        Validator.validateName(employer);
    }

    @Test
    public void validateNameTestShorterThanMinLength() throws MyException {
        String name = "Я";
        employer.setName(name);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect name: " + name);

        Validator.validateName(employer);
    }


    @Test
    public void validateSurnameTestWithLettersOnly() throws MyException {
        String surname = "Чайка";
        employer.setSurname(surname);

        Validator.validateSurname(employer);
    }

    @Test
    public void validateSurnameTestWithApostrophe() throws MyException {
        String surname = "Хом'як";
        employer.setSurname(surname);

        Validator.validateSurname(employer);
    }

    @Test
    public void validateSurnameTestMinLength() throws MyException {
        String surname = "По";
        employer.setSurname(surname);

        Validator.validateSurname(employer);
    }

    @Test
    public void validateSurnameTestMaxLength() throws MyException {
        String surname = "Архієволокотачерепопінаковська";
        employer.setSurname(surname);

        Validator.validateSurname(employer);
    }

    @Test
    public void validateSurnameTestLongerThanMaxLength() throws MyException {
        String surname = "Архієволокотачерепопінаковський";
        employer.setSurname(surname);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect surname: " + surname);

        Validator.validateSurname(employer);
    }

    @Test
    public void validateSurnameTestShorterThanMinLength() throws MyException {
        String surname = "Ї";
        employer.setSurname(surname);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect surname: " +surname);

        Validator.validateSurname(employer);
    }

    @Test
    public void validatePositionTestNormalLength() throws MyException {
        String position = "Старший викладач";
        vacancy.setPosition(position);

        Validator.validatePosition(vacancy);
    }

    @Test
    public void validatePositionTestMinLength() throws MyException {
        String position = "Коп";
        vacancy.setPosition(position);

        Validator.validatePosition(vacancy);
    }

    @Test
    public void validatePositionTestMaxLength() throws MyException {
        String position = "Завідувач кафедрою вивчення іноземних мов, мов, що вже вимерли та української філологої" +
                "Кандидат наук";
        vacancy.setPosition(position);

        Validator.validatePosition(vacancy);
    }

    @Test
    public void validatePositionTestLongerThanMaxLength() throws MyException {
        String position = "Завідувач кафедрою вивчення іноземних мов, мов, що вже вимерли та української філологої " +
                "Кандидат наук ";
        vacancy.setPosition(position);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect position: " + position);

        Validator.validatePosition(vacancy);
    }

    @Test
    public void validatePositionTestShorterThanMaxLength() throws MyException {
        String position = "За";
        vacancy.setPosition(position);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect position: " + position);

        Validator.validatePosition(vacancy);
    }

    @Test
    public void validateSalaryTestNormalLength() throws MyException {
        int salary = 20000;
        vacancy.setSalary(salary);

        Validator.validateSalary(vacancy);
    }

    @Test
    public void validateSalaryTestMinLength() throws MyException {
        int salary = 2000;
        vacancy.setSalary(salary);

        Validator.validateSalary(vacancy);
    }

    @Test
    public void validateSalaryTestMaxLength() throws MyException {
        int salary = 1_000_000;
        vacancy.setSalary(salary);

        Validator.validateSalary(vacancy);
    }

    @Test
    public void validateSalaryTestShorterThanMinLength() throws MyException {
        int salary = 100;
        vacancy.setSalary(salary);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect salary: " + salary);

        Validator.validateSalary(vacancy);
    }

    @Test
    public void validateSalaryTestLongerThanMaxLength() throws MyException {
        int salary = 1_000_500;
        vacancy.setSalary(salary);

        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect salary: " + salary);

        Validator.validateSalary(vacancy);
    }

}