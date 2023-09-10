docker run --name meu-postgres \
  -e POSTGRES_DB=test \
  -e POSTGRES_PASSWORD=mysecretpassword \
  -p 5432:5432 \
  -d postgres:latest
  


-- public.contato definition

-- Drop table

-- DROP TABLE public.contato;

CREATE TABLE public.contato (
	id serial4 NOT NULL,
	nome varchar NULL,
	email varchar NULL,
	telefone varchar NULL,
	estado_civil varchar NULL
);