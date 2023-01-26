drop table tb_usuarios if exists;
create table tb_usuarios(
	id_usuario int not NULL auto_increment,
	nome_usuario VARCHAR(200) not NULL,
	email_usuario VARCHAR(255) not NULL,
	data_nascimento DATE not NULL,
	primary key (id_usuario)
);

drop table tb_ciclos_menstruais if exists;
create table tb_ciclos_menstruais(
	id_ciclo_menstrual int not NULL auto_increment,
	id_usuario int not NULL,
	data_inicio DATE not NULL,
	data_termino DATE not NULL,
	tipo_fluxo VARCHAR(45),
	comentarios VARCHAR(255),
	primary key (id_ciclo_menstrual),
	foreign key (id_usuario) REFERENCES tb_usuarios(id_usuario)
);

drop table tb_informacoes if exists;
create table tb_informacoes(
	id_informacoes int not NULL auto_increment,
	categoria VARCHAR(255) not NULL,
	informacoes VARCHAR(512) not NULL,
	primary key (id_informacoes)
);