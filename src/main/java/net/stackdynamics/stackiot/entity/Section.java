package net.stackdynamics.stackiot.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tix_section")
public class Section {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  @Column(name = "id")
  private int id;
  @Column(name = "title")
  private String title;
  @Column(name = "eid")
  private int eid;
  @Column(name = "price")
  private BigDecimal price;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getEid() {
    return eid;
  }

  public void setEid(int eid) {
    this.eid = eid;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
