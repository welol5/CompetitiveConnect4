package com.revature.data;

import com.revature.beans.Person;

public class PersonDAOFactory {
    public PersonDAO getPersonDAO() {
        return new PersonDAOImpl();
    }
}
