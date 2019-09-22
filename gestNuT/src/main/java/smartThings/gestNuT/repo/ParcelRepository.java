package smartThings.gestNuT.repo;

import java.sql.Date;

import smartThings.gestNuT.model.Parcel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    Parcel findByDate(Date date);
    Parcel findByProduct(String product);
}