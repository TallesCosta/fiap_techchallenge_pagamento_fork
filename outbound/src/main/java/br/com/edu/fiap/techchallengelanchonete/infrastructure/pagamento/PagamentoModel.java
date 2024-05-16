package br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.DomainObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@Entity
@Table(name = "pagamentos")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoModel extends DomainObject {
    @Column(name = "codigo_pedido")
    private String codigoPedido;
    @Column(name = "status_pagamento")
    private String statusPagamento;
    @Column(name = "pix_copia_e_cola")
    private String pixCopiaECola;
    @Column(name = "pix_qrcode_base64", length = 5120)
    private String pixQRCode64;
    @Column(name = "data_expiracao_pagamento")
    private Date dataExpiracaoPagamento;
}
