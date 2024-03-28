package com.devcamp.thongnh.realestate.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "realestate")
public class CRealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 2000)
    private String title;

    @Column(name = "type")
    private Long type;

    @Column(name = "request")
    private Long request;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "featured")
    private boolean featured = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "wards_id")
    @JsonIgnoreProperties("realEstate")
    private CWard ward;

    @Column(name = "street_id")
    private Long streetId;

    @Column(name = "hidden", columnDefinition = "boolean default false")
    private boolean hidden;

    @Column(name = "approve", columnDefinition = "boolean default false")
    private boolean approve;

    @Column(name = "status", columnDefinition = "bigint default 0")
    private Long status;

    @Column(name = "address", nullable = false, length = 2000)
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("realEstate")
    private CCustomers customers;

    @Column(name = "price")
    private Long price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "view_num")
    private Long viewNum;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "acreage", columnDefinition = "bigint default 0")
    private BigDecimal acreage;

    @Column(name = "bedroom", columnDefinition = "bigint default 0")
    private Long bedroom;

    @Column(name = "garage", columnDefinition = "bigint default 0")
    private Long garage;

    @Column(name = "bathroom", columnDefinition = "bigint default 0")
    private Long bathroom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "realestate")
    @JsonIgnore
    private List<CPhoto> photo;

    @Column(name = "linkVideo")
    private String linkVideo;

    @Column(name = "_photo_main")
    private String photoMain;

    @Column(name = "view", columnDefinition = "bigint default 0")
    private int view;

    // Constructors, getters, setters, etc.

    public CRealEstate() {
    }

    public CRealEstate(Long id, String title, Long type, Long request, Long provinceId, Long districtId,
            boolean featured, CWard ward, Long streetId, boolean hidden, boolean approve, Long status, String address,
            CCustomers customers, Long price, Date dateCreate, Date dateUpdate, String description, Long viewNum,
            Long createBy, Long updateBy, BigDecimal acreage, Long bedroom, Long garage, Long bathroom,
            List<CPhoto> photo, String linkVideo, String photoMain, int view) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.request = request;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.featured = featured;
        this.ward = ward;
        this.streetId = streetId;
        this.hidden = hidden;
        this.approve = approve;
        this.status = status;
        this.address = address;
        this.customers = customers;
        this.price = price;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.description = description;
        this.viewNum = viewNum;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.acreage = acreage;
        this.bedroom = bedroom;
        this.garage = garage;
        this.bathroom = bathroom;
        this.photo = photo;
        this.linkVideo = linkVideo;
        this.photoMain = photoMain;
        this.view = view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getRequest() {
        return request;
    }

    public void setRequest(Long request) {
        this.request = request;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public CWard getWard() {
        return ward;
    }

    public void setWard(CWard ward) {
        this.ward = ward;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CCustomers getCustomers() {
        return customers;
    }

    public void setCustomers(CCustomers customers) {
        this.customers = customers;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public Long getBedroom() {
        return bedroom;
    }

    public void setBedroom(Long bedroom) {
        this.bedroom = bedroom;
    }

    public Long getGarage() {
        return garage;
    }

    public void setGarage(Long garage) {
        this.garage = garage;
    }

    public Long getBathroom() {
        return bathroom;
    }

    public void setBathroom(Long bathroom) {
        this.bathroom = bathroom;
    }

    public List<CPhoto> getPhoto() {
        return photo;
    }

    public void setPhoto(List<CPhoto> photo) {
        this.photo = photo;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getPhotoMain() {
        return photoMain;
    }

    public void setPhotoMain(String photoMain) {
        this.photoMain = photoMain;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
