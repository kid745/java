package com.devcamp.thongnh.realestate.Controllers.Web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.CComment;
import com.devcamp.thongnh.realestate.Model.CPhoto;
import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.Impl.CommentServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.PhotoServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;



@RestController
public class detailController {

    @Autowired
    private RealEstateServiceImpl realEstateServiceImpl;

    @Autowired
    private CustomersServiceImpl customersServiceImpl;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private PhotoServiceImpl photoServiceImpl;

    @RequestMapping("/property/{id}")
    public ModelAndView detail(@PathVariable("id") Long pId, Model model) {
        ModelAndView modelAndView = new ModelAndView("detail");

        Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
        if (realEstateId.isPresent()) {
            CRealEstate property = realEstateId.get();
            property.setView(property.getView()+1);
            realEstateServiceImpl.saveRealEstate(property);

            Optional<CCustomers> customerId = customersServiceImpl.getById(property.getCustomers().getId());
            CCustomers agent = customerId.get();

            List<CComment> commentsByRealEstate = commentServiceImpl.getCommentsByRealEsetateId(pId);
            List<CComment> comments = commentsByRealEstate.stream().filter(comment -> comment.isApprove())
                    .collect(Collectors.toList());
            int commentsCount = comments.size();
            // Số comment trên mỗi trang
            int commentInPage = 5;

            //Lấy danh sách id ảnh miêu tả
            List<CPhoto> photoIds= photoServiceImpl.getPhotoIdsByRealEstateId(pId);

            // Tính tổng số trang comment
            int commentPage = (int) Math.ceil((double) commentsCount / commentInPage);

            // Tạo danh sách các số trang để hiển thị
            List<Integer> pageNumbers = IntStream.rangeClosed(1, commentPage).boxed().collect(Collectors.toList());

            // Đưa dữ liệu vào model để hiển thị trên trang JSP
            model.addAttribute("property", property);// dữ liệu property
            model.addAttribute("agent", agent);// dữ liệu agent
            model.addAttribute("commentsCount", commentsCount);// dữ liệu số lượng comment
            model.addAttribute("pageNumbers", pageNumbers);// dữ liệu trang comment
            model.addAttribute("photoIds", photoIds);// dữ liệu thumbail

        }

        return modelAndView;
    }

}
