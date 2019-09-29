package smartThings.gestNuT.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartThings.gestNuT.model.Parcel;
import smartThings.gestNuT.repo.ParcelRepository;

@Service("parcelService")
public class ParcelService {

	@Autowired
	private ParcelRepository parcelRepository;

	@Autowired
	public ParcelService(ParcelRepository parcelRepository) {
		this.parcelRepository = parcelRepository;
	}

	@PostConstruct
	protected void initialize() {
		
	}

	public Parcel saveParcel(Parcel parcel) {
		parcelRepository.save(parcel);
		return parcel;
	}

	public Parcel findOne(Long id) {
		return parcelRepository.findOne(id);
	}

	public Iterable<Parcel> findByUserId(Long id) {
		return parcelRepository.findByUserId(id);
	}

	public Iterable<Parcel> findByAddressId(Long id) {
		return parcelRepository.findByAddressId(id);
	}

	public Iterable<Parcel> findAll() {
		return parcelRepository.findAll();
	}

	public void deleteParcel(Long id) {
		parcelRepository.delete(id);
	}

}