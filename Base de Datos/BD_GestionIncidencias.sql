DROP DATABASE IF EXISTS db_gestion_incidencias;
create database db_gestion_incidencias;
USE db_gestion_incidencias;

-- Creacion de Tablas
CREATE TABLE Departamento (
codDepartamento	INT AUTO_INCREMENT PRIMARY KEY,
nombreDepartamento	VARCHAR(100) NOT NULL,
descripcionDpto	VARCHAR(100) not null,
estado				CHAR(10) NOT NULL
);

CREATE TABLE Empleado
(
codEmpleado 		INT AUTO_INCREMENT PRIMARY KEY,
nombre				varchar(50),
apellido			varchar(50),
email				varchar(50),
fechaNac			date,
direccion			varchar(50),
tipoEmp			varchar(50),
codDepartamento	int,
constraint Empleado_FK1 foreign key (codDepartamento) references Departamento (codDepartamento)
);

CREATE TABLE Incidencia
(
codIncidencia	INT AUTO_INCREMENT PRIMARY KEY,
Descripcion		VARCHAR(100) not null,
fechaRegistro	DATETIME,
nivelPrioridad	ENUM('Alto', 'Medio', 'Bajo') DEFAULT 'Bajo',
codEmpleado	INT,
categoria 	VARCHAR(30),
estado		VARCHAR(20),
constraint Incidencia_FK1 foreign key(codEmpleado) references Empleado(codEmpleado)
);


CREATE TABLE Usuario (
    codUsu INT AUTO_INCREMENT PRIMARY KEY,
    logUsu VARCHAR(20) NOT NULL,
    pasUsu VARCHAR(20) NOT NULL,
    nomUsu VARCHAR(50) NOT NULL,
    estUsu CHAR(1) NOT NULL,
    codDepartamento INT ,
    CONSTRAINT Usuario_FK FOREIGN KEY (codDepartamento) REFERENCES Departamento(codDepartamento)
);

CREATE TABLE Atencion
(
codAtencion	int auto_increment primary key,
acciones VARCHAR(50),
fechaAtencion DATETIME,
presupuesto		DOUBLE,
detallePresupuesto VARCHAR(50),
observaciones	VARCHAR(30),
codEmpleado 	INT,
codIncidencia	int,
constraint Atencion_FK1 foreign key(codIncidencia) references Incidencia(codIncidencia),
constraint Atencion_FK2 foreign key (codEmpleado) references Empleado (codEmpleado)
);

CREATE TABLE MENU (
 codMen INT NOT NULL AUTO_INCREMENT,
 desMen VARCHAR(30) DEFAULT NULL,
 urlMen VARCHAR(400) DEFAULT NULL,
 PRIMARY KEY (codMen)
);

CREATE TABLE ACCESO(
 codMenu 	int NOT NULL,
 codUsu 	int NOT NULL,
 PRIMARY KEY (codMenu, codUsu),
 KEY codUsu (codUsu),
 CONSTRAINT ACCESO_FK1 FOREIGN KEY (codMenu) REFERENCES MENU (codMen),
 CONSTRAINT ACCESO_FK2 FOREIGN KEY (codUsu) REFERENCES Usuario (codUsu)
);

-- insertar registros manualmente - tabla Departamento
INSERT INTO Departamento (nombreDepartamento, descripcionDpto, estado)
VALUES 
    ( 'OSI', 'Oficina de Seguridad de la Informacion', 'Activo'),
    ( 'Mesa de Servicios', 'Departamento de Finanzas', 'Activo'),
    ( 'OTI', 'Oficina de Tecnologia de la Informacion', 'Activo'),
    ( 'OPP', 'Oficina de Planeamiento y Presupuesto', 'Activo');

