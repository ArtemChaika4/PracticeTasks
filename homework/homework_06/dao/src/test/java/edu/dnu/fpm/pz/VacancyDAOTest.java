package edu.dnu.fpm.pz;

import org.junit.*;
import org.junit.rules.ExpectedException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class VacancyDAOTest {
    private final static InterfaceDAO<Vacancy> vacancyDAO = new VacancyDAO();
    private final static InterfaceDAO<Employer> employerDAO = new EmployerDAO();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @BeforeClass
    public static void prepare() throws MyException {
        Employer employer = new Employer("John", "Doe", "+380961234567");
        employerDAO.create(employer);
        List<Employer> employers = employerDAO.read();
        int employerId = employers.get(employers.size()-1).getId();
        employer.setId(employerId);
        Vacancy vacancy = new Vacancy(employer, "teacher",
                "teacher", 12000, Date.valueOf("2023-01-25"));
        vacancyDAO.create(vacancy);
    }

    @Test
    public void createVacancyTest() throws Exception {
        //GIVEN
        Employer employer = employerDAO.read().get(0);
        Vacancy expected = new Vacancy(employer, "teacher",
                "teacher", 20000, Date.valueOf("2023-01-20"));
        //WHEN
        vacancyDAO.create(expected);
        Vacancy result = vacancyDAO.read().get(vacancyDAO.read().size() - 1);
        //THEN
        assertEquals(expected, result);
    }
    @Test
    public void createVacanciesTest() throws Exception {
        //GIVEN
        Employer employer = employerDAO.read().get(0);
        List<Vacancy> expected = new ArrayList<>();
        expected.add(new Vacancy(employer, "Міністр освіти та науки України",
                "Економіст", 25000, Date.valueOf("2023-01-22")));
        expected.add(new Vacancy(employer, "Прикордонник",
                "Вислів навіки", 21000, Date.valueOf("2023-01-15")));
        //WHEN
        vacancyDAO.create(expected);
        List<Vacancy> result = new ArrayList<>();
        result.add(vacancyDAO.read().get(vacancyDAO.read().size() - 2));
        result.add(vacancyDAO.read().get(vacancyDAO.read().size() - 1));
        //THEN
        assertEquals(expected, result);
    }
    @Test
    public void deleteVacancyTest() throws Exception {
        //GIVEN
        int expected = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
        Employer employer = employerDAO.read().get(0);
        Vacancy vacancy = new Vacancy(employer, "Керівник Офісу президента України",
                "Юрист", 20000, Date.valueOf("2023-01-18"));
        vacancyDAO.create(vacancy);
        //WHEN
        vacancyDAO.delete(vacancyDAO.read().get(
                vacancyDAO.read().size() - 1).getId());
        int result = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
        //THEN
        assertEquals(expected, result);
    }
    @Test
    public void updateVacancyTest() throws Exception {
        //GIVEN
        Employer employer = employerDAO.read().get(0);
        int lastId = vacancyDAO.read().get(vacancyDAO.read().size() - 1).getId();
        Vacancy expected = new Vacancy(lastId, employer,
                "Прем'єр-міністр Великобританії",
                "В минулому комік", 99000, Date.valueOf("2023-01-03"));
        //WHEN
        vacancyDAO.update(expected);
        Vacancy result = vacancyDAO.read().get(vacancyDAO.read().size() - 1);
        //THEN
        assertEquals(expected, result);
    }

    @Test
    public void updateVacanciesTest() throws Exception {
        //GIVEN
        int employersSize = employerDAO.read().size();
        Employer firstEmployer = new Employer(employersSize + 1, "Іван", "Іваненков", "+380666666666");
        Employer secondEmployer = new Employer(employersSize + 2, "Іван", "Іваненко", "+380666666666");
        employerDAO.create(firstEmployer);
        employerDAO.create(secondEmployer);

        Vacancy vacancy = new Vacancy(
                firstEmployer, "Керівник Офісу президента України",
                "Юрист", 20000, Date.valueOf("2023-01-18")
        );
        vacancyDAO.create(vacancy);

        int currentSize = vacancyDAO.read().size();
        int firstId = vacancyDAO.read().get(currentSize - 2).getId();
        int secondId = vacancyDAO.read().get(currentSize - 1).getId();

        List<Vacancy> expected = new ArrayList<>();
        expected.add(new Vacancy(firstId, firstEmployer,
                "Актор", "Чудовий", 66000, Date.valueOf("2023-03-03")));
        expected.add(new Vacancy(secondId, secondEmployer,
                "Вчитель", "Вищий навчальний заклад", 10000, Date.valueOf("2023-02-12")));

        //WHEN
        vacancyDAO.update(expected);

        List<Vacancy> result = new ArrayList<>();
        result.add(vacancyDAO.read().get(currentSize - 2));
        result.add(vacancyDAO.read().get(currentSize - 1));

        //THEN
        assertEquals(expected, result);
    }
}