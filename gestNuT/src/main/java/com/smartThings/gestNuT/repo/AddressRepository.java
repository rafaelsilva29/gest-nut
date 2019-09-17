package com.smartThings.gestNuT.repo;

import com.smartThings.gestNuT.model.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>{
    Address findByCity(String city);
    Address findByName(String name);
    Address findByPostalCode(String postalCode);
    Address findByStreet(String street);
}