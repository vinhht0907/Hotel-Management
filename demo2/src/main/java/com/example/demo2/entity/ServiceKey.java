package com.example.demo2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class ServiceKey implements Serializable {
    @Column(name="customer_id")
    private  int custemerId;
    @Column(name="product_id")
    private  int pruductId;

    public ServiceKey(int custemerId, int pruductId) {
        super();
        this.custemerId = custemerId;
        this.pruductId = pruductId;
    }

    public ServiceKey() {

    }

    public int getCustemerId() {
        return custemerId;
    }

    public int getPruductId() {
        return pruductId;
    }

    public void setCustemerId(int custemerId) {
        this.custemerId = custemerId;
    }

    public void setPruductId(int pruductId) {
        this.pruductId = pruductId;
    }
}
