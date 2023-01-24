package br.com.virtualstore.services;

import br.com.virtualstore.configs.CP;
import br.com.virtualstore.configs.UsefulMethods;
import br.com.virtualstore.exceptions.BadRequestException;
import br.com.virtualstore.exceptions.EntityNotFoundException;
import br.com.virtualstore.models.entities.User;
import br.com.virtualstore.models.requests.ChangePasswordRequest;
import br.com.virtualstore.models.responses.UserResponse;
import br.com.virtualstore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.virtualstore.configs.CP.DELETE_MESSAGE;


@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UsefulMethods usefulMethods;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse save(User entity) {
        entity.setEmail(entity.getEmail().toLowerCase());
        if (repository.findByEmail(entity.getEmail()).isPresent()) {
            throw new BadRequestException("Already exist user with this email: " + entity.getEmail() + ", try with another one");
        }
//        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return mapper.map(repository.save(entity), UserResponse.class);
    }

    @Transactional
    public UserResponse update(User entity) {
        entity.setEmail(entity.getEmail().toLowerCase());
        return repository.findByEmail(entity.getEmail())
                .map(result -> {
//        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                    return mapper.map(repository.save(entity), UserResponse.class);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("User" + CP.NOT_FOUND + " id: " + entity.getEmail()));
    }

    public String changePassword(ChangePasswordRequest request) {
        return repository.findById(request.getId())
                .map(result -> {
//                    result.setPassword(passwordEncoder.encode(request.getPassword()));
                    repository.save(result);
                    return "The password was changed with success of the user: " + result.getEmail();
                }).orElseThrow(
                        () -> new EntityNotFoundException("User" + CP.NOT_FOUND + " id: " + request.getId()));
    }

    @Transactional
    public String delete(Long id) {
        return repository.findById(id)
                .map(result -> {
                    repository.deleteById(id);
                    return "User" + DELETE_MESSAGE;
                })
                .orElseThrow(() -> new EntityNotFoundException("User" + CP.NOT_FOUND + "id: " + id));
    }

    public UserResponse findById(Long id) {
        return repository.findById(id)
                .map(result -> mapper.map(result, UserResponse.class))
                .orElseThrow(() -> new EntityNotFoundException("User" + CP.NOT_FOUND + " id:" + id));
    }

//    public Page<UserResponse> findAllWithPageAndSearch(String search, Pageable pageable) {
//        return usefulMethods.mapEntityPageIntoDtoPage(repository.findBySearch(search.trim(), pageable), UserResponse.class);
//    }

    public List<UserResponse> findAllUsers() {
        return usefulMethods.mapListIntoDtoList(repository.findAll(), UserResponse.class);
    }
}
