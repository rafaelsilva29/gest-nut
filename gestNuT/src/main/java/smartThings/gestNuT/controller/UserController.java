package smartThings.gestNuT.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import smartThings.gestNuT.model.User;
import smartThings.gestNuT.repo.UserRepository;

import java.security.Principal;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user/current")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public User currentAccount(Principal principal) {
        Assert.notNull(principal);
        return userRepository.findByEmail(principal.getName());
    }

    @GetMapping("user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public User user(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }
}

