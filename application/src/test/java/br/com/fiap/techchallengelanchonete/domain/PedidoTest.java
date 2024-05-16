package br.com.fiap.techchallengelanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;

class PedidoTest {
    
    @Test
    void deveCriarPagamentoQRCode() {
        var pedido = new Pedido();
        assertThat(pedido)
            .isNotNull();
        assertThat(pedido.getProdutos())
            .isNotNull()
            .isEmpty();
    }

}
