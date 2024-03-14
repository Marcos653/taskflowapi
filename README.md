
# TaskFlow API

A TaskFlow API é uma aplicação REST desenvolvida para facilitar o gerenciamento de tarefas dentro de uma equipe.
Utilizando Java e o framework Spring Boot, essa API permite o cadastro de pessoas e tarefas,
associando-as aos respectivos departamentos e mantendo o controle sobre o progresso das atividades.

## Tecnologias Utilizadas

- **Linguagem de Programação:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** PostgreSQL
- **Documentação da API:** Swagger
- **Testes:** Testes unitários com JUnit

## Configuração e Execução

### Pré-Requisitos

Para executar o projeto, é necessário ter instalado:
- JDK 17
- Maven
- Docker e Docker Compose

### Passos para Execução

1. Clone o repositório do projeto.

2. Navegue até a pasta do projeto e execute o Docker Compose para iniciar o banco de dados PostgreSQL e a aplicação:

   ```sh
   docker-compose up --build
   ```

3. Após a inicialização, a aplicação estará disponível em: `http://localhost:8080`

   **Observação:** Certifique-se de que as portas `5432` (PostgreSQL) e `8080` (aplicação) não estão sendo utilizadas por outros serviços no seu sistema. Caso contrário, pare os serviços que estão utilizando essas portas antes de executar o comando acima.

### Acesso à Documentação da API

A documentação da API, gerada pelo Swagger, está disponível em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Estrutura da Aplicação

### Modelos

- **Pessoa**: Representa os usuários da aplicação, com atributos como id, nome, departamento e lista de tarefas.
- **Tarefa**: Representa as tarefas a serem gerenciadas, contendo informações como id, título, descrição, prazo, departamento, duração, pessoa alocada e status de conclusão.

### Endpoints Principais

- **Pessoas**: CRUD de pessoas.
- **Tarefas**: CRUD de tarefas.

## Testes

Os testes unitários foram implementados utilizando o JUnit e podem ser executados através do Maven com o comando:

```sh
mvn test
```

Após a execução dos testes, o JaCoCo gera o relatório de cobertura na pasta target/site/jacoco do projeto. Você pode acessar o relatório abrindo o arquivo index.html localizado nessa pasta com qualquer navegador web.

## Cobertura de Testes

A cobertura de testes é monitorada pelo JaCoCo, fornecendo um relatório detalhado sobre a cobertura de código da aplicação.
