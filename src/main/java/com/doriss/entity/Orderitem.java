package com.doriss.entity;

public class Orderitem {
  private String itemid;
  private Long count;
  private Double subtotal;
  private String pid;
  private String oid;

  public String getItemid() {
    return itemid;
  }

  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }
}
