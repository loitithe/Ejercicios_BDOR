CREATE SCHEMA objetos;

CREATE TYPE Persona As(nombre text,edad int);
CREATE TYPE Paciente As(numero_historia text,grupo_sanguineo text);
CREATE TYPE ExamenMedico As(nombre_examen text,fecha_realizacion Date,resultado text);
CREATE TYPE Medico As(codigo_medico text,especialidad text);



CREATE TABLE if not exists objetos.Medico
(
	medico_id serial PRIMARY KEY,
	datos_personales objetos.Persona NOT NULL,
	medico_info objetos.Medico
);
CREATE TABLE if not exists objetos.Paciente
(
	paciente_id serial PRIMARY KEY,
	datos_personales objetos.Persona NOT NULL,
	paciente_info objetos.Paciente
);
CREATE TABLE if not exists objetos.CitasMedicas
(
	cita_id serial PRIMARY KEY,
    fecha_hora Date,
	foreign key (medico_id) REFERENCES objetos.Medico(medico_id),
	foreign key (paciente_id) REFERENCES objetos.Paciente(paciente_id));
	
);
CREATE TABLE if not exists objetos.ExamenesMedicos
(
	examen_id serial PRIMARY KEY,
    examen_info objetos.ExamenMedico,
	foreign key (paciente_id) REFERENCES objetos.Paciente(paciente_id),
	
);
private final String createTypePersona = "CREATE TYPE Persona As(nombre text,edad int);";

private final String createTypePaciente = "CREATE TYPE Paciente As(numero_historia text,grupo_sanguineo text);";

private final String createTypeExamenMedico = "CREATE TYPE ExamenMedico As(nombre_examen text,fecha_realizacion Date,resultado text);";

private final String createTypeMedicoa = "CREATE TYPE Medico As(codigo_medico text,especialidad text);";

