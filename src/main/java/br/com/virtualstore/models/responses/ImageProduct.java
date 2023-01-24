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

public class ImageProduct {

    private Long id;
    private String image;
    private Product product;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
