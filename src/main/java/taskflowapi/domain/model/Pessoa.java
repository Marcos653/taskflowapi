package taskflowapi.domain.model;

import taskflowapi.domain.enums.EDepartamento;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pessoa", indexes = {
        @Index(name = "idx_pessoa_nome", columnList = "nome"),
        @Index(name = "idx_pessoa_id", columnList = "id")
})
public class Pessoa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "departamento", nullable = false)
    private EDepartamento departamento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tarefa> tarefas = new HashSet<>();
}
