package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Codigo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataCriacao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Pagamento extends DomainObject {
    private StatusPagamento status;
    private DataCriacao data;
    private DataExpiracao dataExpiracaoPagamento;
    private PagamentoCopiaCola pixCopiaECola;
    private PagamentoQRCode pixQRCode64;
    private Codigo codigoPedido;

    public Pagamento() {
        this.status = StatusPagamento.AGUARDANDO;
        this.data = new DataCriacao();
        this.dataExpiracaoPagamento = new DataExpiracao();
        this.pixCopiaECola = new PagamentoCopiaCola();
        this.pixQRCode64 = new PagamentoQRCode();
        this.codigoPedido = new Codigo();
    }
}