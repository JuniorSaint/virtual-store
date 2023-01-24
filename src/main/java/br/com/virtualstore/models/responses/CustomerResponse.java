package br.com.virtualstore.models.responses;

import br.com.virtualstore.models.entities.Address;
import br.com.virtualstore.models.requests.UserRequest;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class CustomerResponse extends UserResponse {
    private Address address;
    private String cpf;
    private LocalDate birthday;
    private Integer age;
    private Integer calculateAge(LocalDate birthday) {
        if (birthday != null) {
            return Period.between(birthday, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public Integer getAge() {
        return  calculateAge(getBirthday());
    }
}
