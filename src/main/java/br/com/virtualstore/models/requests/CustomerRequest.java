package br.com.virtualstore.models.requests;

import br.com.virtualstore.models.entities.Address;
import br.com.virtualstore.models.entities.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Transient;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class CustomerRequest extends UserRequest {
    private Address address;
    private String cpf;
    private LocalDate birthday;
}
