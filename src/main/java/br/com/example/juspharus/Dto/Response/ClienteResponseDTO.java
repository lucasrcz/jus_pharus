package br.com.example.juspharus.Dto.Response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.example.juspharus.entity.Cliente;
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

    public ClienteResponseDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome= cliente.getNome();
        this.birthdayDate = cliente.getBirthdayDate();
        this.creationDate = cliente.getCreationDate();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.telefone2 = cliente.getTelefone2();
        if(cliente.getEndereco() != null){
            this.endereco = new EnderecoResponseDTO(cliente.getEndereco());
        }
    }
}

