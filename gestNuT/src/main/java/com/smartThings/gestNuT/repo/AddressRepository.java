package com.smartThings.gestNuT.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.smartThings.gestNuT.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{
    List<Address> findByCity(String city);
}