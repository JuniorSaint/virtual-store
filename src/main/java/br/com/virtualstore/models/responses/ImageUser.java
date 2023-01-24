package br.com.virtualstore.models.responses;

import br.com.virtualstore.models.entities.Product;
import br.com.virtualstore.models.entities.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageUser {

    private Long id;
    private String image;
    private User user;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
