-- Populando Habilidades iniciais para atuação na Assistência Social
-- Usando MERGE INTO para ser idempotente (não falha se já existir)
MERGE INTO hability (name, description) KEY(name) VALUES ('Manicure', 'Serviços de manicure e pedicure');
MERGE INTO hability (name, description) KEY(name) VALUES ('Vendas', 'Experiência com vendas em comércio local ou autônomo');
MERGE INTO hability (name, description) KEY(name) VALUES ('Atendimento', 'Atendimento ao público, recepção e afins');
MERGE INTO hability (name, description) KEY(name) VALUES ('Confeitaria', 'Produção de doces, bolos, salgados e culinária em geral');
MERGE INTO hability (name, description) KEY(name) VALUES ('Costura', 'Serviços de corte e costura, reparos de roupas');
MERGE INTO hability (name, description) KEY(name) VALUES ('Limpeza', 'Serviços de limpeza residencial ou comercial');
