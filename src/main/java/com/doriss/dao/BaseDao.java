package com.doriss.dao;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by chenhaha on 2018/9/12.
 */
public interface BaseDao <T> {

    public void add( T t);
    public void delete(String uuid);
    public void update(T t);
    public T queryById(String id );
    public List<T>queryAll();
}
