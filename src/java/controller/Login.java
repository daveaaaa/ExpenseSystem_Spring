package controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author david
 */
@Controller
@RequestMapping("login")
@SessionAttributes("user")
public class Login {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(Map<String, Object> model) {
        String view = "login";
        try {
            model.User user = new model.User();
            model.put("user", user);
        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        return (view);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @ModelAttribute("user") model.User user, Map<String, Object> model) {
        String view = "login";
        try {
            if (business.User.login(user)) {
                view = "receiptProviderHomepage";
                request.getSession(true).setAttribute("user", user);
            } else {
                model.put("message", "Username or password invalid");
            }
        } catch (Exception ex) {
            //TODO: Log Exception 
        }
        return view;
    }

}
