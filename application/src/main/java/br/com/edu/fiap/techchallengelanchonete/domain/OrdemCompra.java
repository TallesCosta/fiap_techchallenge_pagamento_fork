package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.Pagador.Pagador;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrdemCompra {
    
    private Pedido pedido;
    private Pagador pagador;
    private Valor valorTotal;

}
