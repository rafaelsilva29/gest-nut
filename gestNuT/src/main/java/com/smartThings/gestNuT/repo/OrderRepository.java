package com.smartThings.gestNuT.repo;

import org.springframework.data.repository.CrudRepository;
import com.smartThings.gestNuT.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

}