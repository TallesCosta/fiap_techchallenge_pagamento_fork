package br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento;

import br.com.edu.fiap.techchallengelanchonete.adapter.model.PagamentoAdapterModel;
import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPagamentoPersistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class PagamentoAdapterJPA implements IPagamentoPersistence {
    private PagamentoRepository repository;
    private PagamentoAdapterModel adapter;

    public PagamentoAdapterJPA(PagamentoRepository repository, PagamentoAdapterModel adapter) {
        this.repository = repository;
        this.adapter = adapter;
    }

    @Override
    public Optional<Pagamento> consultaPagamentoPorCodigoPedido(Codigo codigoPedido) {
        return this.repository
                .findByCodigoPedido(codigoPedido.getValor())
                .map(adapter::toDomain);
    }

    @Override
    public Pagamento salvaPagamento(Pagamento pagamento) {
        return this.repository.save(adapter.toModel(pagamento));
    }

}
