package com.doriss.dao;

import com.doriss.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by chenhaha on 2018/9/18.
 */
public interface ProductDao extends BaseDao<Product> {
    public List<Map<String, Object>> queryAllByCid(String cid);
    public List<Product> queryAllByCid2(String cid);

}
