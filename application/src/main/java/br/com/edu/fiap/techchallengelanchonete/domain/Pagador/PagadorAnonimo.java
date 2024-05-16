package br.com.edu.fiap.techchallengelanchonete.domain.Pagador;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PagadorAnonimo extends Pagador {

    public PagadorAnonimo() {
        this.setEmail(new Email(Email.EMAIL_PAGAMENTO));
    }

}
