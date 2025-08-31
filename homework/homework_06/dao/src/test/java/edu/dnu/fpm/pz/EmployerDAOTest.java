package edu.dnu.fpm.pz;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class EmployerDAOTest {
    private final InterfaceDAO<Employer> employerDAO = new EmployerDAO();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void createEmployerTest() throws Exception {
        //GIVEN
        Employer employer = new Employer("John", "Smith", "+380967819240");
        //WHEN
        employerDAO.create(employer);
        Employer result = employerDAO.read().get(employerDAO.read().size() - 1);
        //THEN
        assertEquals(employer, result);
    }

    @Test
    public void createEmployersTest() throws Exception {
        //GIVEN
        List<Employer> expected = new ArrayList<>();
        expected.add(new Employer("John", "Smith", "+380967819240"));
        expected.add(new Employer("Іван", "Іваненко", "+380777777777"));
        //WHEN
        employerDAO.create(expected);
        List<Employer> result = new ArrayList<>();
        result.add(employerDAO.read().get(employerDAO.read().size() - 2));
        result.add(employerDAO.read().get(employerDAO.read().size() - 1));
        //THEN
        assertEquals(expected, result);
    }

    @Test
    public void deleteEmployerTest() throws Exception {
        //GIVEN
        int currentSize = employerDAO.read().size();
        //WHEN
        employerDAO.delete(employerDAO.read().get(currentSize - 1).getId());
        int resultSize = employerDAO.read().size();
        //THEN
        assertEquals(currentSize - 1, resultSize);
    }
    @Test
    public void updateEmployerTest() throws Exception {
        //GIVEN
        Employer employer = new Employer("Dmytro", "Hordienko", "+380967812520");
        employerDAO.create(employer);
        int currentSize = employerDAO.read().size();
        int lastId = employerDAO.read().get(currentSize - 1).getId();
        Employer expected = new Employer(lastId, "Dmytro", "Matvienko",
                "+380967812520");
        //WHEN
        employerDAO.update(expected);
        Employer result = employerDAO.read().get(currentSize - 1);
        //THEN
        assertEquals(expected, result);
    }

    @Test
    public void updateEmployersTest() throws Exception {
        //GIVEN
        employerDAO.create(new Employer("John", "Smith", "+380967819240"));
        employerDAO.create(new Employer("Іван", "Іваненко", "+380777777777"));
        int currentSize = employerDAO.read().size();
        int firstId = employerDAO.read().get(currentSize - 2).getId();
        int secondId = employerDAO.read().get(currentSize - 1).getId();
        List<Employer> expected = new ArrayList<>();
        expected.add(new Employer(firstId, "Sam", "Johnson", "380967819240"));
        expected.add(new Employer(secondId, "Іванка", "Іваненко", "+380777777777"));
        //WHEN
        employerDAO.update(expected);
        List<Employer> result = new ArrayList<>();
        result.add(employerDAO.read().get(currentSize - 2));
        result.add(employerDAO.read().get(currentSize - 1));
        //THEN
        assertEquals(expected, result);
    }
}