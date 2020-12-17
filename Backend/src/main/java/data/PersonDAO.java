package data;

import com.revature.beans.Person;

public interface PersonDAO extends GenericDAO<Person> {
    public Person add(Person p);
    public Person getByUsername(String username);

}
