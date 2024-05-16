package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.mercadopago;

import br.com.edu.fiap.techchallengelanchonete.domain.OrdemCompra;
import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.resources.payment.Payment;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO: Descomentar após definições claras dos profiles x contextos de execução da app.
//@Profile("prd")
@Component
public class GatewayMercadoPagoRegistrador implements IGatewayPagamentoRegistrador {
    @Override
    public Pagamento registroPagamento(OrdemCompra ordemCompra) {
        try {
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

            var urlCallback = new StringBuilder()
                .append(ServletUriComponentsBuilder.fromCurrentRequestUri())
                .append("/gatewaypagamento/pedido/")
                .append(ordemCompra.getPedido().getCodigo().getValor())
                .toString();

            var payer = PaymentPayerRequest.builder()
                .email(ordemCompra.getPagador().getEmail().getValor())
                .firstName(ordemCompra.getPagador().getPrimeiroNome())
                .lastName(ordemCompra.getPagador().getSobrenomes())
                .identification(IdentificationRequest.builder()
                    .type("CPF")
                    .number(ordemCompra.getPagador().getCpf().getValor())
                    .build())
                .build();

            var dataExpiracao = DataExpiracao.ExpiracaoPadraoPagamento().getData();
            var paymentCreateRequest =
                PaymentCreateRequest.builder()
                    .transactionAmount(ordemCompra.getValorTotal().getValor())
                    .description(ordemCompra.getPedido().getProdutos().toString())
                    .paymentMethodId("pix")
                    .notificationUrl(urlCallback)
                    .externalReference(ordemCompra.getPedido().getCodigo().getValor())
                    .dateOfExpiration(dataExpiracao.toInstant().atOffset(OffsetDateTime.now().getOffset()))
                    .payer(payer)
                    .build();

            var client = new PaymentClient();
            Payment payment = client.create(paymentCreateRequest, requestOptions);

            return Pagamento.builder()
                .codigoPedido(ordemCompra.getPedido().getCodigo())
                .dataExpiracaoPagamento(new DataExpiracao(dataExpiracao))
                .pixCopiaECola(new PagamentoCopiaCola(payment.getPointOfInteraction().getTransactionData().getQrCode()))
                .pixQRCode64(new PagamentoQRCode(payment.getPointOfInteraction().getTransactionData().getQrCodeBase64()))
                .build();
        } catch (Exception ex) {
            throw new ApplicationException("Erro ao tentar criar pagamento no Mercado Pago");
        }
    }
}
