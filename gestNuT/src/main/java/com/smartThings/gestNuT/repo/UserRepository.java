package com.smartThings.gestNuT.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.smartThings.gestNuT.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findByName(String name);
    
    List<User> findByUserName(String userName);
    
    List<User> findByEmail(String email);
    
    List<User> findByPhoneNumber(int phoneNumber);
}