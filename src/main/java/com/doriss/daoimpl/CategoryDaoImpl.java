package com.doriss.daoimpl;

import com.doriss.dao.CategoryDao;
import com.doriss.entity.Category;
import com.doriss.utlis.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenhaha on 2018/9/17.
 */
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void add(Category category) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public Category queryById(String id) {
        return null;
    }

    @Override
    public List<Category> queryAll() {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category where fid = 0 ";
        List<Category> categoryList= null;
        try {
          categoryList  =queryRunner.query(sql , new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }return categoryList;
    }
}
