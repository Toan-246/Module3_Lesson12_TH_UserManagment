package com.codegym.service;

import com.codegym.dao.IGeneralDao;
import com.codegym.dao.IUserDao;
import com.codegym.model.User;

import java.util.List;

public class UserService implements IUserService{
    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean updateById(int id, User user) {
        return userDao.updateById(id, user);
    }

    @Override
    public boolean deleteById(int id) {
        return userDao.deleteById(id);
    }
}
