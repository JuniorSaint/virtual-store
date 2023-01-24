package br.com.virtualstore.services;

import br.com.virtualstore.configs.CP;
import br.com.virtualstore.configs.UsefulMethods;
import br.com.virtualstore.exceptions.EntityNotFoundException;
import br.com.virtualstore.models.entities.Brand;
import br.com.virtualstore.models.entities.Category;
import br.com.virtualstore.models.responses.BrandResponse;
import br.com.virtualstore.models.responses.CategoryResponse;
import br.com.virtualstore.repositories.BrandRepository;
import br.com.virtualstore.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsefulMethods usefulMethods;

    @Transactional
    public CategoryResponse saveUpdate(Category entity) {
        Category verifyIfBankExist = repository.findById(entity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Category" + CP.NOT_FOUND + "id: " + entity.getId()));
        return mapper.map(repository.save(entity), CategoryResponse.class);
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return repository.findById(id)
                .map(result -> mapper.map(result, CategoryResponse.class))
                .orElseThrow(() -> new EntityNotFoundException("Category" + CP.NOT_FOUND + "id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllBanks() {
        return usefulMethods.mapListIntoDtoList(repository.findAll(), CategoryResponse.class);
    }

    @Transactional
    public String delete(Long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.deleteById(id);
                    return "Category" + CP.DELETE_MESSAGE;
                })
                .orElseThrow(() -> new EntityNotFoundException("Category" + CP.NOT_FOUND + "id: " + id));
    }
}
