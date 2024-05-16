package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;

public interface IGatewayPagamentoConfirmacao {
    void confirmacaoPagamento(Codigo codigoPedido, StatusPagamento statusPagamento);
}
