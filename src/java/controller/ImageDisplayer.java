///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import com.mongodb.gridfs.GridFSDBFile;
//import java.io.IOException;
//import java.util.Map;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
///**
// *
// * @author david
// */
//@Controller
//@RequestMapping("image")
//public class ImageDisplayer {
//
//    @ResponseBody
//    public void getImage(String filename, HttpServletResponse response) {
//        try {
//            byte[] image = business.Reciept.getImage(filename);
//
//            response.setHeader("Accept-ranges", "bytes");
//            response.setContentType("image/jpeg");
//            response.setContentLength(image.length);
//            response.setHeader("Expires", "0");
//            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
//            response.setHeader("Content-Description", "File Transfer");
//            response.setHeader("Content-Transfer-Encoding:", "binary");
//
//            ServletOutputStream out = response.getOutputStream();
//
//            out.write(image);
//            out.flush();
//            out.close();
//        } catch (IOException ioE) {
//            //TODO: logging
//        }
//    }
//
//}
