
# REACTIVE API COM WEB FLUX

## STACK 

* Spring boot
* Aplicação Reativa com: 
  * API -> WebFlux
  * Conexão com banco -> R2DBC
* Postgresql e H2 
* Auditoria > criação e atualização das entidades
* Validation
 

#### Decisões técnicas

* WebFlux
  > Possibilita a criação de aplicações reativas. Diferentemente das aplicações Servlet 
  > as aplicações reativas não tem um contrato síncrono e quando há múltiplas requisições
  > disparadas ao mesmo tempo para o servidor, nenhuma requisição é bloqueada enquanto
  > a que está em execução não é finalizada, todas são executadas de forma simultâneas.
* R2DBC
  > Conexão de forma async com o banco de dados necessária para utilizar essa abordagem reativa.
* Utilização de 2 bancos de dados diferentes (PostgresSQL e H2)
  > A utilização do banco PostgresSQL é a principal para o ambiente de desenvolvimento e 
  > produção, utilizando o H2 (Banco em memória) apenas para os testes de integração.
* Utilizar classe de Configuração 
  > Por que ter uma classe de configurações de Beans para informar manualmente as injeções de 
  > dependências que um componente deve utilizar, sendo que o próprio Spring realiza essa 
  > configuração automaticamente? Ter uma classe de configuração permite centralizar essa
  > essa informação melhorando a organização e gerenciamento, além de permitir que o Spring
  > saiba quais classes deve injetar, evitando assim deduções em tempo de execução que pode 
  > gerar injeções cíclicas. Ter uma classe que permite informar quais injeções serão adicionadas
  > permite também uma melhor forma de escrever testes unitários, podendo injetar dependências 
  > falsas (Mocks) para simular comportamentos das classes. 
* Uso de DTOs 
  > A utilização de DTO evita a necessidade de passar classes da camada de domínio evitando expor
  > informações desnecessárias, com o uso de DTOs conseguimos criar objetos personalizados para 
  > transferir apenas as informações necessárias para cada operação.
* Validation
  > Realizar validações dos dados da requisição antes mesmo de carregar as camadas mais internas da 
  > da aplicação, evitando assim várias injeções de dependências caso alguma informação preenchida
  > na requisição esteja invalida.

### Como executar a aplicação na sua maquina

##### Ambiente Local

Clonar repositório git<br/>
Construir o projeto: <br/>
`./mvnw clean package` <br/>
Executar: <br/>
`java -jar place-service/target/place-service-0.0.1-SNAPSHOT.jar` <br/>
A API poderá ser acessada em localhost:8080 🚀 <br/>


##### DOCKER

Clonar repositório git <br/>
Construir o projeto: <br/>
`./mvnw clean package` <br/>
Construir a imagem: <br/>
`./mvnw spring-boot:build-image` <br/>
Executar o container: <br/>
`docker run --name place-service -p 8080:8080  -d place-service:0.0.1-SNAPSHOT` <br/>
A API poderá ser acessada em localhost:8080 🚀 
