package presentation.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author david
 */
@Controller
@SessionAttributes("currentUser")

public class UserController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String preLoginUser(Model mod) {
        String view = "login";

        try {
            business.businessModel.User user = new business.businessModel.User();
            mod.addAttribute("user", user);

        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        //mav.setView(view);
        return view;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView loginUser(@ModelAttribute("user") business.businessModel.User user) {
        String view = "login";
        ModelAndView mav = new ModelAndView();
        try {
            if (business.businessLogic.User.login(user)) {
                view = "receiptProviderHomepage";
                mav.addObject("currentUser", user);
            } else {
                mav.addObject("message", "Username or password invalid");
            }
        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        mav.setViewName(view);
        return mav;
    }

    @RequestMapping(value = "addUser", method=RequestMethod.GET)
    public String preAddUser(Model mod){
        String view = "";
        try{
            mod.addAttribute("user", new business.businessModel.User());
        } catch(Exception ex){
            //TODO: Log Exception
        }
        
        return view;
    }
    
    @RequestMapping(value = "addUser", method=RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") business.businessModel.User newUser){
        String view = "";
        ModelAndView mav = new ModelAndView();
    
        try{
            
        } catch(Exception ex){
            //TODO: Log Exception
        }
        
        mav.setViewName(view);
        return mav;
    }
}
