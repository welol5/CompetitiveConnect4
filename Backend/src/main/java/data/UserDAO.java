package data;

import com.revature.beans.User;

public interface UserDAO extends GenericDAO<User> {
    public User add (User u);
    public User getByUsername(String username);
}
