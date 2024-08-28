package com.fabiocanedo.dscommerce.services;

import com.fabiocanedo.dscommerce.dto.shopping.OrderDTO;
import com.fabiocanedo.dscommerce.entities.Order;
import com.fabiocanedo.dscommerce.repositories.OrderRepository;
import com.fabiocanedo.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public OrderDTO findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }


}
