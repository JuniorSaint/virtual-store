package br.com.virtualstore.models.responses;

import br.com.virtualstore.models.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private String image;
    private RoleEnum role;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}