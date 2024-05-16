package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import java.util.Optional;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;

public interface IPagamentoPersistence {

    Optional<Pagamento> consultaPagamentoPorCodigoPedido(Codigo codigoPedido);
    void salvaPagamento(Pagamento pagamento);

}
