//package com.example;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.multipart.MultipartFile;
//@Controller
//public class MyFileUploadController {
//    @RequestMapping(value = "/")
//    public String homePage() {
//
//        return "index";
//    }
//
//    // GET: Hiển thị trang form upload
//    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
//    public String uploadOneFileHandler(Model com.example.model) {
//
//        MyUploadForm myUploadForm = new MyUploadForm();
//        com.example.model.addAttribute("myUploadForm", myUploadForm);
//
//        return "uploadOneFile";
//    }
//
//    // POST: Sử lý Upload
//    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
//    public String uploadOneFileHandlerPOST(HttpServletRequest request, //
//                                           Model com.example.model, //
//                                           @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
//
//        return this.doUpload(request, com.example.model, myUploadForm);
//
//    }
//
//    // GET: Hiển thị trang form upload
//    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.GET)
//    public String uploadMultiFileHandler(Model com.example.model) {
//
//        MyUploadForm myUploadForm = new MyUploadForm();
//        com.example.model.addAttribute("myUploadForm", myUploadForm);
//
//        return "uploadMultiFile";
//    }
//
//    // POST: Sử lý Upload
//    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
//    public String uploadMultiFileHandlerPOST(HttpServletRequest request, //
//                                             Model com.example.model, //
//                                             @ModelAttribute("myUploadForm") MyUploadForm myUploadForm) {
//
//        return this.doUpload(request, com.example.model, myUploadForm);
//
//    }
//
//    private String doUpload(HttpServletRequest request, Model com.example.model, //
//                            MyUploadForm myUploadForm) {
//
//        String description = myUploadForm.getDescription();
//        System.out.println("Description: " + description);
//
//        // Thư mục gốc upload file.
//        String uploadRootPath = request.getServletContext().getRealPath("upload");
//        System.out.println("uploadRootPath=" + uploadRootPath);
//
//        File uploadRootDir = new File(uploadRootPath);
//        // Tạo thư mục gốc upload nếu nó không tồn tại.
//        if (!uploadRootDir.exists()) {
//            uploadRootDir.mkdirs();
//        }
//        MultipartFile[] fileDatas = myUploadForm.getFileDatas();
//        //
//        List<File> uploadedFiles = new ArrayList<File>();
//        List<String> failedFiles = new ArrayList<String>();
//
//        for (MultipartFile fileData : fileDatas) {
//
//            // Tên file gốc tại Client.
//            String name = fileData.getOriginalFilename();
//            System.out.println("Client File Name = " + name);
//
//            if (name != null && name.length() > 0) {
//                try {
//                    // Tạo file tại Server.
//                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
//
//                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                    stream.write(fileData.getBytes());
//                    stream.close();
//                    //
//                    uploadedFiles.add(serverFile);
//                    System.out.println("Write file: " + serverFile);
//                } catch (Exception e) {
//                    System.out.println("Error Write file: " + name);
//                    failedFiles.add(name);
//                }
//            }
//        }
//        com.example.model.addAttribute("description", description);
//        com.example.model.addAttribute("uploadedFiles", uploadedFiles);
//        com.example.model.addAttribute("failedFiles", failedFiles);
//        return "uploadResult";
//    }
//}
