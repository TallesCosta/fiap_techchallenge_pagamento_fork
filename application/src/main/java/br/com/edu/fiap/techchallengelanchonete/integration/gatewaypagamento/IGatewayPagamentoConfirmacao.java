package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;

public interface IGatewayPagamentoConfirmacao {
    Pagamento confirmacaoPagamento(Codigo codigoPedido, StatusPagamento statusPagamento);
}
