package br.com.edu.fiap.techchallengelanchonete.controller;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoConfirmacao;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoResolver;
import br.com.edu.fiap.techchallengelanchonete.usecase.PagamentoUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/gatewaypagamento")
public class GatewayMercadoPagoController implements IGatewayPagamentoConfirmacao {
    private PagamentoUseCase pagamentoUseCase;
    private List<IGatewayPagamentoResolver> gatewayPagamentoResolvers;

    public GatewayMercadoPagoController(PagamentoUseCase pagamentoUseCase, List<IGatewayPagamentoResolver> gatewayPagamentoResolvers) {
        this.pagamentoUseCase = pagamentoUseCase;
        this.gatewayPagamentoResolvers = gatewayPagamentoResolvers;
    }

    @PostMapping("pedido/{codigoPedido}")
    public ResponseEntity<Object> confirmacaoPagamento(@PathVariable String codigoPedido,
                                               @RequestHeader Map<String, String> headers,
                                               @RequestParam Map<String, String> params,
                                               @RequestBody Map<String, Object> body) {

        Optional<StatusPagamento> optionalStatusPagamento = Optional.empty();
        for (IGatewayPagamentoResolver gatewayPagamentoResolver: gatewayPagamentoResolvers) {
            if (gatewayPagamentoResolver.validaGatewayPagamentoCorrente(headers, params, body)) {
                optionalStatusPagamento = Optional.of(gatewayPagamentoResolver.interpretaStatusPagamento(headers, params, body));
            }
        }

        optionalStatusPagamento.ifPresent(statusPagamento -> this.confirmacaoPagamento(new Codigo(codigoPedido), statusPagamento));
        return ResponseEntity.ok().build();
    }

    @Override
    public Pagamento confirmacaoPagamento(Codigo codigoPedido, StatusPagamento statusPagamento) {
        return this.pagamentoUseCase.confirmacaoPagamento(codigoPedido, statusPagamento);
    }
}
