package com.devcamp.thongnh.realestate.Model;

import java.util.Date;

import javax.persistence.*;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "blog")
public class CBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "_photo_main")
    private String photoMain;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "view", columnDefinition = "bigint default 0")
    private Long view;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "approve", columnDefinition = "bigint default false")
    private Boolean approve;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("blogs")
    private CCustomers customers;

    @Column(name = "update_by")
    private Long updateBy;

    public CBlog() {
    }

    public CBlog(Long id, String name, String photoMain, String description, String content, Long view, Date dateCreate,
            Boolean approve, CCustomers customers, Long updateBy) {
        this.id = id;
        this.name = name;
        this.photoMain = photoMain;
        this.description = description;
        this.content = content;
        this.view = view;
        this.dateCreate = dateCreate;
        this.approve = approve;
        this.customers = customers;
        this.updateBy = updateBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoMain() {
        return photoMain;
    }

    public void setPhotoMain(String photoMain) {
        this.photoMain = photoMain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

}
