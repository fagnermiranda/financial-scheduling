# Sistema de Agendamento de Transferências Financeiras.

### Frameworks

Os seguintes frameworks foram usadas na construção do projeto:

- Spring Boot facilita a criação de aplicativos baseados em Spring. 
- Spring Boot Starter Validation para customizar as mensagens de validação.
- Spring Data JPA para implementar repositórios baseados em JPA.
- H2 Data Base para criar um banco de dados em memória. 
- Junit para criar os testes unitários. 
- Maven para gerenciar as dependências. 
- Model Mapper para mapear um objeto para o outro. Exemplo: DTO para entidade e entidade para DTO. 

### Patterns
O design pattern utilizado no projeto foi o Strategy para calcular a taxa do tipo de transferência de agendamento. \
Na implementação criei uma enumeração que define as estratégias para os tipos de agendamentos A, B e C.

### Versões
- Spring Boot 2.5.6
- Java 17 
- Model Mapper 2.4.2

### Instruções
- Fazer clone do projeto no git. \
`git clone https://github.com/fagnermiranda/financial-scheduling.git`

- Importar a pasta financial-scheduling no Spring Tool Suite. \
 File >  Import > Maven > Existing Maven Projects. 
- Execute a classe FinancialSchedulingApplication para subir o projeto.  
- Execute a TransferSchedulingServiceImplTest para fazer os testes de unidade. 
