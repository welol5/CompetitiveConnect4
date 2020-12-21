package com.revature.data;

import com.revature.beans.Person;

public interface PersonDAO extends GenericDAO<Person> {
    public Person add(Person p);
    public Person getByUsername(String username);
    public Person getById(Integer id);
}
