package br.com.virtualstore.services;

import br.com.virtualstore.configs.CP;
import br.com.virtualstore.configs.UsefulMethods;
import br.com.virtualstore.exceptions.EntityNotFoundException;
import br.com.virtualstore.models.entities.Category;
import br.com.virtualstore.models.entities.Product;
import br.com.virtualstore.models.responses.CategoryResponse;
import br.com.virtualstore.models.responses.ProductResponse;
import br.com.virtualstore.repositories.CategoryRepository;
import br.com.virtualstore.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsefulMethods usefulMethods;

    @Transactional
    public ProductResponse saveUpdate(Product entity) {
        Product verifyIfBankExist = repository.findById(entity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product" + CP.NOT_FOUND + "id: " + entity.getId()));
        return mapper.map(repository.save(entity), ProductResponse.class);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(result -> mapper.map(result, ProductResponse.class))
                .orElseThrow(() -> new EntityNotFoundException("Product" + CP.NOT_FOUND + "id: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAllBanks() {
        return usefulMethods.mapListIntoDtoList(repository.findAll(), ProductResponse.class);
    }

    @Transactional
    public String delete(Long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.deleteById(id);
                    return "Product" + CP.DELETE_MESSAGE;
                })
                .orElseThrow(() -> new EntityNotFoundException("Product" + CP.NOT_FOUND + "id: " + id));
    }
}
