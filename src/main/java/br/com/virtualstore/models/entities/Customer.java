package br.com.virtualstore.models.entities;

import jakarta.persistence.Transient;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer extends User implements Serializable {
    private Address address;
    private String cpf;
    private LocalDate birthday;
    @Transient
    private Integer age;
}
