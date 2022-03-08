package com.codegym.dao;

import com.codegym.model.User;

import java.sql.SQLException;

public interface IUserDao extends IGeneralDao <User>{
    User getUserById(int id);

    boolean insertUserStore(User user) ;
}
