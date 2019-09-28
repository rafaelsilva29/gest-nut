package smartThings.gestNuT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SigninController {

    private static final String SIGNIN_VIEW_NAME = "signin/signin";

    @RequestMapping(value="/signin", method = RequestMethod.GET)
	public ModelAndView showSigninPage(ModelAndView modelAndView){
		modelAndView.setViewName(SIGNIN_VIEW_NAME);
		return modelAndView;
    }
}
