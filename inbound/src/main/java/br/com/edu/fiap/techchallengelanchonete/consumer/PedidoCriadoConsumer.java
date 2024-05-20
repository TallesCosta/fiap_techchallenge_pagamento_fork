package br.com.edu.fiap.techchallengelanchonete.consumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.rabbitmq.client.DeliverCallback;

import br.com.edu.fiap.techchallengelanchonete.adapter.PedidoAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.OrdemCompra;
import br.com.edu.fiap.techchallengelanchonete.dto.PedidoDTO;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoCriadoConsumer;
import br.com.edu.fiap.techchallengelanchonete.messaging.RabbitMqActor;
import br.com.edu.fiap.techchallengelanchonete.messaging.RabbitMqConnFactory;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class PedidoCriadoConsumer extends RabbitMqActor implements IPedidoCriadoConsumer {

    private PedidoAdapter pedidoAdapter;

    public PedidoCriadoConsumer(RabbitMqConnFactory rabbitMqConnFactory, PedidoAdapter pedidoAdapter) throws IOException {
        super(rabbitMqConnFactory);
        this.pedidoAdapter = pedidoAdapter;

        channel.basicQos(0, 1, false);
    }

    @Override
    public void consome(String nomeFila, Consumer<OrdemCompra> consumidorMensagem) throws IOException {
        subscribe(nomeFila);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                String mensagem = new String(delivery.getBody(), StandardCharsets.UTF_8);
    
                var objectMapper = new ObjectMapper();
                var pedidoDTO = objectMapper.readValue(mensagem, PedidoDTO.class);
                var ordemCompra = pedidoAdapter.toDomain(pedidoDTO);
    
                consumidorMensagem.accept(ordemCompra);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
            catch (UnrecognizedPropertyException ex) {
                log.error("Erro ao converter mensagem da fila " + nomeFila, ex);
            }
        };

        channel.basicConsume(nomeFila, true, deliverCallback, consumerTag -> {});
    }

}
