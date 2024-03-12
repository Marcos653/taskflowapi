package taskflowapi.domain.model;

import taskflowapi.domain.enums.EDepartamento;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
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

    @OneToMany(mappedBy = "pessoa")
    private Set<Tarefa> tarefas;
}
