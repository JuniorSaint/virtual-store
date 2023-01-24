package br.com.virtualstore.services;

import br.com.virtualstore.configs.CP;
import br.com.virtualstore.configs.UsefulMethods;
import br.com.virtualstore.exceptions.BadRequestException;
import br.com.virtualstore.exceptions.EntityNotFoundException;
import br.com.virtualstore.models.entities.Customer;
import br.com.virtualstore.models.requests.ChangePasswordRequest;
import br.com.virtualstore.models.responses.CustomerResponse;
import br.com.virtualstore.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.virtualstore.configs.CP.DELETE_MESSAGE;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsefulMethods usefulMethods;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerResponse save(Customer entity) {
        entity.setEmail(entity.getEmail().toLowerCase());
        if (repository.findByEmail(entity.getEmail()).isPresent()) {
            throw new BadRequestException("Already exist user with this email: " + entity.getEmail() + ", try with another one");
        }
//        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return mapper.map(repository.save(entity), CustomerResponse.class);
    }

    @Transactional
    public CustomerResponse update(Customer entity) {
        entity.setEmail(entity.getEmail().toLowerCase());
        return repository.findByEmail(entity.getEmail())
                .map(result -> {
//        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                    return mapper.map(repository.save(entity), CustomerResponse.class);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Customer" + CP.NOT_FOUND + " id: " + entity.getEmail()));
    }

    public String changePassword(ChangePasswordRequest request) {
        return repository.findById(request.getId())
                .map(result -> {
//                    result.setPassword(passwordEncoder.encode(request.getPassword()));
                    repository.save(result);
                    return "The password was changed with success of the customer: " + result.getEmail();
                }).orElseThrow(
                        () -> new EntityNotFoundException("Customer" + CP.NOT_FOUND + " id: " + request.getId()));
    }

    @Transactional
    public String delete(Long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.deleteById(id);
                    return "Customer" + DELETE_MESSAGE;
                })
                .orElseThrow(() -> new EntityNotFoundException("Customer" + CP.NOT_FOUND + "id: " + id));
    }

    public CustomerResponse findById(Long id) {
        return repository.findById(id)
                .map(result -> mapper.map(result, CustomerResponse.class))
                .orElseThrow(() -> new EntityNotFoundException("Customer" + CP.NOT_FOUND + " id:" + id));
    }

    public Page<CustomerResponse> findAllWithPageAndSearch(String search, Pageable pageable) {
        return usefulMethods.mapEntityPageIntoDtoPage(repository.findBySearch(search.trim(), pageable), CustomerResponse.class);
    }

    public List<CustomerResponse> findAllUsers() {
        return usefulMethods.mapListIntoDtoList(repository.findAll(), CustomerResponse.class);
    }
}
