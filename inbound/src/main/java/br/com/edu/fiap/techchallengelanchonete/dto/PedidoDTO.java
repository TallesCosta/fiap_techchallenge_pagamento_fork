package br.com.edu.fiap.techchallengelanchonete.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoDTO {
    
    private List<ItemPedidoDTO> itens;
    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private String codigo;
    private Date data;
    private BigDecimal valorTotal;

}
