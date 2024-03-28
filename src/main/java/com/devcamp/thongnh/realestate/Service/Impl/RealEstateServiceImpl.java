package com.devcamp.thongnh.realestate.Service.Impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CPhoto;
import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Respository.PhotoRepository;
import com.devcamp.thongnh.realestate.Respository.RealEstateRepository;
import com.devcamp.thongnh.realestate.Service.RealEstateService;

@Service
public class RealEstateServiceImpl implements RealEstateService {
    @Autowired
    private RealEstateRepository realEstateRepository;
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<CRealEstate> getAll() {

        return realEstateRepository.findAll();
    }

    @Override
    public List<CRealEstate> getAllRealEstateByHiddenFalseAndApproveTrueAndStatusFalse() {

        return realEstateRepository.findByHiddenAndApproveAndStatus(false, true, false);
    }

    @Override
    public Optional<CRealEstate> getById(Long id) {

        Optional<CRealEstate> RealEstate = realEstateRepository.findById(id);
        if (RealEstate.isPresent()) {
            return RealEstate;
        } else {
            return null;
        }
    }

    @Override
    public CRealEstate saveRealEstate(CRealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @Override
    public void deleteRealEstateById(Long id) {

        realEstateRepository.deleteById(id);
    }

    @Override
    public void deleteAllRealEstate() {

        realEstateRepository.deleteAll();
    }

    @Override
    public List<CRealEstate> get6NewRealEstate() {

        return realEstateRepository.findTop6ByOrderByIdDesc();

    }

    @Override
    public List<CRealEstate> getRealEstateByFeaturedTrue() {
        List<CRealEstate> realEstateList = realEstateRepository.findByHiddenAndApprove(false, true);
        List<CRealEstate> featuredRealEstates = realEstateList.stream().filter(CRealEstate::getFeatured)
                .collect(Collectors.toList());
        return featuredRealEstates;
    }

    @Override
    public List<CRealEstate> getRealEstateByPage(int page, List<CRealEstate> realEstateList) {
        int pageSize = 15;
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, realEstateList.size());

        // Lấy phần của danh sách dựa trên trang và kích thước trang
        List<CRealEstate> pageRealEstates = realEstateList.subList(start, end);

        return pageRealEstates;
    }

    @Override
    public int getTotalRealEstate(List<CRealEstate> realEstates) {

        return realEstates.size();
    }

    @Override
    public List<CRealEstate> getRealEstateByCustomerId(Long customerId) {
        return realEstateRepository.findByCustomersId(customerId);
    }

    @Override
    public List<CRealEstate> getOtherRealEstatesWithSameCustomers(Long id) {
        CRealEstate currentRealEstate = realEstateRepository.findById(id).orElse(null);

        if (currentRealEstate != null && currentRealEstate.getCustomers() != null) {
            return realEstateRepository.findByCustomersAndIdNot(currentRealEstate.getCustomers(), id);
        }

        return Collections.emptyList();
    }

    @Override
    public List<CRealEstate> findTop10ByOrderByViewDesc() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return realEstateRepository.findTop10ByOrderByViewDesc(pageRequest);
    }

    @Override
    public List<CRealEstate> findTop9ByOrderByDateCreateDesc() {
        return realEstateRepository.findTop6ByOrderByDateCreateDesc();
    }

    @Override
    public List<CRealEstate> get6RealEstatesInPage(List<CRealEstate> realEstateList, int page) {
        int pageSize = 6;
        int startIndex = (page - 1) * pageSize;
        List<CRealEstate> result = realEstateList.stream()
                .skip(startIndex)
                .limit(pageSize)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CRealEstate> filterByStatusSold(List<CRealEstate> realEstateList) {
        return realEstateList.stream()
                .filter(realEstate -> realEstate.getStatus() == 3)
                .collect(Collectors.toList());
    }

    @Override
    public void filterByRequest(Long requestValue, List<CRealEstate> realEstateList) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> Objects.equals(cRealEstate.getRequest(), requestValue))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByType(Long typeValue, List<CRealEstate> realEstateList) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> Objects.equals(cRealEstate.getType(), typeValue))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByStatus(Long statusValue, List<CRealEstate> realEstateList) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> Objects.equals(cRealEstate.getStatus(), statusValue))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByProvince(Long provinceValue, List<CRealEstate> realEstateList) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> Objects.equals(cRealEstate.getProvinceId(), provinceValue))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByMinPriceAndMaxPrice(List<CRealEstate> realEstateList, Long minPrice,
            Long maxPrice) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> isMinAndMax(cRealEstate.getPrice(), minPrice, maxPrice))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByMinBedRoomAndMaxBedRoom(List<CRealEstate> realEstateList, Long minBedRoom, Long maxBedRoom) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> isMinAndMax(cRealEstate.getBedroom(), minBedRoom, maxBedRoom))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByMinBathRoomAndMaxBathRoom(List<CRealEstate> realEstateList, Long minBathRoom,
            Long maxBathRoom) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> isMinAndMax(cRealEstate.getBedroom(), minBathRoom, maxBathRoom))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public void filterByMinGarageAndMaxGarage(List<CRealEstate> realEstateList, Long minGarage, Long maxGarage) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> isMinAndMax(cRealEstate.getBedroom(), minGarage, maxGarage))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    @Override
    public List<CRealEstate> filterByStatusNotSoldAndApproveTrueAndHidenFalse(List<CRealEstate> realEstateList) {
        List<CRealEstate> realEstateNotSold = realEstateList.stream()
                .filter(realEstate -> realEstate.getStatus() != 3)
                .collect(Collectors.toList());
        List<CRealEstate> realEstateApproveTrue = realEstateNotSold.stream()
                .filter(realEstate -> realEstate.isApprove() == true)
                .collect(Collectors.toList());
        List<CRealEstate> realEstateHiddenFalse = realEstateApproveTrue.stream()
                .filter(realEstate -> realEstate.isHidden() == false)
                .collect(Collectors.toList());
        return realEstateHiddenFalse.stream()
                .sorted(Comparator.comparingLong(CRealEstate::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void filterByMinAcreageAndMaxAcreage(List<CRealEstate> realEstateList, Long minAcreage, Long maxAcreage) {
        List<CRealEstate> newList = realEstateList.stream()
                .filter(cRealEstate -> isMinAndMaxAcreage(cRealEstate.getAcreage(), minAcreage, maxAcreage))
                .collect(Collectors.toList());
        realEstateList.clear();
        realEstateList.addAll(newList);
    }

    private boolean isMinAndMaxAcreage(BigDecimal value, Long minValue, Long maxValue) {
        BigDecimal minValueBigDecimal = (minValue != null) ? BigDecimal.valueOf(minValue) : null;
        BigDecimal maxValueBigDecimal = (maxValue != null) ? BigDecimal.valueOf(maxValue) : null;

        // Kiểm tra xem giá trị có lớn hơn hoặc bằng minValue (nếu minValue không null)
        // và giá trị có nhỏ hơn hoặc bằng maxValue (nếu maxValue không null)
        return (minValueBigDecimal == null || value.compareTo(minValueBigDecimal) >= 0)
                && (maxValueBigDecimal == null || value.compareTo(maxValueBigDecimal) <= 0);
    }

    private boolean isMinAndMax(Long value, Long minValue, Long maxValue) {
        return (minValue == null || value >= minValue)
                && (maxValue == null || value <= maxValue);
    }

    @Override
    public void deleteCPhotosByRealEstateId(Long id) {
      List<CPhoto> cPhotos =  photoRepository.findByRealestateId(id);
      photoRepository.deleteAll(cPhotos);
    }
}
