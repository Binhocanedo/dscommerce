package com.fabiocanedo.dscommerce.repositories;

import com.fabiocanedo.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {}
