package com.devcamp.thongnh.realestate.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "street")
public class CStreet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "_name")
    private String name;

    @Column(name = "_prefix")
    private String prefix;

    @ManyToOne
    @JoinColumn(name = "_district_id")
    @JsonIgnoreProperties("street")
    private CDistrict district;

    @Column(name = "_province_id")
    private Long province;

    public CStreet() {
    }

    public CStreet(Long id, String name, String prefix, CDistrict district, Long province) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.district = district;
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

    public CDistrict getDistrict() {
        return district;
    }

    public void setDistrict(CDistrict district) {
        this.district = district;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

}
