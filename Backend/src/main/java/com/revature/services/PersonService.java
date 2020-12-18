package com.revature.services;

import com.revature.beans.Person;


public interface PersonService {
	// create
	public Integer addPerson(Person p);
	// read
	public Person getPersonById(Integer id);
	
	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);

}
