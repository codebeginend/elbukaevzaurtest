package com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double plus_price;
    private double minus_price;

    private Date date = new Date();

    private Long login_id;

    private Long parent_id;

    @ManyToOne
    @JoinColumn(name = "login_id", insertable = false, updatable = false)
    private Login login;

    @Enumerated(value = EnumType.STRING)
    private CurrencyTypeEnum type;

    public Payments() {
    }

    public Payments(double plus_price, double minus_price, Long login_id, CurrencyTypeEnum type, Long parent_id) {
        this.plus_price = plus_price;
        this.minus_price = minus_price;
        this.login_id = login_id;
        this.type = type;
        this.parent_id = parent_id;
    }

    public double getTotalPrice(){
        return this.plus_price - minus_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPlus_price() {
        return plus_price;
    }

    public void setPlus_price(double plus_price) {
        this.plus_price = plus_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CurrencyTypeEnum getType() {
        return type;
    }

    public void setType(CurrencyTypeEnum type) {
        this.type = type;
    }

    public double getMinus_price() {
        return minus_price;
    }

    public void setMinus_price(double minus_price) {
        this.minus_price = minus_price;
    }

    public Long getLogin_id() {
        return login_id;
    }

    public void setLogin_id(Long login_id) {
        this.login_id = login_id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}