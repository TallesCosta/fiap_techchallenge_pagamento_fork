package br.com.edu.fiap.techchallengelanchonete;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento.PagamentoModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento.PagamentoRepository;
import jakarta.transaction.Transactional;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = TechChallengeLanchoneteApplication.class)
// @Transactional
// @AutoConfigureMockMvc
class GatewayMercadoPagoControllerTest {

    // @Autowired
    // private MockMvc mvc;

    // @Autowired
    // private PagamentoRepository repository;

    // @Test
    // void confirmacaoPagamento() throws Exception {
    //     var codigoPedido = UUID.randomUUID().toString();

    //     var pagamentoModel = PagamentoModel.builder()
    //         .codigoPedido(codigoPedido)
    //         .statusPagamento(StatusPagamento.AGUARDANDO.toString())
    //         .dataCriacao(new Date())
    //         .build();
    //     repository.save(pagamentoModel);

    //     mvc.perform(post("/gatewaypagamento/pedido/" + codigoPedido)
    //         .header("status-mock", StatusPagamento.APROVADO.toString()) 
    //         .header("Referer", "https://mercadopago.com.ar")
    //         .header("User-Agent", "MercadoPago WebHook v1.0 payment")
    //         .content("{}")
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk());

    //     assertTrue(repository.findByCodigoPedido(codigoPedido).isPresent());
    // }

}
