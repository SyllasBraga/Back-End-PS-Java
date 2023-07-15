CREATE TABLE conta
(
    id_conta IDENTITY NOT NULL PRIMARY KEY,
    nome_responsavel VARCHAR(50) NOT NULL
);


CREATE TABLE transferencia
(
    id IDENTITY NOT NULL PRIMARY KEY,
    data_transferencia TIMESTAMP WITH TIME ZONE NOT NULL,
    valor NUMERIC (20,2) NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    nome_operador_transacao VARCHAR (50),
    conta_id INT NOT NULL,

        CONSTRAINT FK_CONTA
        FOREIGN KEY (conta_id)
        REFERENCES conta(id_conta)
);

INSERT INTO conta (id_conta, nome_responsavel) VALUES (1,'Fulano');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (2,'Sicrano');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (3,'José');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (4,'Maria');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (5,'João');

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (1,'2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (2,'2019-02-03 09:53:27+03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (3,'2019-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (4,'2019-08-07 08:12:45+03',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (5,'2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA', 'Beltrano',1);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (6, '2023-07-15 14:30:00+03', 500.00, 'DEPOSITO', null, 1);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (7, '2023-07-15 15:45:00+03', 1000.00, 'SAQUE', null, 2);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (8, '2023-07-15 16:20:00+03', 1500.00, 'TRANSFERENCIA', 'Maria', 3);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (9, '2023-07-15 17:10:00+03', 2000.00, 'DEPOSITO', null, 4);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (10, '2023-07-15 18:00:00+03', 3000.00, 'SAQUE', null, 5);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (11, '2023-07-15 19:30:00+03', 2500.00, 'TRANSFERENCIA', 'João', 1);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (12, '2023-07-15 20:15:00+03', 1800.00, 'DEPOSITO', null, 2);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (13, '2023-07-15 21:00:00+03', 900.00, 'SAQUE', null, 3);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (14, '2023-07-15 22:20:00+03', 700.00, 'TRANSFERENCIA', 'Ana', 4);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (15, '2023-07-15 23:40:00+03', 1200.00, 'DEPOSITO', null, 5);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (16, '2023-07-16 00:50:00+03', 600.00, 'SAQUE', null, 1);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (17, '2023-07-16 01:15:00+03', 450.00, 'TRANSFERENCIA', 'Pedro', 2);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (18, '2023-07-16 02:00:00+03', 800.00, 'DEPOSITO', null, 3);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (19, '2023-07-16 03:30:00+03', 2000.00, 'SAQUE', null, 4);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (20, '2023-07-16 04:20:00+03', 1500.00, 'TRANSFERENCIA', 'Mariana', 5);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (21, '2023-07-16 05:10:00+03', 1000.00, 'DEPOSITO', null, 1);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (22, '2023-07-16 06:25:00+03', 300.00, 'SAQUE', null, 2);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (23, '2023-07-16 07:15:00+03', 600.00, 'TRANSFERENCIA', 'Rafael', 3);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (24, '2023-07-16 08:45:00+03', 1200.00, 'DEPOSITO', null, 4);
INSERT INTO transferencia (id, data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (25, '2023-07-16 09:30:00+03', 700.00, 'SAQUE', null, 5);
