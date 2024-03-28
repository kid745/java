package com.devcamp.thongnh.realestate.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;

@Entity
@Table(name = "cart")
public class CCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CCustomers customers;

    @ManyToOne
    @JoinColumn(name = "realEstate_id")
    private CRealEstate realestate;

    public CCart() {
    }

    public CCart(Long id, CCustomers customers, CRealEstate realestate) {
        this.id = id;
        this.customers = customers;
        this.realestate = realestate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CCustomers getCustomers() {
        return customers;
    }

    public void setCustomers(CCustomers customers) {
        this.customers = customers;
    }

    public CRealEstate getRealestate() {
        return realestate;
    }

    public void setRealestate(CRealEstate realestate) {
        this.realestate = realestate;
    }
    
    
}
