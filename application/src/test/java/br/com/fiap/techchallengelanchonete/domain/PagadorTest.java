package br.com.fiap.techchallengelanchonete.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagador.Pagador;

public class PagadorTest {
    
    @Test
    void deveCriarClienteVazio() {
        var pagador = new Pagador();

        assertAll("Atributos vazios", 
            () -> assertThat(pagador).isNotNull(),
            () -> assertThat(pagador.getCpf()).isNotNull(),
            () -> assertThat(pagador.getNome()).isNotNull(),
            () -> assertThat(pagador.getEmail()).isNotNull(),
            () -> assertThat(pagador.getPrimeiroNome()).isBlank(),
            () -> assertThat(pagador.getSobrenomes()).isBlank()
        );
    }

    @Test 
    void deveRetornarPrimeiroNome_quandoNomeNulo() {
        var pagador = new Pagador();
        pagador.setNome(null);

        assertThat(pagador.getPrimeiroNome())
            .isNotNull();
    }

    @Test 
    void deveRetornarSobrenomes_quandoNomeNulo() {
        var pagador = new Pagador();
        pagador.setNome(null);
        
        assertThat(pagador.getSobrenomes())
            .isNotNull();
    }

}
