package br.com.edu.fiap.techchallengelanchonete.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;

import br.com.edu.fiap.techchallengelanchonete.dto.ItemPedidoDTO;
import br.com.edu.fiap.techchallengelanchonete.dto.PedidoDTO;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPedidoCriadoConsumer;
import br.com.edu.fiap.techchallengelanchonete.usecase.PagamentoUseCase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

@Configuration
public class StartupConfiguration {

    @Value( "${mercadopago.token}" )
    private String TOKEN_MERCADO_PAGO;

    public StartupConfiguration(IPedidoCriadoConsumer pedidoCriadoConsumer, PagamentoUseCase pagamentoUseCase) throws IOException {
        pedidoCriadoConsumer.consome("PEDIDOS_CRIADOS", pagamentoUseCase::registraPagamento);
    }

    @PostConstruct
    public void init() throws IOException {
        MercadoPagoConfig.setAccessToken(TOKEN_MERCADO_PAGO);
    }
}
