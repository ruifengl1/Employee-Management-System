package com.team.project.service;

import com.team.project.dao.UserDao;
import com.team.project.entity.User;
import com.team.project.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUser(UserDao userDao) {
        this.userDao = userDao;

    }
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Transactional
    public User getUserById(int id) throws UserNotFoundException {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
    @Transactional
    public Optional<User> getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
    @Transactional
    public Integer addNewUser(User user) {
        return userDao.addUser(user);
    }
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }
    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }
}
