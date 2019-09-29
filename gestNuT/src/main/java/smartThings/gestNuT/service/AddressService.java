package smartThings.gestNuT.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartThings.gestNuT.model.Address;
import smartThings.gestNuT.repo.AddressRepository;

@Service("addressService")
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@PostConstruct
	protected void initialize() {
		
	}

	public Address saveAddress(Address address) {
		addressRepository.save(address);
		return address;
	}

	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}

	public Iterable<Address> findByUserId(Long id) {
		return addressRepository.findByUserId(id);
	}

	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}

	public void deleteAddress(Long id) {
		addressRepository.delete(id);
	}

}