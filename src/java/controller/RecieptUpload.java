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
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author david
 */
@Controller
@RequestMapping("uploadReceipt")
@SessionAttributes("user")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class RecieptUpload {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        try {

        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        return "uploadReceipt";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String handleUpload(@RequestParam("file") MultipartFile multipartfile, @ModelAttribute("user") model.User user) {
        String view = "";
        try {
            business.Reciept.createReciept(multipartfile, user);
        } catch (Exception ex) {
            //TODO: log exception
            System.exit(0);
        }
        return view;

    }

}
