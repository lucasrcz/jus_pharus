package br.com.example.juspharus.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDto {
        

    @NotBlank(message = "Nome da Rua deve ser preenchido")
    public String rua;
    @NotBlank(message = "Número deve ser preenchido")
    public Long numero;

    public String complemento;
    @NotBlank(message = "CEP deve ser preenchido")
    public String cep;

}
