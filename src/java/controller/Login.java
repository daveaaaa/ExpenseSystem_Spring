package controller;

import java.util.Map;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author david
 */

@Controller
@RequestMapping("login")
public class Login {
    
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(Map<String, Object> model){
        User user = new User();
        model.put("user",user); 
        return ("login");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String doLogin(){
        String page = "";
        return (page);
    }
    
}
