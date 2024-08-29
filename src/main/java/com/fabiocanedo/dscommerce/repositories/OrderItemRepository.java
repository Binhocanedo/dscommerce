package com.fabiocanedo.dscommerce.repositories;

import com.fabiocanedo.dscommerce.entities.Order;
import com.fabiocanedo.dscommerce.entities.OrderItem;
import com.fabiocanedo.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {}
