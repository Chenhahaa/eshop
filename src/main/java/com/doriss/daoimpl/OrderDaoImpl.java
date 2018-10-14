package com.doriss.daoimpl;

import com.doriss.dao.OrderDao;
import com.doriss.entity.Orders;
import com.doriss.utlis.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.activation.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenhaha on 2018/10/8.
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public void add(Orders orders) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into orders value(?,?,?,?,?,?,?,?)";
        try {
            queryRunner.update(sql,orders.getOid(),orders.getAddress(),orders.getName(),orders.getOrdertime(),orders.getState(),
                    orders.getTelephone(),orders.getTotal(),orders.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Orders orders) {
           QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into orders value(?,?,?,?,?,?,?,?)";
        try {
            queryRunner.update(sql,orders.getOid(),orders.getAddress(),orders.getName(),orders.getOrdertime(),orders.getState(),
                    orders.getTelephone(),orders.getTotal(),orders.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Orders queryById(String id) {
        return null;
    }

    @Override
    public List<Orders> queryAll() {
        return null;
    }

    @Override
    public void updateTotalprice(Orders orders) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update orders set total=? where oid = ? ";
        try {
            queryRunner.update(sql,orders.getOid(),orders.getTotal());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  @Override
    public void updateOrderAddress(String orderid, String name, String address, String telephone) {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update orders set address=?,name=?,telephone=? where oid=?";
        try {
            queryRunner.update(sql,name,telephone,address,orderid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateStatus(int i) {

    }

    //    @Override
    public void updateStatus(String oid) {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update orders set status=1 where oid=?";
        try {
            queryRunner.update(sql,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
