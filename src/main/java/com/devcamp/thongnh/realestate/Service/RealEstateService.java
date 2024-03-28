package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CRealEstate;

public interface RealEstateService {
    List<CRealEstate> getAll();

    Optional<CRealEstate> getById(Long id);

    CRealEstate saveRealEstate(CRealEstate realEstate);

    void deleteRealEstateById(Long id);

    void deleteAllRealEstate();

    List<CRealEstate> get6NewRealEstate();

    List<CRealEstate> getRealEstateByFeaturedTrue();

    List<CRealEstate> getRealEstateByPage(int page, List<CRealEstate> realEstateList);

    int getTotalRealEstate(List<CRealEstate> realEstates);

    List<CRealEstate> getRealEstateByCustomerId(Long customerId);

    List<CRealEstate> getAllRealEstateByHiddenFalseAndApproveTrueAndStatusFalse();

    List<CRealEstate> getOtherRealEstatesWithSameCustomers(Long id);

    List<CRealEstate> findTop10ByOrderByViewDesc();

    List<CRealEstate> findTop9ByOrderByDateCreateDesc();

    List<CRealEstate> get6RealEstatesInPage(List<CRealEstate> realEstateList, int page);

    List<CRealEstate> filterByStatusNotSoldAndApproveTrueAndHidenFalse(List<CRealEstate> realEstateList);

    List<CRealEstate> filterByStatusSold(List<CRealEstate> realEstateList);

    void filterByRequest(Long requestValue, List<CRealEstate> realEstateList);

    void filterByType(Long typeValue, List<CRealEstate> realEstateList);

    void filterByStatus(Long statusValue, List<CRealEstate> realEstateList);

    void filterByProvince(Long provinceValue, List<CRealEstate> realEstateList);

    void filterByMinPriceAndMaxPrice(List<CRealEstate> realEstateList, Long minPrice, Long maxPrice);

    void filterByMinBedRoomAndMaxBedRoom(List<CRealEstate> realEstateList, Long minBedRoom, Long maxBedRoom);

    void filterByMinBathRoomAndMaxBathRoom(List<CRealEstate> realEstateList, Long minBathRoom, Long maxBathRoom);

    void filterByMinGarageAndMaxGarage(List<CRealEstate> realEstateList, Long minGarage, Long maxGarage);

    void filterByMinAcreageAndMaxAcreage(List<CRealEstate> realEstateList, Long minAcreage, Long maxAcreage);

    void deleteCPhotosByRealEstateId(Long id);
}
