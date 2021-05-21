/*********** table users **************/
CREATE TABLE users(idUser SERIAL NOT NULL,
firstname VARCHAR(60) NOT NULL,
lastname VARCHAR(60) NOT NULL,
username VARCHAR(60) NOT NULL,
birthdate DATE NOT NULL,
sex VARCHAR(2) NOT NULL,
email VARCHAR(50) NOT NULL,
phone VARCHAR(20) NOT NULL,
password VARCHAR(30) NOT NULL,
registrationDate DATE NOT NULL,
role VARCHAR(30) NOT NULL,PRIMARY KEY(idUser));
