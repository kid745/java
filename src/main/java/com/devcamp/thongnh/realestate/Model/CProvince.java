package com.devcamp.thongnh.realestate.Model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "province")
public class CProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "_name")
    private String name;

    @Column(name = "_code", unique = true)
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "province")
    @JsonBackReference
    private List<CDistrict> district;

    // Constructors, getters, setters, and other methods
    public CProvince() {
    }

    public CProvince(Long id, String name, String code, List<CDistrict> district) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.district = district;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CDistrict> getDistrict() {
        return district;
    }

    public void setDistrict(List<CDistrict> district) {
        this.district = district;
    }

}
