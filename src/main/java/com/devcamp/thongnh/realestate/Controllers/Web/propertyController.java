package com.devcamp.thongnh.realestate.Controllers.Web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;

@RestController
public class propertyController {
    @Autowired
    private RealEstateServiceImpl realEstateServiceImpl;

    @RequestMapping("/property")
    public ModelAndView property(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
            @RequestParam(value = "typeProject", required = false) Long typeProject,
            @RequestParam(value = "requestProject", required = false) Long requestProject,
            @RequestParam(value = "minPrice", defaultValue = "0") Long minPrice,
            @RequestParam(value = "maxPrice", required = false) Long maxPrice,
            @RequestParam(value = "minAcreage", defaultValue = "0") Long minAcreage,
            @RequestParam(value = "maxAcreage", required = false) Long maxAcreage,
            @RequestParam(value = "minBedroom", defaultValue = "0") Long minBedroom,
            @RequestParam(value = "maxBedroom", required = false) Long maxBedroom,
            @RequestParam(value = "minBathroom", defaultValue = "0") Long minBathroom,
            @RequestParam(value = "maxBathroom", required = false) Long maxBathroom,
            @RequestParam(value = "minGarage", defaultValue = "0") Long minGarage,
            @RequestParam(value = "maxGarage", required = false) Long maxGarage,
            @RequestParam(value = "statusProject", required = false) Long statusProject,
            @RequestParam(value = "sltProvince", required = false) Long sltProvince) {
        ModelAndView modelAndView = new ModelAndView("property");

        List<CRealEstate> allCRealEstates = realEstateServiceImpl.getAll();// danh sách tất cả dự án
        List<CRealEstate> realEstatesNotSold = realEstateServiceImpl
                .filterByStatusNotSoldAndApproveTrueAndHidenFalse(allCRealEstates);// danh sách tất cả dự án chưa giao
                                                                                   // dịch và dc duyệt và không ẩn
        if (typeProject != null) {
            realEstateServiceImpl.filterByType(typeProject, realEstatesNotSold);
        }
        if (requestProject != null) {
            realEstateServiceImpl.filterByRequest(requestProject, realEstatesNotSold);
        }
        if (maxPrice != null) {
            realEstateServiceImpl.filterByMinPriceAndMaxPrice(realEstatesNotSold, minPrice, maxPrice);
        }
        if (maxAcreage != null) {
            realEstateServiceImpl.filterByMinAcreageAndMaxAcreage(realEstatesNotSold, minAcreage, maxAcreage);;
        }
        if (maxBedroom != null) {
            realEstateServiceImpl.filterByMinBedRoomAndMaxBedRoom(realEstatesNotSold, minBedroom, maxBedroom);
        }
        if (maxBathroom != null) {
            realEstateServiceImpl.filterByMinBathRoomAndMaxBathRoom(realEstatesNotSold, minBathroom, maxBathroom);
        }
        if (maxGarage != null) {
            realEstateServiceImpl.filterByMinGarageAndMaxGarage(realEstatesNotSold, minGarage, maxGarage);
        }
        if (statusProject != null) {
            realEstateServiceImpl.filterByStatus(statusProject, realEstatesNotSold);;
        }
        if (sltProvince != null) {
            realEstateServiceImpl.filterByProvince(sltProvince, realEstatesNotSold);
        }
        // Lấy danh sách property cho trang hiện tại
        List<CRealEstate> properties = realEstateServiceImpl.getRealEstateByPage(page, realEstatesNotSold);

        // Tính tổng số property
        int totalProperties = realEstateServiceImpl.getTotalRealEstate(realEstatesNotSold);

        // Tính tổng số trang
        int totalPages = (int) Math.ceil((double) totalProperties / 15);
        // Tạo danh sách các số trang để hiển thị
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());

        // Đưa dữ liệu vào model để hiển thị trên trang JSP
        model.addAttribute("properties", properties);// dữ liệu danh sách property cho trang hiện tại
        model.addAttribute("currentPage", page);// dữ liệu trang hiện tại
        model.addAttribute("totalProperties", totalProperties);// tổng số property
        model.addAttribute("totalPages", totalPages);// tổng số trang
        model.addAttribute("pageNumbers", pageNumbers);// danh sách các số trang để hiển thị
        return modelAndView;
    }
}
