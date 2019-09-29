package smartThings.gestNuT.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import smartThings.gestNuT.model.User;
import smartThings.gestNuT.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/current")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public User currentAccount(Principal principal) {
        return userService.findByEmail(principal.getName());
    } 
    
    @GetMapping("user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public User user(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }
}

