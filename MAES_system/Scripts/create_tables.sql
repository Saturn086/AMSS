CREATE TABLE Usuario(
  matricula CHAR(10),
  tipo CHAR(1) NOT NULL,
  nombre CHAR(30) NOT NULL,
  contrasena CHAR(15) NOT NULL,
  PRIMARY KEY (matricula)
);

CREATE TABLE Materia(
  id SMALLINT AUTO_INCREMENT,
  nombre CHAR(30) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE Ubicacion(
  id SMALLINT AUTO_INCREMENT,
  lugar CHAR(10) NOT NULL,
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
  nombre_materia SMALLINT,
  lugar SMALLINT,
  disponibilidad CHAR(1) NOT NULL,
  PRIMARY KEY (matricula_mae, matricula_alumno,
    nombre_materia, lugar),
  FOREIGN KEY (matricula_mae) REFERENCES Usuario(matricula),
  FOREIGN KEY (nombre_materia) REFERENCES Materia(id),
  FOREIGN KEY (lugar) REFERENCES Ubicacion(id)
);