package com.revature.services;

import com.revature.beans.Person;
import com.revature.exceptions.DuplicateUserException;


public interface PersonService {
	// create
	public Integer addPerson(Person p) throws DuplicateUserException;
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);
    public void updateUsername(Person p, String newUsername);
	public void calculatePoints(Person winner, Person loser);
}
