package br.com.edu.fiap.techchallengelanchonete.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagamentoDTO  {
    
    private String codigoPedido;
    private String statusPagamento;

}
