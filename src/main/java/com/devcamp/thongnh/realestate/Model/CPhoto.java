package com.devcamp.thongnh.realestate.Model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "photo")
public class CPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
     
    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "real_estate_id")
    @JsonIgnore
    private CRealEstate realestate;

    public CPhoto() {
    }
    public CPhoto(Long id, String path, CRealEstate realestate) {
        this.id = id;
        this.path = path;
        this.realestate = realestate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public CRealEstate getRealestate() {
        return realestate;
    }

    public void setRealestate(CRealEstate realestate) {
        this.realestate = realestate;
    }
}
