package com.fabiocanedo.dscommerce.services;

import com.fabiocanedo.dscommerce.dto.ProductDTO;
import com.fabiocanedo.dscommerce.entities.Product;
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


import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){

        Optional<Product> result = repository.findById(id);
        Product product = result.orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){

        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);

        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            //Instancia o produto pelo ID
            Product entity = repository.getReferenceById(id);
            //Copia os dados do DTO para Entity
            copyDtoToEntity(dto, entity);
            //Salva no banco de dados
            entity = repository.save(entity);
            //Retorna o produto DTO
            return new ProductDTO(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void  delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
