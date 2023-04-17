package com.team.project.dao;

import com.team.project.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao extends  AbstractHibernateDao<User> {

    public UserDao() {
        setClassName(User.class);
    }
    public Optional<User> getUserByUsername(String username) {
        List<User> users = getByField("username", username);
        return users.size() == 0 ? Optional.empty() : Optional.of(users.get(0));
    }
    public Optional<User> getUserByEmail(String email) {
        List<User> users = getByField("email", email);
        return users.size() == 0 ? Optional.empty() : Optional.of(users.get(0));
    }
    public User getUserById(int id) {
        return this.findById(id);
    }

    public List<User> getAllUsers() {
        return this.getAll();
    }

    public Integer addUser(User user) {
        return this.add(user);
    }

    public void removeUser(int id) {
        this.remove(this.findById(id));
    }
}
