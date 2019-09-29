package smartThings.gestNuT.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import smartThings.gestNuT.service.ParcelService;
import smartThings.gestNuT.service.UserService;


@RestController
public class AdminController {

    private static final String PARCELS_ADMIN_VIEW_NAME = "admin/parcels";
    private static final String PARCEL_ADMIN_VIEW_NAME = "admin/parcel";
    private static final String USER_ADMIN_VIEW_NAME = "admin/user";
    private static final String USERS_ADMIN_VIEW_NAME = "admin/users";

    private final UserService userService;
    private final ParcelService parcelService;

    public AdminController(UserService userService, ParcelService parcelService) {
        this.userService = userService;
        this.parcelService = parcelService;
    }

    @RequestMapping(value = "admin/parcels", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public ModelAndView showParcelsAdminPage(ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName(PARCELS_ADMIN_VIEW_NAME);
        modelAndView.addObject("parcels", parcelService.findAll());
        return modelAndView;
    }

    
    @RequestMapping(value = "admin/parcel/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLER_ADMIN"})
    public ModelAndView showParcelAdminPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName(PARCEL_ADMIN_VIEW_NAME);
        modelAndView.addObject("parcel", parcelService.findOne(id));
        return modelAndView;
    }
    
     // Process form input data
    @RequestMapping(value = "admin/parcel/delete/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
	public ModelAndView processDeleteParcelForm(@PathVariable("id") Long id, ModelAndView modelAndView, Principal principal) {
        parcelService.deleteParcel(id);
        modelAndView.setViewName(PARCELS_ADMIN_VIEW_NAME);
        modelAndView.addObject("parcels", parcelService.findAll());
		return modelAndView;
    }
    
    @RequestMapping(value = "admin/users", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public ModelAndView showUsersAdminPage(ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName(USERS_ADMIN_VIEW_NAME);
        modelAndView.addObject("users",userService.findAll());
        return modelAndView;
    }

    
    @RequestMapping(value = "admin/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public ModelAndView showUserAdminPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName(USER_ADMIN_VIEW_NAME);
        modelAndView.addObject("user", userService.findOne(id));
        return modelAndView;
    }
    
     // Process form input data
    @RequestMapping(value = "admin/user/delete/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
	public ModelAndView processDeleteUserForm(@PathVariable("id") Long id, ModelAndView modelAndView, Principal principal) {
        userService.deleteUser(id);
        modelAndView.setViewName(USERS_ADMIN_VIEW_NAME);
        modelAndView.addObject("users", userService.findOne(id));
		return modelAndView;
	}

}