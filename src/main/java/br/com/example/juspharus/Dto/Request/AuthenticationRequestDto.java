package br.com.example.juspharus.Dto.Request;


import br.com.example.juspharus.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDto {

    @NotNull(message = "Login deve ser preenchido")
    String login;
    @NotNull(message = "Senha deve ser preenchida")
    String password;

}
