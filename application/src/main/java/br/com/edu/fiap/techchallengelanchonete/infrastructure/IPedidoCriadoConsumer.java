package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import java.io.IOException;
import java.util.function.Consumer;

import br.com.edu.fiap.techchallengelanchonete.domain.OrdemCompra;

public interface IPedidoCriadoConsumer {
    
    void consome(String nomeFila, Consumer<OrdemCompra> consumidorMensagem) throws IOException;

}
