package br.com.edu.fiap.techchallengelanchonete.usecase;

import java.io.IOException;
import java.util.Optional;

import br.com.edu.fiap.techchallengelanchonete.domain.OrdemCompra;
import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.exception.NotFoundResourceException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IOrdemCompraProcessadaPublisher;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPagamentoPersistence;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PagamentoUseCase {
    
    private IPagamentoPersistence pagamentoPersistence;
    private IGatewayPagamentoRegistrador gatewayPagamentoRegistrador;
    private IOrdemCompraProcessadaPublisher ordemCompraProcessadaPublisher;

    public void registraPagamento(OrdemCompra ordemCompra) {
        var pagamento = this.gatewayPagamentoRegistrador.registroPagamento(ordemCompra);

        this.pagamentoPersistence.salvaPagamento(pagamento);
    }

    public void confirmacaoPagamento(Codigo codigoPedido, StatusPagamento statusPagamento) {
        var optionalPagamento = this.consultaPagamento(codigoPedido);

        if (!optionalPagamento.isPresent())
            throw new NotFoundResourceException("Pedido não encontrado!");

        var pagamento = optionalPagamento.get();
        pagamento.setStatus(statusPagamento);

        this.pagamentoPersistence.salvaPagamento(pagamento);

        try {
            this.ordemCompraProcessadaPublisher.publica(pagamento, "PAGAMENTO_PROCESSADO");
        } catch (IOException ex) {
            throw new ApplicationException("Erro ao tentar notificar fila de pagamentos processados");
        }
    }

    public Optional<Pagamento> consultaPagamento(Codigo codigoPedido) {
        return this.pagamentoPersistence.consultaPagamentoPorCodigoPedido(codigoPedido);
    }

}
