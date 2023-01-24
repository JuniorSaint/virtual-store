package br.com.virtualstore.models.requests;

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
public class ProductRequest {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String shortDescription;
    private String longDescription;
    private Double costValue;
    private Double sellValue;
    private Brand brand;
    private Category category;
}
