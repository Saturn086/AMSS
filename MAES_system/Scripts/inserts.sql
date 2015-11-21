# 0 = MAE, 1 = Admin
INSERT INTO Usuario VALUES ('A00815248','0','Omar Manjarrez Osornio','pando83');
INSERT INTO Usuario VALUES ('A01412546','0','John Smith Martinez','salchicha23');
INSERT INTO Usuario VALUES ('A01238642','0','Bruno Diaz Gonzales','nosoybatman123');
INSERT INTO Usuario VALUES ('A00512240','0','Peter Parker Rodriguez','hombrearacnido');
INSERT INTO Usuario VALUES ('A00000000','1','Profesor Charles Xavier','soygenial01');

INSERT INTO Materia VALUES (1,'Matematicas I');
INSERT INTO Materia VALUES (2,'Metodos Numericos');
INSERT INTO Materia VALUES (3,'Fisica III');
INSERT INTO Materia VALUES (4,'Quimica Analitica');
INSERT INTO Materia VALUES (5,'Fisica I');

INSERT INTO Ubicacion VALUES (1,'Cubiculo 1A');
INSERT INTO Ubicacion VALUES (2,'Cubiculo 1B');
INSERT INTO Ubicacion VALUES (3,'Cubiculo 2A');
INSERT INTO Ubicacion VALUES (4,'Cubiculo 2B');
INSERT INTO Ubicacion VALUES (5,'Sala de Juntas');

INSERT INTO Asistencia VALUES ('A00815248','10:32','10:55','2015-09-03');
INSERT INTO Asistencia VALUES ('A00815248','09:10','10:10','2015-09-06');
INSERT INTO Asistencia VALUES ('A01412546','13:00','15:02','2015-10-06');
INSERT INTO Asistencia VALUES ('A01412546','12:58','14:57','2015-10-10');
INSERT INTO Asistencia VALUES ('A00512240','13:10',NULL,'2015-10-13');
INSERT INTO Asistencia VALUES ('A00815248','09:01',NULL,'2015-11-13');

#0 = no disponible, 1 = disponible
INSERT INTO Asesoria VALUES ('A00546835','A00815248',2,1,0);
INSERT INTO Asesoria VALUES ('A00546455','A00815248',1,1,0);
INSERT INTO Asesoria VALUES ('A00546835','A00512240',5,3,0);
INSERT INTO Asesoria VALUES ('A01246835','A01238642',3,2,1);
INSERT INTO Asesoria VALUES ('A01446425','A01238642',2,2,1);
