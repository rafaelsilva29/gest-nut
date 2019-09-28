package smartThings.gestNuT.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeAdminController {

    private static final String HOMEADMIN_VIEW_NAME = "home/homeAdminSignedIn";
    private static final String HOME_VIEW_NAME = "home/homeNotSignedIn";

    @RequestMapping(value = "/homeAdminSignedIn")
    public ModelAndView showHomeAdminPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        String role =  authResult.getAuthorities().toString();
        if(role.contains("ROLE_ADMIN")) {
            modelAndView.setViewName(HOMEADMIN_VIEW_NAME);
            return modelAndView;                          
        } else {
            modelAndView.setViewName(HOME_VIEW_NAME);
            return modelAndView;   
        }
    }
}
