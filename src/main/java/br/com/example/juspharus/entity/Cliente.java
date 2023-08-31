package br.com.example.juspharus.entity;





import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false , length = 255)
    private String nome;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday_date", nullable = false)
    private Date birthdayDate;

    @Column(name = "cpf" , length = 11)
    private String cpf;

    @Column(name = "telefone" , length = 50)
    private String telefone;
    
    @Column(name = "telefone2" , length = 50 , nullable = true)
    private String telefone2;

    @OneToOne(mappedBy = "cliente" , cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Endereco endereco;

}
