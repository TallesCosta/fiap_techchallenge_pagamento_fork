package br.com.edu.fiap.techchallengelanchonete.adapter.dto;

public interface IAdapterDTO<Domain, DTO> {
    Domain toDomain(DTO model);
    DTO toDTO(Domain domain);
}
