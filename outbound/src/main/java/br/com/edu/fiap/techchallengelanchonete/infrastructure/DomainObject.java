package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@MappedSuperclass
@SuperBuilder
public abstract class DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao")
    protected Date dataCriacao;

    @Column(name = "data_atualizacao")
    protected Date dataAtualizacao;

    protected DomainObject(long id) {
        this.id = id;
    }

    @PrePersist
    public void setDataCriacao() {
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

    @PreUpdate
    public void setDataAtualizacao() {
        this.dataAtualizacao = new Date();
    }
}
