package com.smartThings.gestNuT.repo;

import java.sql.Date;

import com.smartThings.gestNuT.model.Parcel;

import org.springframework.data.repository.CrudRepository;

public interface ParcelRepository extends CrudRepository<Parcel, Integer> {
    Parcel findByDate(Date date);
    Parcel findByProduct(String product);
}