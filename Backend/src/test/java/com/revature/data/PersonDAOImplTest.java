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
        Person person = new Person();
        person = personDao.getById(2);
        Person p = new Person();
        p.setId(2);
        p.setPassword("test");
        p.setUsername("test");
        p.setRank(1000);
        assertEquals(p, person);

    }
}
