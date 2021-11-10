# **_Trabalhando com Banco de Dados utilizando JDBC e JPA_**

# **_Introdução ao JDBC_**


## **Introdução ao Banco de Dados**
1. Configurar Banco de Dados
2. JDBC e drivers de conexão
3. Consultas com JDBC 

### Requisitos Básicos


MySQL (SGBD) e noções de SQL

Java Development Kit (JDK) – 1.8 ou superior

IntelliJ 2019.2.3 IDE

Gradle 5.3.1 (Para baixar o Driver JDBC)

Endereço no Github dos materiais que serão utilizados nessa aula:

https://github.com/danielkv7/digital-innovation-one/tree/master/Aula_JDBC_basico

## **Configurar Banco de Dados**

Um Banco de Dados (BD) armazena dados de forma estruturada, tornando o acesso e atualização dos dados mais rápido, pois aumenta a eficiência computacional (menor “gasto“ de memória, processamento e tempo).

**Nesta aula será utilizado o banco de dados relacional MySQL.**

Passos para instalar e configurar o banco de dados para esta aula:

Instalar MySQL
Configurar usuário e senha
Instalar MySQL Workbench (Opcional)
Criar Banco de dados 
Criar uma tabela

URL com Instruções de Instalação Ubuntu 18.04
https://github.com/danielkv7/digital-innovation-one/blob/master/Aula_JDBC_basico/jdbc-basico/src/main/java/part1/DatabaseInstructions

**Scripts SQL para criar tabela utilizada nessa aula :**

CREATE database digital_innovation_one;
```SQL
USE digital_innovation_one;

CREATE TABLE aluno (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    idade INTEGER NOT NULL,
    estado CHARACTER(2) NOT NULL
);
```
### **Exercício final**

Configure um banco de dados de acordo com os passos explicados nos slides anteriores

### **_Descrição da aula_**

////////////////////////////////////////////////////////
////// INSTRUÇÕES PARA INSTALAR BANCO DE DADOS /////////
////////////////////////////////////////////////////////

========================================================
======================= MYSQL ==========================
========================================================

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ATENÇÃO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
OS PASSOS ABAIXO FORAM UTILIZADOS PARA INSTALAR O MYSQL NO UBUNTU 18.04.
OUTRAS VERSÕES OU SISTEMAS OPERACIONAIS DEVERÃO UTILIZAR OUTRAS REFERẼNCIAS
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ATENÇÃO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

==========================
1 - === INSTALAR MYSQL ===
==========================

1 - Atualizar repositório
sudo apt update

2 - Instalar MySQL
sudo apt install mysql-server

3 - Verificar se instalação foi um sucesso (deve aparecer a versão do MySQL instalado).
mysql --version

4 - (OPCIONAL) (NÂO UTILIZEI NA AULA E NÂO SERÁ NECESSÀRIO PARA AS OUTRAS ETAPAS!) Trocar valores defaults para aumentar segurança
sudo mysql_secure_installation


======================================
2 - === CONFIGURAR USUÁRIO E SENHA ===
======================================

1 - Acessar banco de dados MySQL
sudo mysql

2 - Adicionar senha "password" ao usuário "root" (Rodar no prompt do  MySQL)
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';

3 - Recarregar permissões de acesso ao banco de dados (Rodar no prompt do  MySQL)
FLUSH PRIVILEGES;

4 - Sair do prompt do MySQL
quit;

Obs: A partir de agora, para acessar o mysql deverá ser utilizado o comando abaixo.
Quando pedir a senha, deverá colocar a senha: password
mysql -u root -p

===============================================
3 - === INSTALAR MYSQL WORKBENCH (OPCIONAL) ===
===============================================

1 - Atualizar repositório
sudo apt update

2 - Instalar MySQL Workbench
sudo apt install mysql-workbench

3 - Executar MySQL Workbench (também pode executar ao pesquisar por "workbench" em uma GUI do linux)
mysql-workbench


================================
4 - === CRIAR BANCO DE DADOS ===
================================

1 - Acessar banco de dados. Pode ser workbench ou linha de comando (conforme o comando abaixo)
mysql -u root -p
(Enter password:) password

2 - Criar um banco de dados (rodar no prompt do MySQL OU no MySQL workbench)
CREATE database digital_innovation_one;

3 - Usar o banco recém criado (digital_innovation_one) (rodar no prompt do MySQL OU no MySQL workbench)
USE digital_innovation_one;


============================
5 - === CRIAR UMA TABELA ===
============================

