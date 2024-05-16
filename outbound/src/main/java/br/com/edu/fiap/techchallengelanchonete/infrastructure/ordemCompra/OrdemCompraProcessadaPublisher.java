package br.com.edu.fiap.techchallengelanchonete.infrastructure.ordemCompra;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.com.edu.fiap.techchallengelanchonete.adapter.dto.PagamentoAdapterDTO;
import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IOrdemCompraProcessadaPublisher;
import br.com.edu.fiap.techchallengelanchonete.messaging.RabbitMqActor;
import br.com.edu.fiap.techchallengelanchonete.messaging.RabbitMqConnFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OrdemCompraProcessadaPublisher extends RabbitMqActor implements IOrdemCompraProcessadaPublisher {
    
    private PagamentoAdapterDTO pagamentoAdapterDTO;

    public OrdemCompraProcessadaPublisher(RabbitMqConnFactory rabbitMqConnFactory, PagamentoAdapterDTO pagamentoAdapterDTO) throws IOException {
        super(rabbitMqConnFactory);
        this.pagamentoAdapterDTO = pagamentoAdapterDTO;
    }

    @Override
    public void publica(Pagamento pagamento, String nomeFila) throws IOException {
        var pagamentoDTO = this.pagamentoAdapterDTO.toDTO(pagamento);
        
        var objectMapper = new ObjectMapper();
        var mensagem = objectMapper.writeValueAsString(pagamentoDTO);

        subscribe(nomeFila);
        channel.basicPublish("", nomeFila, null, mensagem.getBytes());
    }

}
