package br.com.edu.fiap.techchallengelanchonete.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class RabbitMqConnFactory {

    @Value( "${messaging.host}" )
    private String host;

    @Value( "${messaging.username}" )
    private String username;

    @Value( "${messaging.password}" )
    private String password;

    private Connection connection;

    public Connection getConnection() {
        if (connection == null || !connection.isOpen()) {
            try {
                var factory = new ConnectionFactory();
                factory.setHost(host);
                factory.setUsername(username);
                factory.setPassword(password);
                connection = factory.newConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
