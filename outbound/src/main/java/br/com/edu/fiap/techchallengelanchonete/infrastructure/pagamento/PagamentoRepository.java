package br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long> {
    Optional<PagamentoModel> findByCodigoPedido(String codigo);
}
