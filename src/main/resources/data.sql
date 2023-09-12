INSERT INTO usuarios (nome, creation_date, birthday_date, identificacao, telefone, perfil)
VALUES
    ('Maria da Silva', '1990-08-15', '1985-03-20', '123.456.789-09', '123-4567', 'CLIENTE'),
    ('José Santos', '1985-02-10', '1970-11-05', '987.654.321-00', '987-6543', 'ADVOGADO'),
    ('Ana Oliveira', '2000-04-25', '1995-07-12', '234.567.890-12', '345-6789', 'CLIENTE'),
    ('Carlos Pereira', '1993-09-30', '1988-12-18', '345.678.901-23', '456-7890', 'ADVOGADO'),
    ('Lucia Rocha', '1982-06-15', '1975-03-02', '456.789.012-34', '567-8901', 'CLIENTE'),
    ('Ricardo Fernandes', '1999-11-20', '1994-09-22', '567.890.123-45', '678-9012', 'ADVOGADO'),
    ('Amanda Sousa', '1992-03-01', '1987-08-10', '678.901.234-56', '789-0123', 'CLIENTE'),
    ('Fernanda Lima', '1988-07-10', '1983-05-15', '789.012.345-67', '890-1234', 'ADVOGADO'),
    ('Pedro Almeida', '1996-12-12', '1991-01-28', '890.123.456-78', '901-2345', 'CLIENTE'),
    ('Laura Barbosa', '1987-04-30', '1982-07-07', '901.234.567-89', '012-3456', 'ADVOGADO'),
    ('Marcos Costa', '1994-10-08', '1989-11-25', '012.345.678-90', '123-4567', 'CLIENTE'),
    ('Sandra Carvalho', '1980-05-18', '1975-03-14', '123.456.789-01', '234-5678', 'ADVOGADO'),
    ('Roberto Silva', '1989-06-05', '1984-09-07', '987.654.321-02', '345-6789', 'CLIENTE'),
    ('Juliana Santos', '1997-03-22', '1992-06-30', '234.567.890-23', '456-7890', 'ADVOGADO'),
    ('Gustavo Oliveira', '1991-11-15', '1986-02-18', '345.678.901-34', '567-8901', 'CLIENTE');

-- Insert sample Endereco records associated with Usuarios
INSERT INTO enderecos (rua, numero, complemento, cep, usuario_id)
VALUES
    ('Rua A', '123', 'Apto 101', '12345-678', 1),
    ('Rua B', '456', 'Casa 2', '98765-432', 2),
    ('Rua C', '789', 'Sala 3', '23456-789', 3),
    ('Rua D', '101', 'Apto 202', '34567-890', 4),
    ('Rua E', '202', 'Casa 4', '45678-901', 5),
    ('Rua F', '303', 'Sala 5', '56789-012', 6),
    ('Rua G', '404', 'Apto 303', '67890-123', 7),
    ('Rua H', '505', 'Casa 7', '78901-234', 8),
    ('Rua I', '606', 'Sala 8', '89012-345', 9),
    ('Rua J', '707', 'Apto 404', '90123-456', 10),
    ('Rua K', '808', 'Casa 10', '01234-567', 11),
    ('Rua L', '909', 'Sala 11', '12345-678', 12),
    ('Rua M', '1010', 'Apto 505', '23456-789', 13),
    ('Rua N', '1111', 'Casa 13', '34567-890', 14),
    ('Rua O', '1212', 'Sala 14', '45678-901', 15);

-- USUARIO

INSERT INTO users(login , password ,role, id) VALUES
    ('lucas', '123456','ADMIN', 1)