//package cn.jdevelops.file;
//
//import cn.jdevelops.file.bean.ExpireDateDTO;
//import cn.jdevelops.file.bean.UploadDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//
//@SpringBootTest
//class FileApplicationTests {
//
//    @Autowired
//    private  OssOperateAPI fileOperation;
//
//
//
//    @Test
//    void contextLoads() {
//
////        File file = new File("D:\\ImageFiles\\credits\\image\\shop\\1618368816089.PNG");
////        DiskFileItemFactory factory = new DiskFileItemFactory(16, null);
////        FileItem item = factory.createItem(file.getName(), "text/plain", true, file.getName());
////        MultipartFile multipartFile = new CommonsMultipartFile(item);
//
//        MultipartFile commonsMultipartFile = new CommonsMultipartFile(Util.createFileItem("D:\\ImageFiles\\credits\\image\\shop\\1618368816089.PNG"));
//        UploadDTO uploadDTO = new UploadDTO();
//        uploadDTO.setBucket("test1111");
//        uploadDTO.setChildFolder("");
//        uploadDTO.setFile(commonsMultipartFile);
//        fileOperation.uploadFile(uploadDTO);
//    }
//
//
//    @Test
//    void expireDateUrl() {
//        ExpireDateDTO expireDateDTO = new ExpireDateDTO();
//        expireDateDTO.setBucket("test1111");
//        expireDateDTO.setChildFolder_FreshName("123/20211215160853365.jpg");
//        expireDateDTO.setExpires(100);
//        System.out.println(fileOperation.expireDateUrl(expireDateDTO));
//    }
//
//}
