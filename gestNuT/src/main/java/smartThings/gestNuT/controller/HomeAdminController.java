package smartThings.gestNuT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeAdminController {

    private static final String HOMEADMIN_VIEW_NAME = "home/homeAdminSignedIn";

    @RequestMapping(value="/homeAdminSignedIn")
	public ModelAndView showHomeAdminPage(ModelAndView modelAndView){
		modelAndView.setViewName(HOMEADMIN_VIEW_NAME);
		return modelAndView;
    }
}
