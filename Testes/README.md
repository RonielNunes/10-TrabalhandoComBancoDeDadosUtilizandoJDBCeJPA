# **_Introdução ao JDBC_**

### A alternativa INCORRETA é:
A classe Connection usa o método executeQuery para execução de uma consulta.


### Qual classe ou interface NÃO pertence ao pacote "java.sql"?


SQLQuery
### A API que permite que um programa Java se comunique com banco de dados de diversos fornecedores é:

JDBC

### Qual método é usado para mover o curso para o próximo registro (row) em um objeto ResultSet:
next

### Um(a) _______ armazena dados de forma estruturada, tornando o acesso e atualização dos dados mais rápido, pois aumenta a eficiência computacional (menor “gasto“ de memória, processamento e tempo).

Banco de Dados

### Quais são os parâmetros passados a um dos métodos getConnection da classe DriverManager para iniciar uma conexão?
url, user e database


### Dado o código a seguir:

String sql = "...";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1 , aluno.getNome());
stmt.setInt(2, aluno.getIdade());
stmt.setString(3 , aluno.getEstado());

Para o objeto stmt ser corretamente executado, a reticências (...) da variável “sql” deve ser substituída por:

INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)

### O valor retornado pelo método executeQuery da interface java.sql.Statement é uma referência a uma instância da classe:
ResultSet

### Para utilizar a API JDBC para se conectar a um banco de dados, o programa JAVA deve possuir o pacote:
import java.sql.*


### O valor retornado pelo método executeUpdate da interface java.sql.Statement é:
Int
