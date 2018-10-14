package com.doriss.dao;

import com.doriss.entity.Orders;

/**
 * Created by chenhaha on 2018/10/8.
 */
public interface OrderDao extends BaseDao<Orders> {
    public void  updateTotalprice(Orders orders);

     public  void updateOrderAddress(String orderid, String name, String address, String telephone);

    public void updateStatus(int i);
}
