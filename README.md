# desafio-java-web-BeHOH

Esta aplicação foi construída como solução para o [Desafio Java Web](https://gitlab.com/behoh/desafio-java-web/) da [BeHOH - Soluções inovadoras para eventos](https://www.behoh.com/)

#### Tecnologias:

Versão do JAVA: 11

Maven 3.8.2

Versão do SpringBoot: [2.5.4](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent/2.5.4)

Bando de dados: H2

### Como rodar

Na IDE de sua preferência, importe o projeto para seu "workspace" através da opção "importar projeto MAVEN", busque o arquivo EventmanagerApplication.java -> botão direito -> rodar como "Springboot app".

Caso prefira executar via linha de comando, abra um terminal dentro da pasta back end e execute `mvn spring-boot:run`

Os endpoints da API estarão disponíveis na porta 8080.

Obs: A Interface CommandLineRunner foi implementada para popular o banco.

### Regras gerais implementadas:

Não permitir a inscrição de usuários quando o limite de vagas for atingido;

Não permitir a inscrição de usuários após o evento ter sido iniciado;

O usuário só poderá entrar no evento no período de uma hora antes do início do evento até a hora de término do evento;

Não permitir o cancelamento de uma inscrição após o usuário ter realizado a entrada no evento.


### Endpoints dos requisitos do desafio

1 - Realizar a criação de um evento;

	POST: /events
	
2 - Realizar a criação de um usuário;

	POST: /users
	
3 - Realizar a operação de inscrição de um usuário em um evento

	POST: /subscriptions
	
4 - Realizar o cancelamento de uma inscrição de um usuário em um evento;

	PATCH: /subscriptions/cancel/{id}
	
5 - Listar as inscrições de um usuário;

	GET: /users/subscriptions/{id}
	
6 - Listar os inscritos de um evento;

	GET: /events/{id}/subscriptions
	
7 - Realizar entrada do usuário no evento;

	PATCH: /subscriptions/checkin/{id}
	
8 - Implementar operação de criação de reservas;
	
	POST: subscriptions/reservation

9 - Realizar conversão de reservas em inscrições

	PATCH: /subscriptions/confirm
	
### Documentação da API

A documentação da API está disponível em https://documenter.getpostman.com/view/17256075/UUxtDqHd



