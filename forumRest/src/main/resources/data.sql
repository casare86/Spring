INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$kcVH3Uy86nJgQtYqAFffZORT9wbNMuNtqytcUZQRX51dx6IfSFEd.'); --truck123
INSERT INTO USUARIO(nome, email, senha) VALUES('Tester', 'teste@email.com', '$2a$10$P4bD3JQOthGpfIFPzrtjMO93AL9V2wuCGuYv51lP2.KjQsgwVN8fW');

INSERT INTO CURSO(nome, categoria) VALUES('Spring', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2021-10-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '20121-10-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2021-10-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);