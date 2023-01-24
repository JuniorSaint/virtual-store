package br.com.virtualstore.repositories;

import br.com.virtualstore.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT obj FROM User obj " +
            " LOWER(obj.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            " OR LOWER(obj.email) LIKE LOWER(CONCAT('%', :search, '%')) ")
    Page<User> findBySearch(@Param("search") String search, Pageable pageable);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
