package br.com.example.juspharus.Dto.Request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    
    @NotBlank(message = "Nome precisar ser preenchido")
    public String nome;


    @NotBlank(message = "Data deve obedecer o padrão: dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthdayDate;
    
    @NotBlank(message = "Identificação precisa ser preenchido")
    private String identificacao;

    @NotBlank(message = "Telefone principal precisa ser preenchido")
    private String telefone;


    private String telefone2;

    private EnderecoRequestDto endereco;

    @NotBlank(message = "Perfil deve ser declarado")
    private String perfilEnum;
}
