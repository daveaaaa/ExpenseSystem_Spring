/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controller;

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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author david
 */
@Controller
@RequestMapping("parseReceipt")
@SessionAttributes({"currentUser", "receipt"})
public class ParseReceipt {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        try {

        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        return "uploadReceipt";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView parseReceipt(@ModelAttribute("receipt") business.businessModel.Receipt receipt, @ModelAttribute("currentUser") business.businessModel.User user) {
        String view = "receiptCorrection";
        ModelAndView mav = new ModelAndView();
        try {
            receipt = business.businessLogic.Reciept.parseReceipt(receipt);
            mav.addObject("receipt", receipt);

        } catch (Exception ex) {
            //TODO: log exception
        }

        mav.setViewName(view);
        return mav;

    }

}
