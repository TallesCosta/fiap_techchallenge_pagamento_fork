package br.com.edu.fiap.techchallengelanchonete.adapter;

public interface IAdapter<Domain, DTO> {
    Domain toDomain(DTO dto);
    DTO toDTO(Domain domain);
}
