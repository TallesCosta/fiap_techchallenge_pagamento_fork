package br.com.edu.fiap.techchallengelanchonete.consumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;

import br.com.edu.fiap.techchallengelanchonete.adapter.PedidoAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.OrdemCompra;
import br.com.edu.fiap.techchallengelanchonete.dto.PedidoDTO;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoCriadoConsumer;
import br.com.edu.fiap.techchallengelanchonete.messaging.RabbitMqActor;
import br.com.edu.fiap.techchallengelanchonete.messaging.RabbitMqConnFactory;

@Component
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
            String mensagem = new String(delivery.getBody(), StandardCharsets.UTF_8);

            var objectMapper = new ObjectMapper();
            var pedidoDTO = objectMapper.readValue(mensagem, PedidoDTO.class);
            var ordemCompra = pedidoAdapter.toDomain(pedidoDTO);

            consumidorMensagem.accept(ordemCompra);
            System.out.println(" [x] Received: " + mensagem);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicConsume(nomeFila, true, deliverCallback, consumerTag -> {});
    }

}
