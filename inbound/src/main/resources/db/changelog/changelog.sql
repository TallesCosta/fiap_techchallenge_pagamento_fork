--liquibase formatted sql

-- changeset talles.costa:1
CREATE TABLE public.pagamentos (
    id bigserial NOT NULL,
    data_criacao timestamp(6) NOT NULL,
	data_atualizacao timestamp(6) NOT NULL,
    codigo_pedido varchar(255) NOT NULL,
    status_pagamento varchar(255) NOT NULL,
	data_expiracao_pagamento timestamp(6) NULL,
	pixqrcode64 varchar(5120) NULL,
	pix_copiaecola varchar(255) NULL,
	CONSTRAINT pagamentos_pkey PRIMARY KEY (id)
);
-- rollback DROP TABLE public.pagamentos;

