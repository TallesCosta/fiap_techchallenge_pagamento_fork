package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagador.PagadorAnonimo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;

class PagadorAnonimoTest {
    
    @Test
    void deveCriarClienteVazio() {
        var pagador = new PagadorAnonimo();

        assertAll("Atributos vazios", 
            () -> assertThat(pagador).isNotNull(),
            () -> assertThat(pagador.getCpf()).isNotNull(),
            () -> assertThat(pagador.getNome()).isNotNull(),
            () -> assertThat(pagador.getEmail()).isNotNull(),
            () -> assertThat(pagador.getPrimeiroNome()).isBlank(),
            () -> assertThat(pagador.getSobrenomes()).isBlank()
        );

        assertThat(pagador.getEmail().getValor()).isEqualTo(Email.EMAIL_PAGAMENTO);
    }

}
