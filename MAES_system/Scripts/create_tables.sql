CREATE TABLE Usuario(
  matricula CHAR(10),
  tipo CHAR(1) NOT NULL,
  nombre CHAR(30) NOT NULL,
  contrasena CHAR(15) NOT NULL,
  PRIMARY KEY (matricula)
);

CREATE TABLE Materia(
  id INT AUTO_INCREMENT,
  nombre CHAR(30) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Ubicacion(
  id INT AUTO_INCREMENT,
  lugar CHAR(30) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Asistencia(
  matricula_mae CHAR(10),
  hora_entrada TIME,
  hora_salida TIME NULL,
  fecha DATE,
  PRIMARY KEY(matricula_mae, hora_entrada, fecha),
  FOREIGN KEY (matricula_mae) REFERENCES
    Usuario(matricula)
);

CREATE TABLE Asesoria(
  matricula_alumno CHAR(10),
  matricula_mae CHAR(10),
  materia INT,
  lugar INT,
  disponibilidad CHAR(1) NOT NULL,
  PRIMARY KEY (matricula_mae, matricula_alumno,
    materia, lugar),
  FOREIGN KEY (matricula_mae) REFERENCES Usuario(matricula),
  FOREIGN KEY (materia) REFERENCES Materia(id),
  FOREIGN KEY (lugar) REFERENCES Ubicacion(id)
);
