package br.com.virtualstore.models.requests;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ChangePasswordRequest {
    private Long id;
    private String password;
}
