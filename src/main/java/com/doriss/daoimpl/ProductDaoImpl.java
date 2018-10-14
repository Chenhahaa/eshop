package com.doriss.daoimpl;

import com.doriss.dao.ProductDao;
import com.doriss.entity.Product;
import com.doriss.utlis.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhaha on 2018/9/18.
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public void add(Product product) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public Product queryById(String id) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        Product product =null;
        try {
           product =  queryRunner.query(sql, new BeanHandler<Product>(Product.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> queryAll() {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        List<Product> productList = null;
        try {
            productList = queryRunner.query(sql , new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Map<String, Object>> queryAllByCid(String cid) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product p ,category c where p.cid = c.cid and p.cid = ?";
        List<Map<String,Object>> productlist = null;
        try {
            productlist =  queryRunner.query(sql, new MapListHandler(),cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productlist;
    }
    @Override
    public List<Product> queryAllByCid2(String cid) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where cid = ?";
        List<Product> productlist = null;
        try {
            productlist = queryRunner.query(sql, new BeanListHandler<>(Product.class), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productlist;
    }
}
