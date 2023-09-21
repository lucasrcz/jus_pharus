package br.com.example.juspharus.Dto.Request;


import br.com.example.juspharus.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    String login;

    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    UsuarioRequestDTO usuarioRequestDTO;

}
