package com.doriss.daoimpl;

import com.doriss.dao.OrderItemDao;
import com.doriss.entity.Orderitem;
import com.doriss.utlis.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenhaha on 2018/10/8.
 */
public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public void add(Orderitem orderitem) {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into orderitem value(?,?,?,?,?)";
        try {
            queryRunner.update(sql,orderitem.getOid(),orderitem.getCount(),orderitem.getItemid(),
                    orderitem.getPid(),orderitem.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void update(Orderitem orderitem) {

    }

    @Override
    public Orderitem queryById(String id) {
        return null;
    }

    @Override
    public List<Orderitem> queryAll() {
        return null;
    }
}
