# Control-More
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
