package com.revature.data;
import static org.junit.jupiter.api.Assertions.*;
import com.revature.beans.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonDAOImplTest {
    @Autowired
    private PersonDAO personDao;

    @Test
    public void getByUserIdTest() {
        Person person = new Person();
        person.setUsername("test");
        person.setPassword("test");
        person.setRank(1000);
        personDao.save(person);
        person = personDao.getOne(1);
        Person p = new Person();
        p.setId(1);
        p.setPassword("test");
        p.setUsername("test");
        p.setRank(1000);
        assertEquals(p, person);

    }
}
