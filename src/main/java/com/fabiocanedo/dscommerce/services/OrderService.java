package com.fabiocanedo.dscommerce.services;

import com.fabiocanedo.dscommerce.dto.shopping.OrderDTO;
import com.fabiocanedo.dscommerce.dto.shopping.OrderItemDTO;
import com.fabiocanedo.dscommerce.entities.*;
import com.fabiocanedo.dscommerce.repositories.OrderItemRepository;
import com.fabiocanedo.dscommerce.repositories.OrderRepository;
import com.fabiocanedo.dscommerce.repositories.ProductRepository;
import com.fabiocanedo.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    public OrderDTO findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }


    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for(OrderItemDTO itemDTO : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, product.getPrice(), itemDTO.getQuantity());
            order.getItems().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);

    }
}
