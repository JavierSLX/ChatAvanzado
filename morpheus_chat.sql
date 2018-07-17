USE morpheus_chat;

DROP TABLE IF EXISTS login;
CREATE TABLE acceso
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    pass VARCHAR(50) NOT NULL
);

INSERT INTO acceso(usuario, pass) VALUES('javier', '1234');
INSERT INTO acceso(usuario, pass) VALUES('juan', '123');