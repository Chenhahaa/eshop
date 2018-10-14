package com.doriss.daoimpl;

import com.doriss.dao.UserDao;
import com.doriss.entity.Orders;
import com.doriss.entity.User;
import com.doriss.utlis.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by chenhaha on 2018/9/12.
 */
public class UserDaoImpl implements UserDao {
//    检查输入的用户名在数据库中是否存在
    public boolean ajax(String name) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from user where username = ? ";
        User user = null;
        try {
            user =  queryRunner.query(sql,new BeanHandler<User>(User.class),name);
            if(user==null){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    @Override
    public User login(String name, String password) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql= "select * from user where username = ? and password = ?";
        User user = new User();
        try {
            user =  queryRunner.query(sql,new BeanHandler<User>(User.class),name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }return user;
    }

        @Override
        public void add (User user){
            QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "insert into user value(?,?,?,?,?,?,?,?,?,?)";
            try {
                queryRunner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void delete (String uuid){

        }

        @Override
        public void update (User user){

        }

        @Override
        public User queryById ( String id){
            return null;
        }

        @Override
        public List<User> queryAll () {
            return null;
        }

    }
