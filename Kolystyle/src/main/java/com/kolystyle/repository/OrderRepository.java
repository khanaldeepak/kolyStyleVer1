package com.kolystyle.repository;

import org.springframework.data.repository.CrudRepository;

import com.kolystyle.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
