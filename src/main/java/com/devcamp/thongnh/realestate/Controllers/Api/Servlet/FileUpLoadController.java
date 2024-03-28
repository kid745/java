package com.devcamp.thongnh.realestate.Controllers.Api.Servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.devcamp.thongnh.realestate.Model.CBlog;
import com.devcamp.thongnh.realestate.Model.CPhoto;
import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Model.CWard;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.Impl.BlogServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.PhotoServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.WardServiceImpl;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class FileUpLoadController {
    @Autowired
    PhotoServiceImpl photoServiceImpl;
    @Autowired
    RealEstateServiceImpl realEstateServiceImpl;
    @Autowired
    WardServiceImpl wardServiceImpl;
    @Autowired
    CustomersServiceImpl customerServiceImpl;
    @Autowired
    BlogServiceImpl blogServiceImpl;

    @PostMapping(value = "/exitUser", consumes = { "multipart/form-data" })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> uploadAvatarFile(
            @RequestPart(name = "avatar", required = false) MultipartFile avatar,
            @RequestPart("customer") CCustomers pCustomers) {
        try {
            long id = pCustomers.getId();
            Optional<CCustomers> customerEmail = customerServiceImpl.getByEmail(pCustomers.getEmail());
            Optional<CCustomers> customerMobie = customerServiceImpl.getByMobile(pCustomers.getMobile());
            Optional<CCustomers> customerId = customerServiceImpl.getById(pCustomers.getId());
            if (!customerId.isPresent()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (customerEmail.isPresent() && customerEmail.get().getId() != id) {
                return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
            }
            if (customerMobie.isPresent() && customerMobie.get().getId() != id) {
                return new ResponseEntity<>("Mobile numberalready exists", HttpStatus.CONFLICT);
            }
            CCustomers customersUpdate = customerId.get();
            if (avatar != null) {
                String customerIdFolder = createCustomerFolder(id);
                if (!clearFileInFolder(customerIdFolder)) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                saveAvatarToCustomerFolder(customerIdFolder, avatar, customersUpdate);
            }
            if (avatar == null) {
                customersUpdate.setAvatar(null);
                String avatarFolderPath = "src/main/resources/static/images/Customer/" + id;
                File avatarFolder = new File(avatarFolderPath);
                if (avatarFolder.exists()) {
                    deleteFolder(avatarFolder);
                }
            }
            customersUpdate.setContactName(pCustomers.getContactName());
            customersUpdate.setAddress(pCustomers.getAddress());
            customersUpdate.setMobile(pCustomers.getMobile());
            customersUpdate.setEmail(pCustomers.getEmail());
            customersUpdate.setNote(pCustomers.getNote());
            customersUpdate.setUpdatedAt(new Date());
            customerServiceImpl.saveCustomers(customersUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // post new project
    @PostMapping(value = "/newproject", consumes = { "multipart/form-data" })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> uploadNewProject(@RequestPart("realestate") CRealEstate pRealEstate,
            @RequestPart(name = "file", required = false) MultipartFile file,
            @RequestPart(name = "files", required = false) MultipartFile[] files,
            @RequestPart(name = "video", required = false) MultipartFile video) {
        try {
            pRealEstate.setDateCreate(new Date());
            CRealEstate savedEntity = realEstateServiceImpl.saveRealEstate(pRealEstate);
            long id = savedEntity.getId();
            String pathProjectId = createProjectFolder(id);
            if (file != null) {
                String pathProjectIdImgThum = createImageThumbFolder(pathProjectId);// foder img thum
                UploadThumImgNewProject(pathProjectIdImgThum, file, savedEntity);
            }
            if (files != null && files.length > 0) {
                String pathProjectIdImgDesc = createImageDescFolder(pathProjectId);// folder img Desc
                saveImagesToDescFolder(pathProjectIdImgDesc, files, savedEntity);
            }
            if (video != null) {
                String pathProjectIdVideoThum = createVideoThumFolder(pathProjectId);// foder video thum
                saveVideoToThumbFolder(pathProjectIdVideoThum, video, savedEntity);
            }
            postProject(pRealEstate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // post new blog
    @PostMapping(value = "/newblog", consumes = { "multipart/form-data" })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> uploadNewBlog(@RequestPart("blog") CBlog pBlog,
            @RequestPart(name = "imgThumBlog", required = false) MultipartFile file) {
        try {
            pBlog.setView(0L);
            pBlog.setDateCreate(new Date());
            CBlog blog = blogServiceImpl.saveCBlog(pBlog);
            long id = blog.getId();
            String blogIdFolder = createBlogIdFolder(id);
            if (file != null) {
                String pathBlogIdImgThum = createImageThumbFolder(blogIdFolder);
                UploadThumImgBlog(pathBlogIdImgThum, file, blog);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cập nhật dự án
    @PostMapping(value = "/exitsproject", consumes = { "multipart/form-data" })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> uploadProject(@RequestPart("realestate") CRealEstate pRealEstate,
            @RequestPart(name = "file", required = false) MultipartFile file,
            @RequestPart(name = "files", required = false) MultipartFile[] files,
            @RequestPart(name = "video", required = false) MultipartFile video) {
        try {
            long id = pRealEstate.getId();
            Optional<CRealEstate> realEstateOptional = realEstateServiceImpl.getById(id);

            if (!realEstateOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CRealEstate realEstate = realEstateOptional.get();

            String pathProjectId = createProjectFolder(id);// foder id project
            if (file != null) {
                String pathProjectIdImgThum = createImageThumbFolder(pathProjectId);// folder img thum
                if (!clearFileInFolder(pathProjectIdImgThum)) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                saveMainImageToThumbFolder(pathProjectIdImgThum, file, realEstate, pRealEstate);
            }
            if (file == null) {
                realEstate.setPhotoMain(null);
                String imgThumFolderPath = pathProjectId + "/imgThum";
                File imgThumFolder = new File(imgThumFolderPath);
                if (imgThumFolder.exists()) {
                    deleteFolder(imgThumFolder);
                }

            }
            if (files != null && files.length > 0) {
                String pathProjectIdImgDesc = createImageDescFolder(pathProjectId);// folder img Desc
                realEstateServiceImpl.deleteCPhotosByRealEstateId(realEstate.getId());
                if (!clearFileInFolder(pathProjectIdImgDesc)) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                saveImagesToDescFolder(pathProjectIdImgDesc, files, realEstate);
            }
            if (files == null || files.length == 0) {
                realEstateServiceImpl.deleteCPhotosByRealEstateId(realEstate.getId());
                String imgDescFolderPath = pathProjectId + "/imgDesc";
                File imgDescFolder = new File(imgDescFolderPath);
                if (imgDescFolder.exists()) {
                    deleteFolder(imgDescFolder);
                }
            }
            if (video != null) {
                String pathProjectIdVideoThum = createVideoThumFolder(pathProjectId);// foder video thum
                if (!clearFileInFolder(pathProjectIdVideoThum)) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                saveVideoToThumbFolder(pathProjectIdVideoThum, video, realEstate);
            }
            if (video == null) {
                realEstate.setLinkVideo(null);
                String videoThumFolderPath = pathProjectId + "/videoThum";
                File videoThumFolder = new File(videoThumFolderPath);
                if (videoThumFolder.exists()) {
                    deleteFolder(videoThumFolder);
                }
            }
            realEstateServiceImpl.saveRealEstate(realEstate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // cập nhật blog
    @PostMapping(value = "/exitblog", consumes = { "multipart/form-data" })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> uploadExitBlog(@RequestPart("blog") CBlog pBlog,
            @RequestPart(name = "imgThumBlog", required = false) MultipartFile file) {
        try {
            Long id = pBlog.getId();
            Optional<CBlog> blogOptional = blogServiceImpl.findCBlogId(id);
            if (!blogOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String pathBlogId = createBlogIdFolder(id);
            if (file != null) {
                String pathBlogIdImgThum = createImageThumbFolder(pathBlogId);// folder img thum
                if (!clearFileInFolder(pathBlogIdImgThum)) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                UploadThumImgBlog(pathBlogIdImgThum, file, pBlog);
            }
            if (file == null) {
                if (!clearFileInFolder(pathBlogId)) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                pBlog.setPhotoMain(null);
                blogServiceImpl.saveCBlog(pBlog);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Khai báo request dùng cho ckfinder
    @PostMapping(value = "/ckfinder/images", consumes = { "multipart/form-data" })
    @Transactional(rollbackFor = Exception.class)
    public String handleImageUpload(@RequestParam("upload") MultipartFile file) throws IOException {
        System.out.println("Content Type: " + file.getContentType());
        String folderImgBlog = "src/main/resources/static/images/Blog/AllBlog/";
        String pathFileName = folderImgBlog + file.getOriginalFilename();
        File imgBlog = new File(pathFileName);
        file.transferTo(imgBlog);
        String src = "/images/Blog/AllBlog/" + file.getOriginalFilename();
        return "{ \"uploaded\": true, \"url\": \"" + src + "\" }";
    }

    // phần khai báo hàm dùng chung

    private String createCustomerFolder(long id) {// Tạo folder id customer
        String pathCustomerId = "src/main/resources/static/images/Customer/" + id;
        File folderCustomerId = new File(pathCustomerId);
        if (!folderCustomerId.exists()) {
            boolean created = folderCustomerId.mkdirs();
            if (!created) {
            }
        }
        return pathCustomerId;
    }

    private String createProjectFolder(long id) {// Tạo folder id project
        String pathProjectId = "src/main/resources/static/images/Project/" + id;
        File fileProjectId = new File(pathProjectId);
        if (!fileProjectId.exists()) {
            boolean created = fileProjectId.mkdirs();
            if (!created) {
            }
        }
        return pathProjectId;
    }

    private String createBlogIdFolder(long id) {// Tạo folder id Blog
        String pathProjectId = "src/main/resources/static/images/Blog/" + id;
        File fileProjectId = new File(pathProjectId);
        if (!fileProjectId.exists()) {
            boolean created = fileProjectId.mkdirs();
            if (!created) {

            }
        }
        return pathProjectId;
    }

    private String createImageDescFolder(String pathProjectId) {
        String pathProjectIdImgDesc = pathProjectId + "/imgDesc";
        File fileProjectIdImgDesc = new File(pathProjectIdImgDesc);
        if (!fileProjectIdImgDesc.exists()) {
            boolean created = fileProjectIdImgDesc.mkdirs();
            if (!created) {
                // Xử lý lỗi khi không thể tạo thư mục
            }
        }
        return pathProjectIdImgDesc;
    }

    private String createVideoThumFolder(String pathProjectId) {// Tạo folder chứa video nền
        String pathProjectIdVideoThum = pathProjectId + "/videoThum";
        File fileProjectIdVideoThum = new File(pathProjectIdVideoThum);
        if (!fileProjectIdVideoThum.exists()) {
            boolean created = fileProjectIdVideoThum.mkdirs();
            if (!created) {
                System.out.println("lỗi tạo thư mục");
            }
        }
        return pathProjectIdVideoThum;
    }

    private String createImageThumbFolder(String pathProjectId) {// Tạo folder chứa ảnh nền
        String pathProjectIdImgThum = pathProjectId + "/imgThum";
        File fileProjectIdImgThum = new File(pathProjectIdImgThum);
        if (!fileProjectIdImgThum.exists()) {
            boolean createdImgThum = fileProjectIdImgThum.mkdirs();
            if (!createdImgThum) {
                // Xử lý lỗi khi không thể tạo thư mục
            }
        }
        return pathProjectIdImgThum;
    }

    private boolean clearFileInFolder(String pathProjectIdImgDesc) {// Xóa tất cả file có trong thư mục truyền vào trước
                                                                    // đó
        File fileProjectIdImgDesc = new File(pathProjectIdImgDesc);
        if (fileProjectIdImgDesc.exists()) {
            File[] filesDesc = fileProjectIdImgDesc.listFiles();
            if (filesDesc != null) {
                for (File fileAvatar : filesDesc) {
                    if (fileAvatar.isFile()) {
                        if (!fileAvatar.delete()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void deleteFolder(File folder) {// xóa folder truyền vào
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        // Xóa thư mục sau khi đã xóa tất cả các tệp và thư mục bên trong
        folder.delete();
    }

    private void updateCRealEstate(CRealEstate existingRealEstate, CRealEstate newRealEstate) {// update dự án vào csdl
        existingRealEstate.setTitle(newRealEstate.getTitle());
        existingRealEstate.setRequest(newRealEstate.getRequest());
        existingRealEstate.setProvinceId(newRealEstate.getProvinceId());
        existingRealEstate.setDistrictId(newRealEstate.getDistrictId());
        existingRealEstate.setStreetId(newRealEstate.getStreetId());
        Optional<CWard> ward = wardServiceImpl.getById(newRealEstate.getWard().getId());
        existingRealEstate.setWard(ward.get());
        existingRealEstate.setProvinceId(ward.get().getProvince());
        existingRealEstate.setDistrictId(ward.get().getDistrict().getId());
        existingRealEstate.setAddress(newRealEstate.getAddress());
        existingRealEstate.setPrice(newRealEstate.getPrice());
        existingRealEstate.setBathroom(newRealEstate.getBathroom());
        existingRealEstate.setBedroom(newRealEstate.getBedroom());
        existingRealEstate.setDescription(newRealEstate.getDescription());
        existingRealEstate.setDateUpdate(new Date());
        realEstateServiceImpl.saveRealEstate(existingRealEstate);
    }
    private void postProject(CRealEstate newRealEstate){
        newRealEstate.setProvinceId(newRealEstate.getWard().getProvince());
        newRealEstate.setDistrictId(newRealEstate.getWard().getDistrict().getId());
        newRealEstate.setStatus(0L);
        realEstateServiceImpl.saveRealEstate(newRealEstate);
    }
    // Lưu ảnh vào thư mục imgDesc và thêm thông tin ảnh vào cơ sở dữ liệu đã có vào
    // csdl
    private void saveImagesToDescFolder(String pathProjectIdImgDesc, MultipartFile[] files,
            CRealEstate realEstate) throws IOException {
        for (MultipartFile imgDesc : files) {
            if (!imgDesc.isEmpty()) {
                String filename = imgDesc.getOriginalFilename();
                String fullPath = pathProjectIdImgDesc + "/" + filename;
                File imgFile = new File(fullPath);
                imgDesc.transferTo(imgFile);
                String srcFileDisplayHtml = "/images/Project/" + realEstate.getId() + "/imgDesc/" + filename;
                CPhoto photo = new CPhoto();
                photo.setPath(srcFileDisplayHtml);
                photo.setRealestate(realEstate);
                photoServiceImpl.savePhoto(photo);
            }
        }
    }

    // Lưu ảnh vào thư mục imgThum và cập nhật thông tin ảnh chính của dự án đã có
    // vào csdl
    private void saveMainImageToThumbFolder(String pathProjectIdImgThum, MultipartFile file,
            CRealEstate existingRealEstate, CRealEstate newRealEstate) throws IOException {
        String filename = file.getOriginalFilename();
        String fullPath = pathProjectIdImgThum + "/" + filename;
        File imgFile = new File(fullPath);
        file.transferTo(imgFile);
        String srcFileDisplayHtml = "/images/Project/" + existingRealEstate.getId() + "/imgThum/" + filename;
        updateCRealEstate(existingRealEstate, newRealEstate);
        existingRealEstate.setPhotoMain(srcFileDisplayHtml);
        realEstateServiceImpl.saveRealEstate(existingRealEstate);
    }

    // Lưu ảnh vào thư mục Video và cập nhật thông tin video miêu tả của dự án vào
    // csdl
    private void saveVideoToThumbFolder(String pathProjectIdVideoThum, MultipartFile video,
            CRealEstate existingRealEstate) throws IOException {
        String filename = video.getOriginalFilename();
        String fullPath = pathProjectIdVideoThum + "/" + filename;
        File imgFile = new File(fullPath);
        video.transferTo(imgFile);
        String srcFileDisplayHtml = "/images/Project/" + existingRealEstate.getId() + "/videoThum/" + filename;
        existingRealEstate.setLinkVideo(srcFileDisplayHtml);
        realEstateServiceImpl.saveRealEstate(existingRealEstate);
    }

    // Lưu ảnh vào thư mục Avatar và cập nhật thông tin avatar của tài khoản đã có
    // vào csdl
    private void saveAvatarToCustomerFolder(String pathCustomerIdAvatar, MultipartFile avatar,
            CCustomers existingCustomer) throws IOException {
        String filename = avatar.getOriginalFilename();
        String fullPath = pathCustomerIdAvatar + "/" + filename;
        File imgFile = new File(fullPath);
        avatar.transferTo(imgFile);
        String srcFileDisplayHtml = "/images/Customer/" + existingCustomer.getId() + "/" + filename;
        existingCustomer.setAvatar(srcFileDisplayHtml);
        customerServiceImpl.saveCustomers(existingCustomer);
    }

    // Lưu ảnh vào thư mục imgThum và cập nhật thông tin ảnh chính của dự án mới
    // csdl
    private void UploadThumImgNewProject(String pathProjectIdImgThum, MultipartFile file, CRealEstate newRealEstate)
            throws IOException {
        String filename = file.getOriginalFilename();
        String fullPath = pathProjectIdImgThum + "/" + filename;
        File imgFile = new File(fullPath);
        file.transferTo(imgFile);
        String srcFileDisplayHtml = "/images/Project/" + newRealEstate.getId() + "/imgThum/" + filename;
        newRealEstate.setPhotoMain(srcFileDisplayHtml);
        realEstateServiceImpl.saveRealEstate(newRealEstate);
    }

    // Lưu ảnh vào thư mục imgThum và cập nhật thông tin ảnh chính của blog mới
    // csdl
    private void UploadThumImgBlog(String pathBlogIdImgThum, MultipartFile file, CBlog blog)
            throws IOException {
        String filename = file.getOriginalFilename();
        String fullPath = pathBlogIdImgThum + "/" + filename;
        File imgFile = new File(fullPath);
        file.transferTo(imgFile);
        String srcFileDisplayHtml = "/images/Blog/" + blog.getId() + "/imgThum/" + filename;
        blog.setPhotoMain(srcFileDisplayHtml);
        blogServiceImpl.saveCBlog(blog);
    }

}
