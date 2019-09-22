package smartThings.gestNuT.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import smartThings.gestNuT.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    Address findByCity(String city);
    Address findByName(String name);
    Address findByPostalCode(String postalCode);
    Address findByStreet(String street);
}