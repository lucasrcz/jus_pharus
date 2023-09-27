package br.com.example.juspharus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "enderecos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    
    @Id
    @Column(name = "usuario_id")
    public Long id;
    @Column(name = "rua")
    public String rua;
    @Column(name = "numero")
    public Long numero;
    @Column(name = "complemento")
    public String complemento;
    @Column(name = "cep")
    public String cep;
    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    public Usuario usuario;

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
