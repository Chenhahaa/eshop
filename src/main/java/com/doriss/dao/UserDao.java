package com.doriss.dao;

import com.doriss.entity.User;

/**
 * Created by chenhaha on 2018/9/12.
 */
public interface UserDao extends  BaseDao<User> {
   public User login(String name ,String password);
   public boolean ajax(String name);
}
