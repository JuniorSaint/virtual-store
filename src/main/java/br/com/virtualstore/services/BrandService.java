package br.com.virtualstore.services;

import br.com.virtualstore.configs.CP;
import br.com.virtualstore.configs.UsefulMethods;
import br.com.virtualstore.exceptions.EntityNotFoundException;
import br.com.virtualstore.models.entities.Brand;
import br.com.virtualstore.models.responses.BrandResponse;
import br.com.virtualstore.repositories.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsefulMethods usefulMethods;

    @Transactional
    public BrandResponse saveUpdate(Brand entity) {
        Brand verifyIfBankExist = repository.findById(entity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Brand" + CP.NOT_FOUND + "id: " + entity.getId()));

        return mapper.map(repository.save(entity), BrandResponse.class);
    }

    @Transactional(readOnly = true)
    public BrandResponse findById(Long id) {
        return repository.findById(id)
                .map(result -> mapper.map(result, BrandResponse.class))
                .orElseThrow(() -> new EntityNotFoundException("Brand" + CP.NOT_FOUND + "id: " + id));
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> findAllBanks() {
        return usefulMethods.mapListIntoDtoList(repository.findAll(), BrandResponse.class);
    }

    @Transactional
    public String delete(Long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.deleteById(id);
                    return "Brand" + CP.DELETE_MESSAGE;
                })
                .orElseThrow(() -> new EntityNotFoundException("Brand" + CP.NOT_FOUND + "id: " + id));
    }
}
