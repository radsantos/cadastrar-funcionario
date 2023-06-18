# cadastrar-funcionario
Crud para cadastrar funcionario. Implementado as seguintes funções: SALVAR, EDITAR, EXCLUIR  e CONSULTAR.

Utilizado banco de dados MySql para persistir os dados.

Comando para criar o bando de dados:

create table funcionario(

id int auto_increment primary key,

matricula varchar(10) not null unique,

nome varchar(100) not null,

cargo varchar(50),

salario double);
