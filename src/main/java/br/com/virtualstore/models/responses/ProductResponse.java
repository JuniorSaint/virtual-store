package br.com.virtualstore.models.responses;

import br.com.virtualstore.models.entities.Brand;
import br.com.virtualstore.models.entities.Category;
import br.com.virtualstore.models.entities.Image;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class ProductResponse {
    private Long id;
    private String shortDescription;
    private String longDescription;
    private Double costValue;
    private Double sellValue;
    private Set<Image> images;
    private Brand brand;
    private Category category;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
