package br.com.edu.fiap.techchallengelanchonete.adapter.dto;

import org.springframework.stereotype.Component;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.dto.PagamentoDTO;

@Component
public class PagamentoAdapterDTO implements IAdapterDTO<Pagamento, PagamentoDTO> {
    
    @Override
    public Pagamento toDomain(PagamentoDTO pagamentoDTO) {
        return Pagamento.builder()
            .codigoPedido(new Codigo(pagamentoDTO.getCodigoPedido()))
            .status(StatusPagamento.valueOf(pagamentoDTO.getStatusPagamento()))
            .build();
    }

    @Override
    public PagamentoDTO toDTO(Pagamento pagamento) {
        return PagamentoDTO.builder()
            .codigoPedido(pagamento.getCodigoPedido().getValor())
            .statusPagamento(pagamento.getStatus().toString())
            .build();
    }

}
