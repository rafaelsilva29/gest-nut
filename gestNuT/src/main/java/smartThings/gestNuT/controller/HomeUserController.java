package smartThings.gestNuT.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeUserController {

    private static final String HOMEUSER_VIEW_NAME = "home/homeUserSignedIn";
    private static final String HOME_VIEW_NAME = "home/homeNotSignedIn";

    @RequestMapping(value = "/homeUserSignedIn")
    public ModelAndView showHomeUserPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        String role =  authResult.getAuthorities().toString();
        if(role.contains("ROLE_USER")) {
            modelAndView.setViewName(HOMEUSER_VIEW_NAME);
            return modelAndView;                          
        } else {
            modelAndView.setViewName(HOME_VIEW_NAME);
            return modelAndView;   
        }
    }
}
