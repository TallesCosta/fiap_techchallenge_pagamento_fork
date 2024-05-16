package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import java.io.IOException;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;

public interface IOrdemCompraProcessadaPublisher {
    
    void publica(Pagamento pagamento, String nomeFila) throws IOException;

}
