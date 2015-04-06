/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controller;

/**
 *
 * @author david
 */
import business.businessModel.Item;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author david
 */
@Controller
@SessionAttributes({"currentUser", "receipt"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class ReceiptController {

    @RequestMapping(value = "receiptUpload", method = RequestMethod.GET)
    public String preReceiptUpload() {
        try {

        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        return "receiptUpload";
    }

    @RequestMapping(value = "receiptUpload", method = RequestMethod.POST)
    public ModelAndView handleUpload(@RequestParam("file") MultipartFile multipartfile, @ModelAttribute("currentUser") business.businessModel.User user) {
        String view = "receiptView";
        ModelAndView mav = new ModelAndView();
        try {
            business.businessModel.Receipt receipt = business.businessLogic.Reciept.createReciept(multipartfile, user);
            mav.addObject("receipt", receipt);
        } catch (Exception ex) {
            //TODO: log exception
        }

        mav.setViewName(view);
        return mav;

    }

    @RequestMapping(value = "parseReceipt", method = RequestMethod.GET)
    public String preReceiptParse() {
        try {

        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        return "receiptUpload";
    }

    @RequestMapping(value = "editReceipt", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView editReceipt(@ModelAttribute("receiptID") String receiptID, @ModelAttribute("currentUser") business.businessModel.User user) {

        ModelAndView mav = new ModelAndView();

        try {
            databaseAccess.DBHandler handler = databaseAccess.mongoDB.MongoDBHelper.getDBHandler();
            business.businessModel.Receipt receipt = handler.getReceipt(receiptID);

            mav = correctReceipt(receipt);
        } catch (Exception ex) {
            //TODO: log exception
        }

        return mav;
    }

    private ModelAndView correctReceipt(business.businessModel.Receipt receipt) {
        String view = "receiptCorrection";
        ModelAndView mav = new ModelAndView();
         
            mav.addObject("totalList", receipt.getReceiptItems().getTotal());
            mav.addObject("itemList", receipt.getReceiptItems().getItems());
            mav.addObject("merchantList", receipt.getReceiptItems().getMerchants());
            mav.addObject("receiptID", receipt.getReceiptID());
        

        mav.setViewName(view);
        return mav;
    }

    @RequestMapping(value = "parseReceipt", method = RequestMethod.POST)
    public ModelAndView parseReceipt(@ModelAttribute("receipt") business.businessModel.Receipt receipt, @ModelAttribute("currentUser") business.businessModel.User user) {
        receipt = business.businessLogic.Reciept.parseReceipt(receipt);

        return correctReceipt(receipt);
    }

    @RequestMapping(value = "receiptList", method = RequestMethod.GET)
    public ModelAndView receiptList(@ModelAttribute("currentUser") business.businessModel.User user) {
        String view = "receiptList";
        ModelAndView mav = new ModelAndView();
        ArrayList<business.businessModel.Receipt> receipts = business.businessLogic.Reciept.listReceipts(user);
        mav.addObject("receipts", receipts);
        mav.setViewName(view);
        return mav;
    }

    @RequestMapping(value = "receiptList", method = RequestMethod.POST)
    public ModelAndView selectReceipt(@ModelAttribute("currentUser") business.businessModel.User user) {
        return new ModelAndView();
    }

    @RequestMapping(value = "receiptCorrection/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@RequestBody String json) {

        business.businessLogic.Reciept.parseReceiptUpdateJSON(json);

    }

}
