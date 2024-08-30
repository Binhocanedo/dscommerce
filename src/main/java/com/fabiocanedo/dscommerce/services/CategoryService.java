package com.fabiocanedo.dscommerce.services;

import com.fabiocanedo.dscommerce.dto.CategoryDTO;
import com.fabiocanedo.dscommerce.dto.ProductDTO;
import com.fabiocanedo.dscommerce.dto.ProductMinDTO;
import com.fabiocanedo.dscommerce.entities.Category;
import com.fabiocanedo.dscommerce.entities.Product;
import com.fabiocanedo.dscommerce.repositories.CategoryRepository;
import com.fabiocanedo.dscommerce.repositories.ProductRepository;
import com.fabiocanedo.dscommerce.services.exceptions.DatabaseException;
import com.fabiocanedo.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAllCategories(){
        List<Category> result = repository.findAll();
        return result.stream().map(CategoryDTO::new).toList();
    }
}
