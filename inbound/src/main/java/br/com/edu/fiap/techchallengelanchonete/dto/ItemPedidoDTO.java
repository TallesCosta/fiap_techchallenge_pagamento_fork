package br.com.edu.fiap.techchallengelanchonete.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {

    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal precoProduto;
    private String categoriaProduto;
    private Integer quantidadeItem;

}
