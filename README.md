# Simple Springboot API

# Tecnologias

- `Java/OpenJDK 11` - JDK 11
- `Spring boot` - Framework utilizado para desenvolver a API 
- `Postgresql` - Base de dados relacional
- `Flyway` - Utilizado para versionar e realizar as migrações automáticas da base de dados
- `Swagger` - Documentação da API

# Executando a aplicação com Docker

- Prerequisitos

	- `Git`
	- `Java JDK 11`
	- `Docker`
	- `Docker Compose`
	
Faça o clone do projeto: 

`git clone https://github.com/icaromagnago/springboot-api-docker.git`

	
Acesse a pasta do projeto

`cd springboot-api`
	
Execute o maven para buildar o projeto

`mvn clean install`
	
Execute o build do Dockerfile

`docker build -t pismo/pismo-api .`
		
Executando a aplicação

`docker-compose up`
	
A `API` estarará executando na porta `8080` e o postgresql na porta `5432`

Acesse `localhost:8080/swagger-ui.html` para vê a documentação swagger.