-- insertar registros manualmente - tabla Empleado
INSERT INTO Empleado (nombre, apellido, email, fechaNac, direccion, tipoEmp, codDepartamento)
VALUES
    ('Juan Miguel', 'Pérez', 'juan.perez@email.com', '1990-05-15', 'Calle 123', 'Tecnico', 1),
    ('María Mercedes', 'Gómez', 'maria.gomez@email.com', '1988-08-22', 'Avenida 456', 'Secretaria', 1),
    ('Carlos', 'Rodríguez', 'carlos.rodriguez@email.com', '1995-02-10', 'Calle 789', 'Asistente', 2),
    ('Laura Roxana', 'Fernández Pereira', 'laura.fernandez@email.com', '1992-11-30', 'Avenida 1011', 'Tecnico', 3),
    ('Pedro Josue', 'López', 'pedro.lopez@email.com', '1985-07-05', 'Calle 1213', 'Tecnico', 4),
    ('Ana Paula', 'Martínez Solar', 'ana.martinez@email.com', '1993-04-18', 'Avenida 1415', 'Tecnico', 1),
    ('Miguel Angel', 'González Prado', 'miguel.gonzalez@email.com', '1998-01-25', 'Calle 1617', 'Desarrollador', 3),
    ('Isabel', 'Díaz Martinez', 'isabel.diaz@email.com', '1987-06-08', 'Avenida 1819', 'Secretaria', 2),
    ('Francisco Javier', 'Hernández', 'javier.hernandez@email.com', '1991-09-12', 'Calle 2021', 'Desarrollador', 1),
    ('Carmen Lorena', 'Ruiz Ruiz', 'carmen.ruiz@email.com', '1989-03-04', 'Avenida 2223', 'Analista', 4);


-- insertar registros manualmente - tabla INCIDENCIA
INSERT INTO incidencia (descripcion, fechaRegistro, nivelPrioridad, categoria, estado, codEmpleado)
VALUES 
('Error en la aplicación móvil', '2015-01-15 10:30:00', 'Alto', "Tecnologico", "Procesada", 9),
('Problema con la red local', '2016-03-22 09:15:00', 'Medio', "No Tecnologico", "No Procesada", 2),
('Fallo en la impresora', '2017-05-10 14:20:00', 'Bajo', "Alerta Externa", "Procesada", 3),
('Incidente de seguridad informática', '2018-07-18 16:45:00', 'Alto', "No Tecnologico", "No Procesada", 2),
('Falla en el servidor de correo', '2019-09-25 08:00:00', 'Alto', "Tecnologico", "Procesada", 3),
('Problema con la conexión VPN', '2020-11-03 11:30:00', 'Medio', "Alerta Externa", "Procesada", 4),
('Solicitud de asistencia técnica', '2021-02-14 13:10:00', 'Bajo', "Tecnologico", "No Procesada", 2),
('Error en el software de contabilidad', '2022-04-30 17:00:00', 'Medio', "No Tecnologico", "Procesada", 4),
('Acceso no autorizado al sistema', '2023-06-08 10:20:00', 'Alto', "Tecnologico", "No Procesada", 3),
('Interrupción del servicio web', '2023-07-20 09:45:00', 'Alto', "Tecnologico", "Procesada", 2),
('Problema con la aplicación de usuario', '2023-08-12 14:55:00', 'Medio', "Alerta Externa", "Procesada", 5),
('Problema de conectividad a la base de datos', '2023-09-05 12:30:00', 'Alto', "Tecnologico", "Procesada", 2),
('Ataque de phishing', '2023-10-01 18:20:00', 'Alto', "Tecnologico", "Procesada", 2),
('Solicitud de actualización de software', '2022-10-25 15:40:00', 'Bajo', "Tecnologico", "Procesada", 3),
('Problema en la base de datos principal', '2023-11-07 10:00:00', 'Medio', "No Tecnologico", "Procesada", 2),
('Fallo en la fotocopiadora', '2023-11-15 11:15:00', 'Bajo', "Tecnologico", "Procesada", 3),
('Violación de seguridad de datos', '2022-11-22 13:25:00', 'Alto', "Tecnologico", "Procesada", 7),
('Error crítico en el sistema operativo', '2020-12-03 16:05:00', 'Alto', "Alerta Externa", "Procesada", 2),
('Problema con el correo electrónico corporativo', '2023-12-15 09:30:00', 'Medio', "Tecnologico", "No Procesada", 3),
('Solicitud de mantenimiento de hardware', '2023-12-28 14:00:00', 'Bajo', "No Tecnologico", "Procesada", 2);


