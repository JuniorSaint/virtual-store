package br.com.virtualstore.repositories;

import br.com.virtualstore.models.entities.Customer;
import br.com.virtualstore.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
