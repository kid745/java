package com.devcamp.thongnh.realestate.Model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ward")
public class CWard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="_name")
    private String name;

    @Column(name="_prefix")
    private String prefix;

    @ManyToOne
    @JoinColumn(name="_district_id")
    @JsonIgnoreProperties("ward")
    private CDistrict district;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ward")
    @JsonIgnore
    private List<CRealEstate> realEstate;

    @Column(name="_province_id")
    private  Long province;

    public CWard() {
    }

    public CWard(Long id, String name, String prefix, CDistrict district, Long province) {
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


    public List<CRealEstate> getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(List<CRealEstate> realEstate) {
        this.realEstate = realEstate;
    }
    
}
