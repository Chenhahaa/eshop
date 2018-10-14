package com.doriss.entity;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;

public class Orders {
  private String oid;
  private Date ordertime;
  private Double total;
  private Long state;
  private String address;
  private String name;
  private String telephone;
  private String uid;

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }

  public java.sql.Timestamp getOrdertime() {
    return (Timestamp) ordertime;
  }

  public void setOrdertime(java.sql.Timestamp ordertime) {
    this.ordertime = ordertime;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Long getState() {
    return state;
  }

  public void setState(Long state) {
    this.state = state;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
}