1 - Acessar banco de dados. Pode ser workbench ou linha de comando (conforme o comando abaixo)
mysql -u root -p
(Enter password:) password

2 - (CASO NÂO ESTEJA NO BANCO DE DADOS) Mudar para o banco digital_innovation_one (rodar no prompt do MySQL OU no MySQL workbench)
USE digital_innovation_one;

3 - Criar uma tabela no banco de dados (rodar no prompt do MySQL OU no MySQL workbench)
CREATE TABLE aluno (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    idade INTEGER NOT NULL,
    estado CHARACTER(2) NOT NULL
);

4 - Adicionar alguns exemplos (rodar no prompt do MySQL OU no MySQL workbench)
INSERT INTO aluno(nome, idade, estado) VALUES ('Pedro', 20, 'RJ');
INSERT INTO aluno(nome, idade, estado) VALUES ('Maria', 35, 'AC');
INSERT INTO aluno(nome, idade, estado) VALUES ('Joao', 10, 'SC');
INSERT INTO aluno(nome, idade, estado) VALUES ('Ana', 51, 'GO');




!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ATENÇÃO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
O PASSO ABAIXO É NECESSÁRIO PARA EXECUTAR AS PRÓXIMAS TAREFAS DA PART2 e PART3

O PASSO ABAIXO FOI UTILIZADO EM UM PROJETO GRADLE
CASO ESTEJA USANDO MAVEN (ou nenhum gerenciador de pacotes) DEVERÁ SER UTILIZADO OUTRO GUIA
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ATENÇÃO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


=======================================================
=== BAIXAR DRIVER PARA O JAVA SE COMUNICAR COM O BD ===
=======================================================

- No gradle deve-se adicionar no "build.gradle" (na parte de "dependencies") a linha abaixo
compile group: 'mysql', name: 'mysql-connector-java', version: '8.0.17'

## **JDBC e drivers de conexão**
JDBC(Java Database Connectivity) é uma API com diversas classes e interfaces escritas na linguagem Java que estão presentes nos pacotes java.sql e javax.sql. Elas permitem que programas em Java realizem conexões em bancos de dados para realizar consultas. Uma dessas classes principais é o driver JDBC que intermedia essa interação. 

Sem a API JDBC, seria necessário conhecer o protocolo proprietário de cada banco de dados para se conectar e realizar consultas. Já com a API JDBC, é utilizada somente UMA interface Java para qualquer banco de dados, deixando o driver  implementar as especificações de cada banco de dados, enquanto o desenvolvedor se preocupa apenas em selecionar um driver e criar as queries (neste caso, consultas SQL).

Classes e interfaces que serão utilizadas:

Classe DriverManager – Responsável pela comunicação com os drivers disponíveis. É utilizada para criar uma Connection com o banco de dados através de uma URL (que especifica driver, localização do BD e nome do BD).

Interface Connection – Representa a conexão com o banco de dados. Permite criar “Statements” que constroem consultas SQL.

Passos para se conectar ao banco de dados:

Realizar download do driver específico para o BD que será utilizado (nesta aula, será o MySQL). É possível baixar o driver manualmente ou através do Gradle ou Maven.

Criar URL (string de conexão) com os seguintes parâmetros: driver, endereço do BD e nome do BD.

Criar uma connection através do “DriverManager” utilizando o método “getConnection”, passando os parâmetros: string de conexão, usuário e senha.

### **Exercício final**
1. Criar outro usuário do BD e senha deste usuário e se conectar através da API JDBC.

2. Explorar os métodos da classe DriverManager e da interface Connection através da IDE (ex: intelliJ IDEA, eclipse…) ou documentos oficiais.

3. Configurar outro banco de dados (ex: PostgreSQL, H2…)e tentar se conectar a ele utilizando a API JDBC.


## **Consultas com JDBC**


# **_Trabalhando com JPA_**

## **Introdução da aula**

## **Entendendo o JPA e começando o mapeamento do banco**

- Problema notados: maior parte do tempo era gasto com queires SQL atráves do JDBC. Outro problema mudança de paradimana de OO para entidade relacionada, dois modelos para o mesmo sistema.
- Solução: Mapemanto Objeto Relacional(ORM) para presentar tabelas bd atrav´s de classes java. 
- 
## **Implementações do JPA**
-JPA é apenas a especificação, por issp temos que implementar. 
hIBERNITE E EXKIPNETE PARA EXECUTAR A APLICAÇÃO.

## **Linguagens de consulta orientada a objetos**
