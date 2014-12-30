/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author david
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@Controller
@RequestMapping("uploadReceipt")
@SessionAttributes("user")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)
public class RecieptUpload {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest request) {

        //TODO: Some business logic to ensure that user can see things
        return "uploadReceipt";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(HttpServletRequest request) {
        String view = "";
        
        
        
        File file = new File(request.getParameter("file")); 
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream
//                        = new BufferedOutputStream(new FileOutputStream(new File(new java.util.Date().getTime() + "")));
//                stream.write(bytes);
//                stream.close();
//                view = "You successfully uploaded!";
//            } catch (Exception e) {
//                view = "You failed to upload: "+ e.getMessage();
//            }
//        } else {
//            view = "The file was empty.";
//        }

        return view;

    }

}
