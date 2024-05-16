package br.com.edu.fiap.techchallengelanchonete.configuration;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.IOrdemCompraProcessadaPublisher;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IPagamentoPersistence;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;
import br.com.edu.fiap.techchallengelanchonete.usecase.PagamentoUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBean {
    @Bean
    public PagamentoUseCase pagamentoUseCase(IPagamentoPersistence pagamentoAdapterJPA, 
        IGatewayPagamentoRegistrador gatewayPagamentoRegistrador, 
        IOrdemCompraProcessadaPublisher ordemCompraProcessadaPublisher) {
        return new PagamentoUseCase(pagamentoAdapterJPA, gatewayPagamentoRegistrador, ordemCompraProcessadaPublisher);
    }
}
