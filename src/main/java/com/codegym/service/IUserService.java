package com.codegym.service;

import com.codegym.model.User;

import java.sql.SQLException;

public interface IUserService extends IGeneralService <User>{
    User getUserById(int id);

    boolean insertUserStore(User user);
}
