package com.revature.data;

import com.revature.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {
    //public Person add(Person p);
    public Person findByUsername(String username);
    //public Person getById(Integer id);
}
