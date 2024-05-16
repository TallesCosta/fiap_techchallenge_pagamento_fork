package br.com.edu.fiap.techchallengelanchonete.adapter.model;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento.PagamentoModel;

import org.springframework.stereotype.Component;

@Component
public class PagamentoAdapterModel implements IAdapterModel<Pagamento, PagamentoModel> {
    @Override
    public Pagamento toDomain(PagamentoModel pagamentoModel) {
        return Pagamento.builder()
            .id(new Id(pagamentoModel.getId()))
            .codigoPedido(new Codigo(pagamentoModel.getCodigoPedido()))
            .status(StatusPagamento.valueOf(pagamentoModel.getStatusPagamento()))
            .data(new DataCriacao(pagamentoModel.getDataCriacao()))
            .dataExpiracaoPagamento(new DataExpiracao(pagamentoModel.getDataExpiracaoPagamento()))
            .pixCopiaECola(new PagamentoCopiaCola(pagamentoModel.getPixCopiaECola()))
            .pixQRCode64(new PagamentoQRCode(pagamentoModel.getPixQRCode64()))
            .build();
    }

    @Override
    public PagamentoModel toModel(Pagamento pagamento) {
        return PagamentoModel.builder()
            .id(pagamento.getId().getValor())
            .codigoPedido(pagamento.getCodigoPedido().getValor())
            .statusPagamento(pagamento.getStatus().toString())
            .dataCriacao(pagamento.getData().getValor())
            .dataExpiracaoPagamento(pagamento.getDataExpiracaoPagamento().getData())
            .pixCopiaECola(pagamento.getPixCopiaECola().getValor())
            .pixQRCode64(pagamento.getPixQRCode64().getValor())
            .build();
    }
}
