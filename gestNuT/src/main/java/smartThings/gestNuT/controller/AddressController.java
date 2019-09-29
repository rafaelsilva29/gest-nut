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
import smartThings.gestNuT.model.User;
import smartThings.gestNuT.service.AddressService;
import smartThings.gestNuT.service.UserService;


@RestController
public class AddressController {

    private static final String ADDRESSES_VIEW_NAME = "address/addresses";
    private static final String ADDRESS_VIEW_NAME = "address/address";
    private static final String ADDADDRESS_VIEW_NAME = "address/addAddress";
    private final AddressService addressService;
    private final UserService userService;

    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @RequestMapping(value = "addresses", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER"})
    public ModelAndView showAddressesPage(ModelAndView modelAndView, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        modelAndView.setViewName(ADDRESSES_VIEW_NAME);
        modelAndView.addObject("addresses", addressService.findByUserId(user.getId()));
        return modelAndView;
    }

    
    @RequestMapping(value = "address/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER"})
    public ModelAndView showAddressPage(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName(ADDRESS_VIEW_NAME);
        modelAndView.addObject("address", addressService.findOne(id));
        return modelAndView;
    }

    @RequestMapping(value = "/addAddress")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER"})
    public ModelAndView showAddAddressPage(ModelAndView modelAndView, Address address) {
        modelAndView.addObject("address", address);
        modelAndView.setViewName(ADDADDRESS_VIEW_NAME);
        return modelAndView;                          
    }

    // Process form input data
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER"})
	public ModelAndView processAddAddressForm(Principal principal, ModelAndView modelAndView, @Valid Address address, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes ra) {
        User user = userService.findByEmail(principal.getName());
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName(ADDADDRESS_VIEW_NAME);		
		} 
		else {
            address.setUser(user);
		    addressService.saveAddress(address);
            modelAndView.addObject("confirmationMessage", "Address added successful...");
            modelAndView.setViewName(ADDRESSES_VIEW_NAME);
            modelAndView.addObject("addresses", addressService.findByUserId(user.getId()));
		}
		return modelAndView;
    }
    
     // Process form input data
    @RequestMapping(value = "/address/delete/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ModelAndView processDeleteAddressForm(@PathVariable("id") Long id, ModelAndView modelAndView, Principal principal) {
        addressService.deleteAddress(id);
        User user = userService.findByEmail(principal.getName());
        modelAndView.setViewName(ADDRESSES_VIEW_NAME);
        modelAndView.addObject("addresses", addressService.findByUserId(user.getId()));
		return modelAndView;
	}

}