INSERT INTO Atencion (acciones, fechaAtencion, presupuesto, detallePresupuesto, observaciones, codEmpleado, codIncidencia) VALUES
('Reiniciar servidor', '2023-06-01 10:00:00', 200.00, 'Costo de reinicio', 'Ninguna', 6, 1),
('Limpiar rodillos', '2023-06-15 09:30:00', 20.00, 'Costo de limpieza de rodillos', 'Ninguna', 6, 3),
('Reiniciar servidor de correo', '2023-06-04 08:00:00', 0.00, 'Sin costo', 'Ninguna', 3, 5),
('Actualizar firmware del router', '2023-06-05 11:30:00', 75.00, 'Costo de actualización de firmware', 'Ninguna', 4, 6),
('Actualizar software a la última versión', '2023-06-07 17:30:00', 150.00, 'Costo de actualización', 'Ninguna', 6, 8),
('Actualizar certificado SSL', '2023-07-20 10:15:00', 100.00, 'Costo del certificado', 'Urgente', 2, 10),
('Depurar errores', '2023-08-12 15:00:00', 100.00, 'Costo de depuración', 'Ninguna', 5, 11),
('Optimizar consultas SQL', '2023-09-05 13:15:00', 100.00, 'Costo de optimización', 'Ninguna', 2, 12),
('Implementar medidas de seguridad', '2023-10-02 11:00:00', 300.00, 'Costo de medidas de seguridad', 'Urgente', 2, 13),
('Instalación de software actualizado', '2024-06-28 14:00:00', 300.00, 'Instalación de software y configuración inicial.', 'Software funcionando correctamente.', 4, 14),
('Reparar índices corruptos', '2023-11-08 09:00:00', 200.00, 'Costo de reparación', 'Urgente', 2, 15),
('Realizar mantenimiento preventivo', '2023-11-16 11:00:00', 50.00, 'Costo de mantenimiento', 'Ninguna', 3, 16),
('Implementar nuevas políticas de seguridad', '2022-11-23 12:00:00', 200.00, 'Costo de implementación', 'Urgente', 7, 17),
('Aplicar parches de seguridad', '2020-12-04 12:00:00', 100.00, 'Costo de parches', 'Urgente', 2, 18),
('Realizar limpieza de hardware', '2023-12-29 10:00:00', 50.00, 'Costo de limpieza', 'Ninguna', 2, 20);


-- insertar registros manualmente - tabla menu
INSERT INTO MENU VALUES(1,'INCIDENCIA','gestionIncidencia?operacion=listado');
INSERT INTO MENU VALUES(2,'EMPLEADO','gestionEmpleado?operacion=listar');
-- INSERT INTO MENU VALUES(3,'ATENCIONES','gestionDetalle_Incidencia?operacion=listar');

-- insertar registros manualmente - tabla USUARIO
INSERT INTO USUARIO VALUES (null,'logmesa','passmesa','MESA DE SERVICIOS', 1, 2);
INSERT INTO USUARIO VALUES (null,'logosi','passosi','OSI', 1, 1);
INSERT INTO USUARIO VALUES (null,'logoti','passoti','OTI' , 1, 3);

-- insertar registros manualmente - tabla ACCESO
INSERT INTO ACCESO VALUES(1,1);
INSERT INTO ACCESO VALUES(2,1);
INSERT INTO ACCESO VALUES(1,2);
INSERT INTO ACCESO VALUES(2,3);

-- vistas contenido tablas
-- SELECT * FROM CATEGORIA;
SELECT * FROM INCIDENCIA;
SELECT * FROM USUARIO;
SELECT * FROM MENU;
SELECT * FROM ACCESO;
SELECT * FROM EMPLEADO;
SELECT * FROM ATENCION;

    
