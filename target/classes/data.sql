-- Limpeza preventiva de dados
DELETE FROM livros;
DELETE FROM usuarios;

-- Carga inicial de livros (com colunas capa_url e download_url)
INSERT INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
VALUES ('Dom Casmurro', 'Machado de Assis', 0.0, 'Literatura', 1900, 'https://picsum.photos/id/24/280/400', 'https://livraria-camara-leg.usrfiles.com/ugd/5ca0e9_77426ca451ec4f60b14af67f925f038e.pdf');

INSERT INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
VALUES ('100 Frases de Platão', 'Platão', 0.0, 'Filosofia', 2023, 'https://picsum.photos/id/48/280/400', 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf');

INSERT INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
VALUES ('A Arte da Guerra', 'Sun Tzu', 39.90, 'Estratégia', 2025, 'https://picsum.photos/id/119/280/400', NULL);

INSERT INTO livros (titulo, autor, ano_publicacao, preco, categoria, capa_url, download_url) VALUES 
('Introdução à Computação', 'Professor Facom', 2024, 0.0, 'Exatas', 'https://images.unsplash.com/photo-1517694712202-14dd9538aa97', 'https://www.orimi.com/pdf-test.pdf'),
('Cálculo Diferencial e Integral', 'James Stewart', 2020, 89.90, 'Exatas', 'https://images.unsplash.com/photo-1635070041078-e363dbe005cb', 'https://www.orimi.com/pdf-test.pdf'),
('Estruturas de Dados Avançadas', 'Thomas Cormen', 2022, 120.00, 'Exatas', 'https://images.unsplash.com/photo-1515879218367-8466d910aaa4', 'https://www.orimi.com/pdf-test.pdf'),
('Anatomia Humana Básica', 'Sobotta', 2019, 0.0, 'Saúde', 'https://images.unsplash.com/photo-1530026405186-ed1ea0ac7a63', 'https://www.orimi.com/pdf-test.pdf'),
('Tratado de Fisiologia Médica', 'Guyton', 2021, 250.00, 'Saúde', 'https://images.unsplash.com/photo-1576091160550-2173dba999ef', 'https://www.orimi.com/pdf-test.pdf'),
('História do Pensamento Ocidental', 'Bertrand Russell', 2015, 45.00, 'Humanas', 'https://images.unsplash.com/photo-1524995997946-a1c2e315a42f', 'https://www.orimi.com/pdf-test.pdf'),
('Sociologia Líquida', 'Zygmunt Bauman', 2018, 0.0, 'Humanas', 'https://images.unsplash.com/photo-1506880018603-83d5b814b5a6', 'https://www.orimi.com/pdf-test.pdf'),
('A Arte de Pintar', 'Leonardo da Vinci', 2012, 35.50, 'Artes', 'https://images.unsplash.com/photo-1513364776144-60967b0f800f', 'https://www.orimi.com/pdf-test.pdf'),
('História da Música Clássica', 'Otto Maria Carpeaux', 2014, 0.0, 'Artes', 'https://images.unsplash.com/photo-1507838153414-b4b713384a76', 'https://www.orimi.com/pdf-test.pdf');

-- Carga inicial de usuários com privilégios definidos
INSERT INTO usuarios (nome, email, senha, role) 
VALUES ('Administrador', 'admin@admin.com', '123456', 'ADMIN');

INSERT INTO usuarios (nome, email, senha, role) 
VALUES ('Cliente Comum', 'cliente@teste.com', '123456', 'CLIENTE');