package smartThings.gestNuT.repo;

import org.springframework.data.repository.CrudRepository;

import smartThings.gestNuT.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{
    Address findByCity(String city);
    Address findByName(String name);
    Address findByPostalCode(String postalCode);
    Address findByStreet(String street);
}