package smartThings.gestNuT.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import smartThings.gestNuT.model.Address;
import smartThings.gestNuT.model.Parcel;
import smartThings.gestNuT.model.User;
import smartThings.gestNuT.service.AddressService;
import smartThings.gestNuT.service.ParcelService;
import smartThings.gestNuT.service.UserService;


@RestController
public class ParcelController {

    private static final String PARCELS_VIEW_NAME = "parcel/parcels";
    private static final String PARCEL_VIEW_NAME = "parcel/parcel";
    private static final String ADDPARCEL_VIEW_NAME = "parcel/addParcel";

    private final AddressService addressService;
    private final UserService userService;
    private final ParcelService parcelService;

    public ParcelController(AddressService addressService, UserService userService, ParcelService parcelService) {
        this.addressService = addressService;
        this.userService = userService;
        this.parcelService = parcelService;
    }

    @RequestMapping(value = "parcels", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ModelAndView showAddressesPage(ModelAndView modelAndView, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        modelAndView.setViewName(PARCELS_VIEW_NAME);
        modelAndView.addObject("parcels", parcelService.findByUserId(user.getId()));
        return modelAndView;
    }

    
    @RequestMapping(value = "parcel/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLER_ADMIN"})
    public ModelAndView showParcelPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName(PARCEL_VIEW_NAME);
        modelAndView.addObject("parcel", parcelService.findOne(id));
        return modelAndView;
    }

    @RequestMapping(value = "/addParcel")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER"})
    public ModelAndView showAddParcelPage(ModelAndView modelAndView, Parcel parcel) {
        modelAndView.addObject("parcel", parcel);
        modelAndView.setViewName(ADDPARCEL_VIEW_NAME);
        return modelAndView;                          
    }

    // Process form input data
    @RequestMapping(value = "/addParcel", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER"})
	public ModelAndView processAddParcelForm(Principal principal, ModelAndView modelAndView, @PathVariable("nameAddress") String nameAddress, @Valid Parcel parcel, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes ra) {
        User user = userService.findByEmail(principal.getName());
        Address address = addressService.findByName(nameAddress);
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName(ADDPARCEL_VIEW_NAME);		
		} 
		else {
            parcel.setUser(user);
            parcel.setAddress(address);
		    parcelService.saveParcel(parcel);
            modelAndView.addObject("confirmationMessage", "Parcel added successful...");
            modelAndView.setViewName(PARCELS_VIEW_NAME);
            modelAndView.addObject("parcels", parcelService.findByUserId(user.getId()));
		}
		return modelAndView;
    }
    
     // Process form input data
    @RequestMapping(value = "/parcel/delete/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ModelAndView processDeleteParcelForm(@PathVariable("id") Long id, ModelAndView modelAndView, Principal principal) {
        parcelService.deleteParcel(id);
        User user = userService.findByEmail(principal.getName());
        modelAndView.setViewName(PARCELS_VIEW_NAME);
        modelAndView.addObject("parcels", parcelService.findByUserId(user.getId()));
		return modelAndView;
	}

}