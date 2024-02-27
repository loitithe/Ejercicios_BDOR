CREATE SCHEMA objetos;


CREATE TYPE objetos.Persona As(f1 text,f2 int);
CREATE TYPE objetos.Estudiante As(matricula text,carrera text);
CREATE TYPE objetos.Profesor As(cedula text,departamento text);

CREATE TABLE if not exists objetos.Estudiantes
(
	estudiante_id serial PRIMARY KEY,
	datos_personales objetos.Persona NOT NULL,
	estudiante_info objetos.Estudiante
);

CREATE TABLE if not exists objetos.Curso (
	curso_id serial PRIMARY KEY,
	nombre varchar(40) NOT NULL
);
CREATE TABLE if not exists objetos.Profesores(
	profesor_id serial PRIMARY KEY,
	datos_personales objetos.Persona,
	profesor_info objetos.Profesor,
	curso_id int,
	foreign key (curso_id) REFERENCES objetos.Curso(curso_id)
);
CREATE TABLE if not exists objetos.Inscripciones (
	inscripcion_id serial PRIMARY KEY,
	estudiante_id int,
	curso_id int,
	foreign key (estudiante_id) REFERENCES objetos.Estudiantes(estudiante_id),
	foreign key (curso_id) REFERENCES objetos.Curso(curso_id));
	