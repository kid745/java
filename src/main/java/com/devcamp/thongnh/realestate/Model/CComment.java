package com.devcamp.thongnh.realestate.Model;

import java.util.Date;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "approve")
    private boolean approve;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("comment")
    private CCustomers customers;

    @ManyToOne
    @JoinColumn(name = "readlEstate_id")
    @JsonIgnoreProperties("comment")
    private CRealEstate realestate;

    

    public CComment() {
    }

    public CComment(Long id, String content, CCustomers customers, CRealEstate realestate, boolean approve,
            Date dateCreate) {
        this.id = id;
        this.content = content;
        this.approve = approve;
        this.dateCreate = dateCreate;
        this.customers = customers;
        this.realestate = realestate;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
