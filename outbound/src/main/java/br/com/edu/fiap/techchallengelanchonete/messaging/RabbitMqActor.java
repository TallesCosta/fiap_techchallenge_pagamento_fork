package br.com.edu.fiap.techchallengelanchonete.messaging;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public abstract class RabbitMqActor {
    
    protected Channel channel;

    protected RabbitMqActor(RabbitMqConnFactory rabbitMqConnFactory) throws IOException {
        var connection = rabbitMqConnFactory.getConnection();
        if (channel == null || !channel.isOpen()) {
            channel = connection.createChannel();
        }
    }

    protected void subscribe(String queue) throws IOException {
        channel.queueDeclare(queue, false, false, false, null);
    }

}
