package br.com.example.juspharus.Dto.Response;

import br.com.example.juspharus.entity.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoResponseDTO {

    private String rua;

    private Long numero;

    private String complemento;

    private String cep;

    public EnderecoResponseDTO(Endereco endereco){
        this.rua = endereco.getRua();
        this.numero=endereco.getNumero();
        this.complemento=endereco.getComplemento();
        this.cep=endereco.getCep();
    }

}
