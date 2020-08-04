package com.github.SpringBootTests.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

  @Id private long id;

  private String address;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
