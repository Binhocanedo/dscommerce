package com.fabiocanedo.dscommerce.repositories;

import com.fabiocanedo.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
