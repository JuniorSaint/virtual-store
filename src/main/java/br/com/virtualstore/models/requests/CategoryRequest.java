package br.com.virtualstore.models.requests;

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
@Getter
@Setter
@AllArgsConstructor
public class CategoryRequest {
    private Long id;
    private String category;
}
