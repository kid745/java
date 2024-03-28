package com.devcamp.thongnh.realestate.Model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "district")
public class CDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "_name")
    private String name;

    @Column(name = "_prefix")
    private String prefix;

    @ManyToOne
    @JoinColumn(name = "_province_id")
    @JsonIgnoreProperties("district")
    private CProvince province;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
    @JsonIgnore
    private List<CWard> ward;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
    @JsonIgnore
    private List<CStreet> street;


    public CDistrict() {
    }

    public CDistrict(Long id, String name, String prefix, CProvince province) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.province = province;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public CProvince getProvince() {
        return province;
    }

    public void setProvince(CProvince province) {
        this.province = province;
    }

    public List<CWard> getWard() {
        return ward;
    }

    public void setWard(List<CWard> ward) {
        this.ward = ward;
    }

    public List<CStreet> getStreet() {
        return street;
    }

    public void setStreet(List<CStreet> street) {
        this.street = street;
    }

}
