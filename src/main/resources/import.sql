DROP TABLE IF EXISTS cavallo;

CREATE TABLE cavallo (
  id INT PRIMARY KEY,
  nome VARCHAR(255),
  razza VARCHAR(255)
);

INSERT INTO cavallo (id, nome, razza) VALUES
(1, 'Zio Frac', 'Sardo'),
(2, 'Tale e Quale', 'Arabo'),
(3, 'Ares Elce', 'Mezziobusto'),
(4, 'Viso d''Angelo', 'Sella Italiano'),
(5, 'Zenis', 'Puledro'),
(6, 'Comancio', 'Lipizzano'),
(7, 'Arestetulesu', 'Anglo-Arabo'),
(8, 'Diosu De Campeda', 'Sardo'),
(9, 'Diodoro', 'Mezziobusto'),
(10, 'Dorotea Dimmonia', 'Puledro');
