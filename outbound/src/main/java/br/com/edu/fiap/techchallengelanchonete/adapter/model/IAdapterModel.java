package br.com.edu.fiap.techchallengelanchonete.adapter.model;

public interface IAdapterModel<Domain, Model> {
    Domain toDomain(Model model);
    Model toModel(Domain domain);
}
