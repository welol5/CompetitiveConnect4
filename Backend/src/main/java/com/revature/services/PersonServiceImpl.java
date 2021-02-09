package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDao;

	@Autowired
	public PersonServiceImpl(PersonDAO p){
	    personDao = p;
    }


	@Override
	public Integer addPerson(Person p) {
		return personDao.save(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.findById(id).get();
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.findByUsername(username);
	}
	
	@Override
	public void updateUsername(Person p, String newUsername) {
		p.setUsername(newUsername);
		personDao.save(p);
		
	}
// add a comment
	@Override
	public void updatePerson(Person p) {
		personDao.save(p);
	}

	@Override
	public void deletePerson(Person p) {
		personDao.delete(p);
	}
	public void calculatePoints(Person win, Person lose) {
		Person winner = win;
		Person loser = lose;
		if (winner.getRank() > loser.getRank()) {
			winner.setRank(winner.getRank() + 20);
			loser.setRank(loser.getRank() - 20);
		} else {
			winner.setRank(winner.getRank() + 25);
			loser.setRank(loser.getRank() - 25);
		}
		personDao.save(winner);
		personDao.save(loser);
	}

}
