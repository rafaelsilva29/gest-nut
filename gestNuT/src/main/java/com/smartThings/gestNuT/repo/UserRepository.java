package com.smartThings.gestNuT.repo;

import org.springframework.data.repository.CrudRepository;
import com.smartThings.gestNuT.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User findByPhoneNumber(int phoneNumber);
    User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
}