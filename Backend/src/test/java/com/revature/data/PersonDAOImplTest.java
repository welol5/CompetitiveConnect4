package com.revature.data;
import static org.junit.jupiter.api.Assertions.*;
import com.revature.beans.Person;
import org.junit.jupiter.api.Test;

import java.util.List;


public class PersonDAOImplTest {
    private PersonDAO personDao;
    @Test
    void getByUserIdTest() {
        PersonDAOFactory personDAOFactory = new PersonDAOFactory();
        personDao = personDAOFactory.getPersonDAO();
        Person person = personDao.getById(1);
        assertEquals(1, person.getId());

    }
}
