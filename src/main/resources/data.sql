-- Usuarios iniciales (INSERT IGNORE evita duplicados en reinicios)
INSERT IGNORE INTO usuarios (nombre, email, password, rol) VALUES ('Administrador', 'admin@horaspro.cl', 'admin123', 'ADMIN');
INSERT IGNORE INTO usuarios (nombre, email, password, rol) VALUES ('Usuario Test', 'usuario@test.cl', 'user123', 'USUARIO');

-- Servicios (IDs explícitos para que INSERT IGNORE ignore duplicados en reinicios)
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (1, 'Hora de programacion', 'Sesion individual de programacion guiada por un experto. Resolvemos tu problema en tiempo real.', 15000, 60);
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (2, 'Asesoria base de datos', 'Diseño, optimizacion y consultas SQL. Incluye modelado ER y buenas practicas.', 20000, 60);
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (3, 'Clase de Java', 'Aprende Java desde cero o avanza en temas especificos con un instructor certificado.', 18000, 60);
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (4, 'Introduccion a Spring Boot', 'Construye tu primera API REST con Spring Boot, JPA y base de datos.', 16000, 60);
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (5, 'Diseno de APIs REST', 'Principios REST, versionado, autenticacion y documentacion con OpenAPI.', 22000, 60);
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (6, 'Fundamentos de Git y GitHub', 'Control de versiones, ramas, pull requests y flujo de trabajo colaborativo.', 12000, 60);
INSERT IGNORE INTO servicios (id_servicio, nombre, descripcion, precio, duracion) VALUES (7, 'Seguridad en aplicaciones web', 'OWASP Top 10, autenticacion segura, manejo de tokens y mejores practicas.', 25000, 60);
