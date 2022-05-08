# Control-More

<strong>------- English </strong>

Personal financial control web system

This project was created to be the object of evaluation for the Engineering Laboratory class.
The project was built from the elaboration of documents such as the specification of requirements, commercial technical proposal, domain, and implementation class diagrams, vision document, and description of the implemented use cases.
Mockups were also produced for the simulation of screens and navigability.
The database was previously created and was evaluated in the Database Laboratory class, which was also supported by a series of documentation and underwent changes as the project was being developed.

The project was developed to meet best programming practices and apply the knowledge acquired during the years I studied at FATEC Mogi das Cruzes. Therefore, the entire system was implemented to be modified and impact as few classes as possible, using design patterns and MVC architecture.

The Project is separated into three parts: core, domain, and web.

- Core:

	- Application: Result class, to receive entities or messages to be transmitted to View (web);
	- Control: Contains the facade, with a list of DAOs and another of Business Rules (Strategies). The save, change, delete, query, and view methods are implemented generically, receiving a domain entity.
	- DAO (Data Access Object): with the implemented DAOs of each domain entity.
	- JDBC: with the database connection class.
	- Business: with the implemented strategies.

- Domain: 
	Contains all implemented domain classes that inherit from a Domain Entity class.

- Web:
	- Command: a command to invoke each method specified in the facade.
	- Web Control: Contains the servlet with a list of each ViewHelper and Commands.
	- ViewHelper: with the ViewHelper of each domain entity; 
	- Additionally the jsp screens.

ADDITIONAL INFORMATION:
- Object Oriented Programming;
- Programming Language: Java;
- Web Development: Java EE (Servlets, JSP) + Apache Tomcat;
- Front-end: HTML, CSS, Bootstrap;
- Database: Oracle;
- Design Pattern: Facade, Command, ViewHelper, DAO, Strategy;
- Persistence: JDBC;
- Development environment: Eclipse;
<br><br>

<strong>------- Português</strong>

Sistema web de controle financeiro pessoal

Esse projeto foi criado com o intuito de ser objeto de avaliação para disciplina Laboratório de Engenharia. <br>
O projeto foi construido a partir de elaboração de documentos como: documento de requisitos, proposta técnica comercial, diagramas de classe de domínio e implementação, documento de visão e descrição dos casos de uso implementados. <br>
Também foram produzidos mockups para visualização das telas e navegabilidade.<br>
O banco de dados foi criado anteriormente e foi objeto de avaliação em Laboratório de Banco de Dados, o qual também foi amparado por uma série de documentações e sofreu alterações conforme o projeto foi sendo dsenvolvido.  

O projeto foi desenvolvido para atender melhores práticas de programação e aplicar os conhecimentos adquiridos durante os anos em que estudei na FATEC Mogi das Cruzes. Portante todo o sistema foi implementado para ser modificado e impactar o mínimo de 
classes possíveis, utilizando padrões de projeto e arquitetura MVC.

O Projeto é separado em três partes: core, domínio e web. 
- Core: 
	- Aplicação: classe Resultado, para receber entidades ou mensagens para serem transmitidas para view (web); 
	- Controle: Contém a fachada, com uma lista de DAOs e outra de Regras de negócio (Strategys). Os métodos de salvar, alterar, excluir, consultar e visualizar estão implementados de forma genérica, recebendo uma entidade domínio.
	- DAO: com as DAOs implementados de cada entidade domínio.
	- JDBC: com a classe de conexão com o banco de dados.
	- Negócio: com as strategys implementadas.

- Domínio: 
  - Domínio contém todas as classes de domínio implementadas que herdam de uma classe Entidade Domínio. 

- Web:
	- Command: um command para invocar cada método especificado na fachada.
	- Controle Web: contém a servlet com uma lista de cada ViewHelper e Commands.
	- ViewHelper: com a ViewHelper de cada entidade domínio;
	- Além das telas em jsp. 
  
INFORMAÇÕES ADICIONAIS:
  - Programação Orientada a Objetos;
  - Linguagem de Programação: Java;
  - Desenvolvimento Web: Java EE (Servlets, JSP) + Apache Tomcat;
  - Front-end: HTML, CSS, Bootstrap;
  - Banco de Dados: Oracle;
  - Padrão de projeto:Facade, Command, ViewHelper, DAO, Strategy;
  - Persistência: JDBC;
  - Ambiente de desenvolvimento: Eclipse; 
