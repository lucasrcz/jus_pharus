package br.com.example.juspharus.entity;





import java.util.Date;

import br.com.example.juspharus.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    
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

    @Column(name = "identificacao" , length = 30)
    private String identificacao;

    @Column(name = "telefone" , length = 50)
    private String telefone;
    
    @Column(name = "telefone2" , length = 50)
    private String telefone2;

    @OneToOne(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @PrimaryKeyJoinColumn
    private Endereco endereco;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", creationDate=" + creationDate +
                ", birthdayDate=" + birthdayDate +
                ", identificacao='" + identificacao + '\'' +
                ", telefone='" + telefone + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                '}';
    }
}
