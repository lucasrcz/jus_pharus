package br.com.example.juspharus.Dto.Response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.example.juspharus.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    
    public Long id;

    public String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy") 
    private Date birthdayDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date creationDate;

    private String cpf;

    private String telefone;
    
    private String telefone2;

    private EnderecoResponseDTO endereco;

    private String perfil;

    public ClienteResponseDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome= usuario.getNome();
        this.birthdayDate = usuario.getBirthdayDate();
        this.creationDate = usuario.getCreationDate();
        this.cpf = usuario.getIdentificacao();
        this.telefone = usuario.getTelefone();
        this.telefone2 = usuario.getTelefone2();
        if(usuario.getEndereco() != null){
            this.endereco = new EnderecoResponseDTO(usuario.getEndereco());
        }
        this.perfil = usuario.getPerfilEnum().getValue();
    }
}

