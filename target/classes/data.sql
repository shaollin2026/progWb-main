-- LIVROS
MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('Dom Casmurro', 'Machado de Assis', 0.0, 'Literatura', 1899, 'https://upload.wikimedia.org/wikipedia/commons/0/05/DomCasmurroMachadodeAssis.jpg', 'https://livraria-camara-leg.usrfiles.com/ugd/5ca0e9_77426ca451ec4f60b14af67f925f038e.pdf');

MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('A Divina Comedia: inferno', 'Dante Alighieri', 0.0, 'Classicos', 1320, 'https://m.media-amazon.com/images/I/81BUzGHyPlL._UF1000,1000_QL80_.jpg', 'https://www.ebooksbrasil.org/adobeebook/inferno.pdf');

MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('Iracema', 'Jose de Alencar', 0.0, 'Literatura', 1865, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfzXIQBIodpDck61U3BC1sZjRtlpy3yD6SUbKLtqnjVQ&s=10', 'https://domainpublic.wordpress.com/wp-content/uploads/2022/01/iracema.pdf');

MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('Utopia', 'Thomas More', 29.90, 'Filosofia', 1516, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuACnvZgZkvEpjAk1wNjcWvhchtYvWrZkX66WEYBvNiA&s=10', 'https://www.ebooksbrasil.org/adobeebook/utopia.pdf');

MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('Crime e Castigo', 'Fiodor Dostoievski', 35.00, 'Classicos', 1866, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHti_7aQELt1pUbO9mAIxGXQkkrCKjeoAhTeCjKpKnDw&s=10', 'https://archivepublicdomain.com/files/2025/02/Fiodor-Dostoievski-1.pdf');

MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('Alice no Pais das Maravilhas', 'Lewis Carroll', 25.50, 'Fantasia', 1865, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxy8ZHSydtWe9WBXM4ZqdoRb-xcgmZ0xZRw0-fVzXyOQ&s=10', 'https://www.ebooksbrasil.org/adobeebook/alicep.pdf');

MERGE INTO livros (titulo, autor, preco, categoria, ano_publicacao, capa_url, download_url) 
KEY(titulo) VALUES ('A Arte da Guerra', 'Sun Tzu', 19.90, 'Estrategia', -500, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRL8gCf5zabZpv4rmadWJ1tW8VspzMrE3tk3MjOKj57nQ&s=10', 'https://archivepublicdomain.com/files/2026/05/arte-da-guerra.pdf');

-- USUARIOS
MERGE INTO usuarios (nome, email, senha, role) 
KEY(email) VALUES ('Administrador', 'admin@admin.com', '123456', 'ADMIN');

MERGE INTO usuarios (nome, email, senha, role) 
KEY(email) VALUES ('Cliente Comum', 'cliente@teste.com', '123456', 'CLIENTE');