package br.com.edu.fiap.techchallengelanchonete.domain.Pagador;

import br.com.edu.fiap.techchallengelanchonete.domain.DomainObject;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@SuperBuilder
public class Pagador implements IPagador {
    private Nome nome;
    private Email email;
    private CPF cpf;

    public Pagador() {
        this.nome = new Nome("");
        this.cpf = new CPF("");
        this.email = new Email("");
    }

    public String getPrimeiroNome() {
        if (nome == null)
            return "";

        return this.getNome().getPrimeiro();
    }

    public String getSobrenomes() {
        if (nome == null)
            return "";

        return this.getNome().getSobrenomes();
    }
}
