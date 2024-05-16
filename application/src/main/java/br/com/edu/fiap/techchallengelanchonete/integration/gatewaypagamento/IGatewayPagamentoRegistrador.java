package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento;

import br.com.edu.fiap.techchallengelanchonete.domain.OrdemCompra;
import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

public interface IGatewayPagamentoRegistrador {
    Pagamento registroPagamento(OrdemCompra ordemCompra) throws ApplicationException;
}
