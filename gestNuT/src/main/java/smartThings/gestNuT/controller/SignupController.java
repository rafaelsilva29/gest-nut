package smartThings.gestNuT.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import smartThings.gestNuT.model.User;
import smartThings.gestNuT.service.UserService;
import smartThings.gestNuT.support.web.AjaxUtils;
import smartThings.gestNuT.support.web.MessageHelper;

@Controller
class SignupController {

	private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private UserService userService;

	//mudar para o anteiror

	@GetMapping("signup")
	String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new User());
		if (AjaxUtils.isAjaxRequest(requestedWith)) {
			return SIGNUP_VIEW_NAME.concat(" :: user");
		}
		return SIGNUP_VIEW_NAME;
	}

	@PostMapping("signup")
	public String signup(@Valid @ModelAttribute User user, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		user.setRole("ROLE_USER");
		userService.saveUser(user);
		userService.signIn(user);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}
