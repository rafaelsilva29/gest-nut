package smartThings.gestNuT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeUserController {

    private static final String HOMEUSER_VIEW_NAME = "home/homeUserSignedIn";

    @RequestMapping(value="/homeUserSignedIn")
	public ModelAndView showHomeUserPage(ModelAndView modelAndView){
		modelAndView.setViewName(HOMEUSER_VIEW_NAME);
		return modelAndView;
    }
}
