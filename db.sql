DROP TABLE IF EXISTS Comentario;
DROP TABLE IF EXISTS Pelicula_Actor;
DROP TABLE IF EXISTS Serie_Actor;
DROP TABLE IF EXISTS Puntuacion;
DROP TABLE IF EXISTS Capitulo;
DROP TABLE IF EXISTS Pelicula;
DROP TABLE IF EXISTS Serie;
DROP TABLE IF EXISTS Actor;
DROP TABLE IF EXISTS Director;
DROP TABLE IF EXISTS Usuario;

CREATE TABLE usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    rol ENUM('ADMIN', 'CLIENTE') NOT NULL,
    password VARCHAR(255) NOT NULL,
    premium BOOLEAN NOT NULL
);

CREATE TABLE director (
    idDirector INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE actor (
    idActor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE pelicula (
    idPelicula INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    duracion INT NOT NULL,
    premium BOOLEAN NOT NULL,
    puntuacion DOUBLE NOT NULL,
    idDirector INT,
    imgURL VARCHAR(300),
    FOREIGN KEY (idDirector) REFERENCES director(idDirector)
);

CREATE TABLE serie (
    idSerie INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    premium BOOLEAN NOT NULL,
    puntuacion DOUBLE NOT NULL,
    idDirector INT,
    imgURL VARCHAR(300),    
    FOREIGN KEY (idDirector) REFERENCES director(idDirector)
);

CREATE TABLE capitulo (
    idCapitulo INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    duracion INT NOT NULL,
    idSerie INT,
    FOREIGN KEY (idSerie) REFERENCES serie(idSerie)
);

CREATE TABLE puntuacion (
    idPuntuacion INT PRIMARY KEY AUTO_INCREMENT,
    idUsuario INT,
    idPelicula INT NULL,
    idSerie INT NULL,
    puntuacion DOUBLE NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario),
    FOREIGN KEY (idPelicula) REFERENCES pelicula(idPelicula),
    FOREIGN KEY (idSerie) REFERENCES serie(idSerie)
);

CREATE TABLE pelicula_actor (
    idPelicula INT,
    idActor INT,
    PRIMARY KEY (idPelicula, idActor),
    FOREIGN KEY (idPelicula) REFERENCES pelicula(idPelicula),
    FOREIGN KEY (idActor) REFERENCES actor(idActor)
);

CREATE TABLE serie_actor (
    idSerie INT,
    idActor INT,
    PRIMARY KEY (idSerie, idActor),
    FOREIGN KEY (idSerie) REFERENCES serie(idSerie),
    FOREIGN KEY (idActor) REFERENCES actor(idActor)
);

CREATE TABLE comentario (
    idComentario INT PRIMARY KEY AUTO_INCREMENT,
    comentario TEXT NOT NULL,
    idPelicula INT NULL,
    idSerie INT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idPelicula) REFERENCES pelicula(idPelicula),
    FOREIGN KEY (idSerie) REFERENCES serie(idSerie),
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

INSERT INTO usuario (nombre, email, password, premium, rol) VALUES
('Eduardo', 'eduardo@fempa.es', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'ADMIN'),
('ACD', 'acd@admin.es', '$2a$12$9vPH6elgk7rKp8UuFhYo5O9Wxx4jg72DjpWf6zkgruYp/vuUhX36S', true, 'ADMIN'),
('ACD', 'acd@cliente.es', '$2a$12$9vPH6elgk7rKp8UuFhYo5O9Wxx4jg72DjpWf6zkgruYp/vuUhX36S', true, 'CLIENTE'),
('Ana García', 'ana.garcia@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Carlos Martínez', 'carlos.martinez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Laura Fernández', 'laura.fernandez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Pedro López', 'pedro.lopez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('María Sánchez', 'maria.sanchez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Javier Pérez', 'javier.perez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Sofía González', 'sofia.gonzalez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Daniel Rodríguez', 'daniel.rodriguez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Elena Gómez', 'elena.gomez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Pablo Hernández', 'pablo.hernandez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Lucía Díaz', 'lucia.diaz@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Adrián Moreno', 'adrian.moreno@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Marta Álvarez', 'marta.alvarez@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('David Romero', 'david.romero@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Carmen Torres', 'carmen.torres@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE'),
('Jorge Navarro', 'jorge.navarro@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', true, 'CLIENTE'),
('Natalia Molina', 'natalia.molina@example.com', '$2a$10$bm2iwDaNoZdPy8.6hCyiNeu2Ov0lKVsqWDdKP5caaVOiD3cgyDqj6', false, 'CLIENTE');

INSERT INTO director (nombre) VALUES
('Christopher Nolan'), ('Quentin Tarantino'), ('Martin Scorsese'), 
('Steven Spielberg'), ('James Cameron'), ('Ridley Scott'),
('Alfred Hitchcock'), ('Stanley Kubrick'), ('David Fincher'),
('Tim Burton'), ('Clint Eastwood'), ('Francis Ford Coppola'),
('Guillermo del Toro'), ('Peter Jackson'), ('Wes Anderson'),
('Damien Chazelle'), ('Bong Joon-ho'), ('Denis Villeneuve'),
('Alejandro G. Iñárritu'), ('Paolo Sorrentino'), ('Hayao Miyazaki'),
('Sofia Coppola'), ('Greta Gerwig'), ('Jordan Peele'),
('Spike Lee'), ('Joel Coen'), ('Ethan Coen'), ('Lana Wachowski'),
('Wong Kar-wai'), ('Park Chan-wook'), ('Yorgos Lanthimos'),
('Taika Waititi'), ('Ava DuVernay'), ('Ryan Coogler'),
('Céline Sciamma'), ('Emerald Fennell'), ('Chloé Zhao'),
('Baz Luhrmann'), ('Robert Zemeckis'), ('Sam Mendes');

INSERT INTO director (nombre) VALUES
('Vince Gilligan'), ('David Benioff'), ('D.B. Weiss'),
('Matt Duffer'), ('Ross Duffer'), ('Jon Favreau'),
('Greg Berlanti'), ('Phoebe Waller-Bridge'), ('Noah Hawley'),
('David Lynch'), ('Shonda Rhimes'), ('David Chase'),
('Sam Levinson'), ('Craig Mazin'), ('Jesse Armstrong'),
('Mike White'), ('Bruce Miller'), ('Eric Kripke'),
('Christopher Markus'), ('Stephen McFeely'), ('Damon Lindelof'),
('Mark Mylod'), ('Lesli Linka Glatter'), ('Michaël R. Roscam'),
('Megan Mostyn-Brown'), ('John Requa'), ('Glenn Ficarra'),
('Steve Blackman'), ('Jonathan Nolan'), ('Lisa Joy');

INSERT INTO actor (nombre) VALUES
('Leonardo DiCaprio'), ('Christian Bale'), ('Heath Ledger'),
('Robert De Niro'), ('Al Pacino'), ('Marlon Brando'),
('Tom Hanks'), ('Morgan Freeman'), ('Brad Pitt'),
('Edward Norton'), ('Scarlett Johansson'), ('Samuel L. Jackson'),
('John Travolta'), ('Uma Thurman'), ('Keanu Reeves'),
('Laurence Fishburne'), ('Carrie-Anne Moss'), ('Harrison Ford'),
('Mark Hamill'), ('Arnold Schwarzenegger'), ('Sigourney Weaver'),
('Kate Winslet'), ('Cate Blanchett'), ('Anthony Hopkins'),
('Joaquin Phoenix'), ('Russell Crowe'), ('Matt Damon'),
('Anne Hathaway'), ('Matthew McConaughey'), ('Jessica Chastain'),
('Daniel Day-Lewis'), ('Liam Neeson'), ('Ralph Fiennes'),
('Ben Kingsley'), ('Gary Oldman'), ('Tom Hardy'),
('Charlize Theron'), ('Natalie Portman'), ('Emma Stone'),
('Ryan Gosling'), ('Bryan Cranston'), ('Aaron Paul'),
('Anna Gunn'), ('Kit Harington'), ('Emilia Clarke'),
('Peter Dinklage'), ('Millie Bobby Brown'), ('Finn Wolfhard'),
('Winona Ryder'), ('David Harbour'), ('Pedro Pascal'),
('Gina Carano'), ('Jason Bateman'), ('Laura Linney'),
('Evan Rachel Wood'), ('Thandiwe Newton'), ('Jeffrey Wright'),
('Zendaya'), ('Hunter Schafer'), ('Brian Cox'),
('Jeremy Strong'), ('Sarah Snook'), ('Bob Odenkirk'),
('Rhea Seehorn'), ('Jason Sudeikis'), ('Juno Temple'),
('Brett Goldstein'), ('Henry Cavill'), ('Freya Allan'),
('Chris Evans'), ('Chris Hemsworth'), ('Mark Ruffalo'),
('Jeremy Renner'), ('Paul Rudd'), ('Brie Larson'), 
('Zoe Saldana'), ('Vin Diesel'), ('Dwayne Johnson'),
('Idris Elba'), ('Gal Gadot');

INSERT INTO pelicula (nombre, genero, duracion, premium, puntuacion, idDirector, imgURL) VALUES
('El Caballero Oscuro', 'Accion', 152, 1, ROUND(RAND()*4+6,1), 1, '/portadas/peliculas/1.jpg'),
('Origen', 'Ciencia Ficcion', 148, 1, ROUND(RAND()*4+6,1), 1, '/portadas/peliculas/2.jpg'),
('Interestelar', 'Ciencia Ficcion', 169, 1, ROUND(RAND()*4+6,1), 1, '/portadas/peliculas/3.jpg'),
('Pulp Fiction', 'Comedia', 154, 1, ROUND(RAND()*4+6,1), 2, '/portadas/peliculas/4.jpg'),
('Malditos Bastardos', 'Comedia', 153, 1, ROUND(RAND()*4+6,1), 2, '/portadas/peliculas/5.jpg'),
('El Irlandés', 'Accion', 209, 1, ROUND(RAND()*4+6,1), 3, '/portadas/peliculas/6.jpg'),
('Tiburón', 'Drama', 124, 0, ROUND(RAND()*4+6,1), 4, '/portadas/peliculas/7.jpg'),
('Parque Jurásico', 'Accion', 127, 0, ROUND(RAND()*4+6,1), 4, '/portadas/peliculas/8.jpg'),
('Titanic', 'Drama', 195, 0, ROUND(RAND()*4+6,1), 5, '/portadas/peliculas/9.jpg'),
('Avatar', 'Ciencia Ficcion', 162, 1, ROUND(RAND()*4+6,1), 5, '/portadas/peliculas/10.jpg'),
('Blade Runner', 'Ciencia Ficcion', 117, 1, ROUND(RAND()*4+6,1), 6, '/portadas/peliculas/11.jpg'),
('Alien', 'Thriller', 116, 1, ROUND(RAND()*4+6,1), 6, '/portadas/peliculas/12.jpg'),
('Psicosis', 'Thriller', 109, 0, ROUND(RAND()*4+6,1), 7, '/portadas/peliculas/13.jpg'),
('2001: Odisea del Espacio', 'Ciencia Ficcion', 149, 1, ROUND(RAND()*4+6,1), 8, '/portadas/peliculas/14.jpg'),
('El Club de la Lucha', 'Drama', 139, 1, ROUND(RAND()*4+6,1), 9, '/portadas/peliculas/15.jpg'),
('Seven', 'Thriller', 127, 1, ROUND(RAND()*4+6,1), 9, '/portadas/peliculas/16.jpg'),
('El Padrino', 'Thriller', 175, 0, ROUND(RAND()*4+6,1), 12, '/portadas/peliculas/17.jpg'),
('Apocalypse Now', 'Accion', 153, 1, ROUND(RAND()*4+6,1), 12, '/portadas/peliculas/18.jpg'),
('El Laberinto del Fauno', 'Accion', 118, 1, ROUND(RAND()*4+6,1), 13, '/portadas/peliculas/19.jpg'),
('El Señor de los Anillos: La Comunidad', 'Accion', 178, 1, ROUND(RAND()*4+6,1), 14, '/portadas/peliculas/20.jpg'),
('Grand Budapest Hotel', 'Comedia', 99, 0, ROUND(RAND()*4+6,1), 15, '/portadas/peliculas/21.jpg'),
('Whiplash', 'Drama', 106, 1, ROUND(RAND()*4+6,1), 16, '/portadas/peliculas/22.jpg'),
('Parásitos', 'Drama', 132, 1, ROUND(RAND()*4+6,1), 17, '/portadas/peliculas/23.jpg'),
('Dune', 'Ciencia Ficcion', 155, 1, ROUND(RAND()*4+6,1), 18, '/portadas/peliculas/24.jpg'),
('El Renacido', 'Accion', 156, 1, ROUND(RAND()*4+6,1), 19, '/portadas/peliculas/25.jpg'),
('La Gran Belleza', 'Drama', 141, 1, ROUND(RAND()*4+6,1), 20, '/portadas/peliculas/26.jpg'),
('El Viaje de Chihiro', 'Drama', 125, 0, ROUND(RAND()*4+6,1), 21, '/portadas/peliculas/27.jpg'),
('Lost in Translation', 'Drama', 102, 0, ROUND(RAND()*4+6,1), 22, '/portadas/peliculas/28.jpg'),
('Mujercitas', 'Drama', 135, 0, ROUND(RAND()*4+6,1), 23, '/portadas/peliculas/29.jpg'),
('Huye!', 'Thriller', 104, 1, ROUND(RAND()*4+6,1), 24, '/portadas/peliculas/30.jpg'),
('Haz lo Correcto', 'Drama', 120, 0, ROUND(RAND()*4+6,1), 25, '/portadas/peliculas/31.jpg'),
('Fargo', 'Thriller', 98, 0, ROUND(RAND()*4+6,1), 26, '/portadas/peliculas/32.jpg'),
('Matrix', 'Ciencia Ficcion', 136, 1, ROUND(RAND()*4+6,1), 27, '/portadas/peliculas/33.jpg'),
('Amélie', 'Comedia', 122, 0, ROUND(RAND()*4+6,1), 28, '/portadas/peliculas/34.jpg'),
('Oldboy', 'Thriller', 120, 1, ROUND(RAND()*4+6,1), 30, '/portadas/peliculas/35.jpg'),
('El Sacrificio del Ciervo Sagrado', 'Drama', 121, 1, ROUND(RAND()*4+6,1), 31, '/portadas/peliculas/36.jpg'),
('Jojo Rabbit', 'Comedia', 108, 0, ROUND(RAND()*4+6,1), 32, '/portadas/peliculas/37.jpg'),
('Pantera Negra', 'Comedia', 134, 1, ROUND(RAND()*4+6,1), 34, '/portadas/peliculas/38.jpg'),
('El Gran Gatsby', 'Comedia', 143, 1, ROUND(RAND()*4+6,1), 38, '/portadas/peliculas/39.jpg'),
('1917', 'Drama', 119, 1, ROUND(RAND()*4+6,1), 40, '/portadas/peliculas/40.jpg');

INSERT INTO serie (idSerie, nombre, genero, premium, puntuacion, idDirector, imgURL) VALUES
(1, 'Breaking Bad', 'Drama', 0, 7.1, 41, '/portadas/series/1.jpg'),
(2, 'Juego de Tronos', 'Drama', 0, 7.7, 42, '/portadas/series/2.jpg'),
(3, 'Stranger Things', 'Ciencia Ficcion', 0, 7.4, 44, '/portadas/series/3.jpg'),
(4, 'The Mandalorian', 'Ciencia Ficcion', 0, 7.8, 46, '/portadas/series/4.jpg'),
(5, 'Chernobyl', 'Drama', 0, 6.7, 54, '/portadas/series/5.jpg'),
(6, 'Westworld', 'Ciencia Ficcion', 0, 8.1, 70, '/portadas/series/6.jpg'),
(7, 'The Crown', 'Drama', 0, 6.4, 48, '/portadas/series/7.jpg'),
(8, 'The Witcher', 'Accion', 0, 9.9, 58, '/portadas/series/8.jpg'),
(9, 'Better Call Saul', 'Drama', 0, 8.2, 41, '/portadas/series/9.jpg'),
(10, 'True Detective', 'Thriller', 0, 9.3, 49, '/portadas/series/10.jpg'),
(11, 'House of the Dragon', 'Drama', 0, 7.7, 42, '/portadas/series/11.jpg'),
(12, 'The Last of Us', 'Thriller', 0, 8.7, 54, '/portadas/series/12.jpg'),
(13, 'Succession', 'Drama', 0, 6.3, 55, '/portadas/series/13.jpg'),
(14, 'The White Lotus', 'Drama', 0, 7.6, 56, '/portadas/series/14.jpg'),
(15, 'Ozark', 'Thriller', 0, 8.9, 19, '/portadas/series/15.jpg'),
(16, 'Peaky Blinders', 'Thriller', 0, 7.9, 20, '/portadas/series/16.jpg'),
(17, 'Black Mirror', 'Ciencia Ficcion', 0, 6.9, 62, '/portadas/series/17.jpg'),
(18, 'The Handmaid''s Tale', 'Drama', 0, 8.8, 22, '/portadas/series/18.jpg'),
(19, 'Fargo', 'Comedia', 0, 9.2, 23, '/portadas/series/19.jpg'),
(20, 'Mindhunter', 'Thriller', 0, 9.5, 24, '/portadas/series/20.jpg'),
(21, 'Narcos', 'Thriller', 0, 9.9, 25, '/portadas/series/21.jpg'),
(22, 'Dark', 'Ciencia Ficcion', 0, 6.9, 26, '/portadas/series/22.jpg'),
(23, 'The Boys', 'Accion', 0, 7.0, 58, '/portadas/series/23.jpg'),
(24, 'Euphoria', 'Drama', 0, 8.2, 28, '/portadas/series/24.jpg'),
(25, 'The Queen''s Gambit', 'Drama', 0, 6.1, 29, '/portadas/series/25.jpg'),
(26, 'Bridgerton', 'Drama', 0, 7.8, 30, '/portadas/series/26.jpg'),
(27, 'The Umbrella Academy', 'Ciencia Ficcion', 0, 6.7, 68, '/portadas/series/27.jpg'),
(28, 'Money Heist', 'Thriller', 0, 8.0, 40, '/portadas/series/28.jpg'),
(29, 'Squid Game', 'Thriller', 0, 6.0, 30, '/portadas/series/29.jpg'),
(30, 'The Office', 'Comedia', 0, 8.2, 34, '/portadas/series/30.jpg');

INSERT INTO pelicula_actor (idPelicula, idActor) VALUES
(1, 2), (1, 3),       -- El Caballero Oscuro: Christian Bale, Heath Ledger
(2, 1), (2, 35),      -- Origen: Leonardo DiCaprio, Tom Hardy
(3, 28), (3, 29),     -- Interestelar: Matthew McConaughey, Jessica Chastain
(4, 12), (4, 13),     -- Pulp Fiction: Samuel L. Jackson, John Travolta
(5, 14), (5, 9),      -- Malditos Bastardos: Uma Thurman, Brad Pitt
(6, 4), (6, 5),       -- El Irlandés: Robert De Niro, Al Pacino
(7, 22), (7, 23),     -- Tiburón: Kate Winslet, Cate Blanchett
(8, 15), (8, 16),     -- Parque Jurásico: Keanu Reeves, Laurence Fishburne
(9, 1), (9, 22),      -- Titanic: Leonardo DiCaprio, Kate Winslet
(10, 17), (10, 18),   -- Avatar: Harrison Ford, Mark Hamill
(11, 19), (11, 20),   -- Blade Runner: Arnold Schwarzenegger, Sigourney Weaver
(12, 21), (12, 24),   -- Alien: Carrie-Anne Moss, Anthony Hopkins
(13, 25), (13, 26),   -- Psicosis: Joaquin Phoenix, Russell Crowe
(14, 27), (14, 28),   -- 2001: Odisea del Espacio: Matt Damon, Anne Hathaway
(15, 9), (15, 35),    -- El Club de la Lucha: Brad Pitt, Tom Hardy
(16, 8), (16, 34),    -- Seven: Morgan Freeman, Gary Oldman
(17, 5), (17, 31),    -- El Padrino: Al Pacino, Daniel Day-Lewis
(18, 1), (18, 33),    -- Apocalypse Now: Leonardo DiCaprio, Ben Kingsley
(19, 36), (19, 37),   -- El Laberinto del Fauno: Charlize Theron, Natalie Portman
(20, 38), (20, 39),   -- El Señor de los Anillos: Emma Stone, Ryan Gosling
(21, 40), (21, 41),   -- Grand Budapest Hotel: Bryan Cranston, Aaron Paul
(22, 42), (22, 43),   -- Whiplash: Anna Gunn, Kit Harington
(23, 44), (23, 45),   -- Parásitos: Emilia Clarke, Peter Dinklage
(24, 46), (24, 47),   -- Dune: Millie Bobby Brown, Finn Wolfhard
(25, 48), (25, 49),   -- El Renacido: Winona Ryder, David Harbour
(26, 50), (26, 51),   -- La Gran Belleza: Pedro Pascal, Gina Carano
(27, 52), (27, 53),   -- El Viaje de Chihiro: Jason Bateman, Laura Linney
(28, 54), (28, 55),   -- Lost in Translation: Evan Rachel Wood, Thandiwe Newton
(29, 56), (29, 57),   -- Mujercitas: Jeffrey Wright, Zendaya
(30, 58), (30, 59),   -- Huye!: Hunter Schafer, Brian Cox
(31, 60), (31, 61),   -- Haz lo Correcto: Jeremy Strong, Sarah Snook
(32, 62), (32, 63),   -- Fargo: Bob Odenkirk, Rhea Seehorn
(33, 64), (33, 65),   -- Matrix: Jason Sudeikis, Juno Temple
(34, 66), (34, 67),   -- Amélie: Brett Goldstein, Henry Cavill
(35, 68), (35, 69),   -- Oldboy: Freya Allan, Christian Bale
(36, 24), (36, 35),   -- El Sacrificio del Ciervo Sagrado: Anthony Hopkins, Tom Hardy
(37, 9), (37, 14),    -- Jojo Rabbit: Brad Pitt, Uma Thurman
(38, 17), (38, 18),   -- Pantera Negra: Harrison Ford, Mark Hamill
(39, 1), (39, 22),    -- El Gran Gatsby: Leonardo DiCaprio, Kate Winslet
(40, 27), (40, 28);   -- 1917: Matt Damon, Anne Hathaway

INSERT INTO serie_actor (idSerie, idActor) VALUES
(1, 41), (1, 42), (1, 43),      -- Breaking Bad: Bryan Cranston, Aaron Paul, Anna Gunn
(2, 44), (2, 45), (2, 46),      -- Juego de Tronos: Kit Harington, Emilia Clarke, Peter Dinklage
(3, 47), (3, 48), (3, 49),      -- Stranger Things: Millie Bobby Brown, Finn Wolfhard, Winona Ryder
(4, 50), (4, 51), (4, 52),      -- The Mandalorian: Pedro Pascal, Gina Carano, Laura Linney
(5, 53), (5, 54), (5, 55),      -- Chernobyl: Evan Rachel Wood, Thandiwe Newton, Jeffrey Wright
(6, 56), (6, 57), (6, 58),      -- Westworld: Zendaya, Hunter Schafer, Brian Cox
(7, 59), (7, 60), (7, 61),      -- The Crown: Jeremy Strong, Sarah Snook, Bob Odenkirk
(8, 69), (8, 70),               -- The Witcher: Henry Cavill, Freya Allan
(9, 62), (9, 63),               -- Better Call Saul: Bob Odenkirk, Rhea Seehorn
(10, 1), (10, 2),               -- True Detective: Leonardo DiCaprio, Christian Bale
(11, 44), (11, 45),             -- House of the Dragon: Kit Harington, Emilia Clarke
(12, 50), (12, 47),             -- The Last of Us: Pedro Pascal, Millie Bobby Brown
(13, 59), (13, 60),             -- Succession: Jeremy Strong, Sarah Snook
(14, 64), (14, 65),             -- The White Lotus: Jason Sudeikis, Juno Temple
(15, 52), (15, 53),             -- Ozark: Laura Linney, Evan Rachel Wood
(16, 35), (16, 36),             -- Peaky Blinders: Gary Oldman, Tom Hardy
(17, 11), (17, 12),             -- Black Mirror: Scarlett Johansson, Samuel L. Jackson
(18, 37), (18, 38),             -- The Handmaid's Tale: Charlize Theron, Natalie Portman
(19, 4), (19, 5),               -- Fargo: Robert De Niro, Al Pacino
(20, 27), (20, 28),             -- Mindhunter: Matt Damon, Anne Hathaway
(21, 50), (21, 9),              -- Narcos: Pedro Pascal, Brad Pitt
(22, 15), (22, 16),             -- Dark: Keanu Reeves, Laurence Fishburne
(23, 39), (23, 40),             -- The Boys: Emma Stone, Ryan Gosling
(24, 56), (24, 57),             -- Euphoria: Zendaya, Hunter Schafer
(25, 38), (25, 39),             -- The Queen's Gambit: Natalie Portman, Emma Stone
(26, 22), (26, 23),             -- Bridgerton: Kate Winslet, Cate Blanchett
(27, 66), (27, 67),             -- The Umbrella Academy: Brett Goldstein, Henry Cavill
(28, 7), (28, 8),               -- Money Heist: Tom Hanks, Morgan Freeman
(29, 25), (29, 26),             -- Squid Game: Joaquin Phoenix, Russell Crowe
(30, 64), (30, 65);             -- The Office: Jason Sudeikis, Juno Temple

INSERT INTO capitulo (nombre, duracion, idSerie) VALUES
('Pilot', 53, 1),
('Cat''s in the Bag...', 54, 1),
('And the Bag''s in the River', 58, 1),
('Cancer Man', 40, 1),
('Gray Matter', 55, 1),
('Crazy Handful of Nothin''', 44, 1),
('A No-Rough-Stuff-Type Deal', 52, 1),
('Seven Thirty-Seven', 43, 1),
('Grilled', 56, 1),
('Bit by a Dead Bee', 45, 1),

('Winter Is Coming', 59, 2),
('The Kingsroad', 53, 2),
('Lord Snow', 41, 2),
('Cripples, Bastards, and Broken Things', 58, 2),
('The Wolf and the Lion', 57, 2),
('A Golden Crown', 57, 2),
('You Win or You Die', 60, 2),
('The Pointy End', 55, 2),
('Baelor', 56, 2),
('Fire and Blood', 57, 2),

('The Vanishing of Will Byers', 56, 3),
('The Weirdo on Maple Street', 58, 3),
('Holly, Jolly', 60, 3),
('The Body', 54, 3),
('The Flea and the Acrobat', 60, 3),
('The Monster', 60, 3),
('Aftermath', 55, 3),
('Nightfall', 56, 3),
('The Bathtub', 57, 3),
('The Upside Down', 58, 3),

('The Mandalorian', 44, 4),
('The Child', 56, 4),
('The Sin', 52, 4),
('Sanctuary', 54, 4),
('The Gunslinger', 44, 4),
('The Prisoner', 40, 4),
('The Reckoning', 57, 4),
('Redemption', 60, 4),
('The Bounty', 47, 4),
('Finale', 50, 4),

('Please Remain Calm', 49, 5),
('The Beast', 53, 5),
('Open Wide, O Earth', 43, 5),
('The Happiness of All Mankind', 50, 5),
('Vichnaya Pamyat', 52, 5),
('The Sacrifice', 42, 5),
('The Aftermath', 46, 5),
('Remembrance', 47, 5),
('Fallout', 50, 5),
('Judgment', 49, 5),

('The Original', 55, 6),
('Chestnut', 52, 6),
('The Stray', 48, 6),
('Dissonance Theory', 50, 6),
('Contrapasso', 53, 6),
('Trace Decay', 51, 6),
('The Adversary', 49, 6),
('Rebellion', 55, 6),
('Revelation', 54, 6),
('Convergence', 53, 6),

('Wolferton Splash', 58, 7),
('Hyde Park Corner', 56, 7),
('Windsor', 54, 7),
('Dear Mrs. Kennedy', 57, 7),
('Regal', 55, 7),
('Coup', 59, 7),
('Smoke and Mirrors', 53, 7),
('Misadventure', 52, 7),
('Legacy', 51, 7),
('Ascension', 50, 7),

('The End''s Beginning', 45, 8),
('Four Marks', 47, 8),
('Betrayer Moon', 46, 8),
('Of Banquets, Bastards and Burials', 48, 8),
('Bottled Appetites', 44, 8),
('Rare Species', 49, 8),
('Destiny', 48, 8),
('Valor', 47, 8),
('Fate', 46, 8),
('Honor', 45, 8),

('Uno', 60, 9),
('Mijo', 58, 9),
('Five-O', 59, 9),
('Nacho', 57, 9),
('Pimento', 56, 9),
('Marco', 55, 9),
('Hero', 54, 9),
('Alpine Shepherd Boy', 53, 9),
('Cobbler', 52, 9),
('Final Call', 51, 9),

('The Long Bright Dark', 50, 10),
('Seeing Things', 51, 10),
('The Locked Room', 52, 10),
('Who Goes There', 53, 10),
('The Secret Fate of All Life', 54, 10),
('Haunted Houses', 55, 10),
('After You''ve Gone', 56, 10),
('Beyond', 57, 10),
('Resolution', 56, 10),
('Finale', 55, 10),

('The Heirs of the Dragon', 48, 11),
('The Rogue Prince', 49, 11),
('The Princess and the Queen', 50, 11),
('The Black Queen', 51, 11),
('The Bloodline', 52, 11),
('The Heir''s Gambit', 53, 11),
('The Dragon''s Claw', 54, 11),
('The Flame and the Shadow', 55, 11),
('Reign', 56, 11),
('Destiny', 55, 11),

('When You''re Lost in the Darkness', 57, 12),
('Infected', 56, 12),
('Long, Long Time', 55, 12),
('Please Hold My Hand', 54, 12),
('Endure and Survive', 53, 12),
('Kin', 52, 12),
('Survivors', 53, 12),
('Echoes', 52, 12),
('Dawn', 51, 12),
('Aftermath', 50, 12),

('Celebration', 59, 13),
('Shit Show at the Fuck Factory', 58, 13),
('Lifeboats', 57, 13),
('Sad Sack Wasp Trap', 56, 13),
('I Went to Market', 55, 13),
('Which Side Are You On?', 54, 13),
('Austerlitz', 53, 13),
('Nobody Is Ever Missing', 52, 13),
('Pre-Nuptial', 51, 13),
('Too Much, Too Late', 50, 13),

('Arrivals', 45, 14),
('New Day', 46, 14),
('Mysterious Guests', 47, 14),
('The Inconvenience of Being', 48, 14),
('Unwelcome Guests', 49, 14),
('The Breakdown', 50, 14),
('Departures', 51, 14),
('Insight', 52, 14),
('Crisis', 51, 14),
('Conclusion', 50, 14),

('Sugarwood', 52, 15),
('Blue Cat', 53, 15),
('My Dripping Sleep', 54, 15),
('Kaleidoscope', 55, 15),
('It Came From Michoacán', 56, 15),
('The Toll', 57, 15),
('Outer Darkness', 58, 15),
('In the Gray', 59, 15),
('Aftermath', 60, 15),
('Finale', 59, 15),

('The Real Book', 60, 16),
('The Noose', 59, 16),
('The D-Plan', 58, 16),
('A Dangerous Game', 57, 16),
('The Heist', 56, 16),
('The Reckoning', 55, 16),
('Scheme', 54, 16),
('Underworld', 53, 16),
('Betrayal', 52, 16),
('The End', 51, 16),

('The National Anthem', 54, 17),
('Fifteen Million Merits', 53, 17),
('The Entire History of You', 52, 17),
('Be Right Back', 51, 17),
('White Bear', 50, 17),
('The Waldo Moment', 49, 17),
('Hated in the Nation', 48, 17),
('San Junipero', 47, 17),
('Striking Vipers', 46, 17),
('Reset', 50, 17),

('Offred', 45, 18),
('Birth Day', 46, 18),
('Nolite Te Bastardes Carborundorum', 47, 18),
('Household', 48, 18),
('Salvaging', 49, 18),
('Faithful', 50, 18),
('Night', 51, 18),
('Rebellion', 52, 18),
('Resistance', 51, 18),
('Liberation', 50, 18),

('The Crocodile''s Dilemma', 52, 19),
('The Rooster Prince', 53, 19),
('A Fox, a Rabbit, and a Cabbage', 54, 19),
('Eating the Blame', 55, 19),
('The Six Ungraspables', 56, 19),
('Buridan''s Ass', 57, 19),
('The Narrow Escape Problem', 58, 19),
('Who Rules the Land of Denial?', 59, 19),
('Escape', 60, 19),
('Aftermath', 59, 19),

('The Case of Edmund Kemper', 60, 20),
('Talking to the Enemy', 59, 20),
('The Interview', 58, 20),
('The Family', 57, 20),
('The Missing Piece', 56, 20),
('The Breakthrough', 55, 20),
('Investigation', 57, 20),
('Suspects', 56, 20),
('Profile', 55, 20),
('Closure', 54, 20),

('Descenso', 54, 21),
('The Sword of Simón Bolívar', 53, 21),
('Los Pepes', 52, 21),
('Blowback', 51, 21),
('The Big Heist', 50, 21),
('El Chapo', 49, 21),
('Breaking Point', 48, 21),
('Ambush', 47, 21),
('Retribution', 46, 21),
('Confrontation', 45, 21),

('Secrets', 47, 22),
('Lies', 48, 22),
('Time', 49, 22),
('The Cycle', 50, 22),
('Echoes', 51, 22),
('Origins', 52, 22),
('Connections', 53, 22),
('Revelations', 54, 22),
('Shadows', 52, 22),
('Unraveling', 51, 22),

('The Name of the Game', 55, 23),
('Cherry', 56, 23),
('Get Some', 57, 23),
('The Female of the Species', 58, 23),
('Good for the Soul', 59, 23),
('The Only Way', 60, 23),
('Insurgence', 50, 23),
('Outbreak', 49, 23),
('Uprising', 48, 23),
('Finale', 47, 23),

('Pilot', 59, 24),
('Stuntin'' Like My Daddy', 58, 24),
('Shook One: Part II', 57, 24),
('And Dial That', 56, 24),
('Trouble Don''t Last Always', 55, 24),
('The Next Episode', 54, 24),
('The Real', 53, 24),
('You Get Me, I Get You', 52, 24),
('The End', 51, 24),
('Renewal', 50, 24),

('Openings', 50, 25),
('Exchanges', 51, 25),
('Midgame', 52, 25),
('Fork', 53, 25),
('Endgame', 54, 25),
('Sacrifice', 55, 25),
('Checkmate', 56, 25),
('Advantage', 55, 25),
('Transition', 54, 25),
('Finale', 53, 25),

('Diamond of the First Water', 57, 26),
('Shock and Awe', 58, 26),
('Suspicions', 59, 26),
('The Duke and I', 60, 26),
('An Unthinkable Act', 59, 26),
('The Viscount Who Loved Me', 58, 26),
('The Aftermath', 57, 26),
('Secrets and Scandals', 56, 26),
('Aftermath', 55, 26),
('Revelation', 54, 26),

('We Only See Each Other at Weddings and Funerals', 55, 27),
('Run Boy Run', 54, 27),
('Extra Ordinary', 53, 27),
('The Day That Was Ended', 52, 27),
('The Time of the End', 51, 27),
('The Rest', 50, 27),
('Awakening', 49, 27),
('Rising', 48, 27),
('Unbound', 47, 27),
('Convergence', 46, 27),

('Do as Planned', 49, 28),
('Mi Agente de Hacienda', 50, 28),
('El Profesor', 51, 28),
('La Casa de Papel', 52, 28),
('The Heist Begins', 53, 28),
('Inside the Bank', 54, 28),
('The Ambush', 55, 28),
('The Escape', 56, 28),
('The Countdown', 57, 28),
('Finale', 55, 28),

('Red Light', 58, 29),
('Green Light', 57, 29),
('Tug of War', 56, 29),
('Marbles', 55, 29),
('Glass Stepping Stones', 54, 29),
('The Final Game', 53, 29),
('Elimination', 52, 29),
('Survival', 58, 29),
('Resistance', 57, 29),
('Victory', 56, 29),

('Pilot', 44, 30),
('Diversity Day', 58, 30),
('Health Care', 48, 30),
('The Alliance', 44, 30),
('Basketball', 45, 30),
('Hot Girl', 54, 30),
('The Dundies', 58, 30),
('Dundies II', 55, 30),
('Diversity Redux', 54, 30),
('Finale', 53, 30);

INSERT INTO comentario (comentario, idPelicula, idUsuario) VALUES
('¡Increíble película! La mejor de este año.', 1, 2),
('DiCaprio está brillante en este papel.', 7, 3),
('Esta película me ha cambiado como persona.', 6, 9),
('Tarantino en estado puro, ¡obra maestra!', 4, 4),
('Los efectos especiales siguen impresionando.', 10, 5),
('Una experiencia cinematográfica única...', 3, 6),
('Los efectos especiales están muy logrados.', 1, 12),
('Una experiencia fabulosa, no me arrepiento.', 3, 10),
('De lo mejor del cine moderno.', 12, 7),
('La volvería a ver, ha sido emocionante.', 1, 20),
('La banda sonora es simplemente épica.', 14, 8),
('Merece todos los premios que recibió.', 9, 9),
('Una adaptación perfecta...', 1, 10),
('No pude quitar los ojos de la pantalla.', 5, 11);
('No he entendido muy bien algunas cosas.', 12, 16);
