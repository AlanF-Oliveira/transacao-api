# API de Transações

Uma API REST para gerenciar transações financeiras e gerar estatísticas em tempo real.

## Descrição

A Transação API é uma aplicação Spring Boot construída com Java 21 que permite:
- Registrar transações com valor e data/hora
- Calcular estatísticas de transações (contagem, soma, média, mínimo e máximo)
- Consultar métricas em intervalos customizáveis
- Explorar a API através do Swagger UI

## Tecnologias

- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.5.16
- **Build Tool**: Gradle
- **Documentação**: SpringDoc OpenAPI (Swagger UI)
- **Testing**: JUnit 5
- **Monitoramento**: Spring Boot Actuator
- **Dependency Injection**: Lombok

## Requisitos

- Java 21 ou superior
- Gradle 8.0+ (ou usar o gradlew incluído)

## Como Executar

### 1. Clone o repositório
```bash
git clone https://github.com/AlanF-Oliveira/transacao-api.git
cd transacao-api
```

### 2. Execute a aplicação
```bash
./gradlew bootRun
```

A API estará disponível em `http://localhost:8080`

### 3. Acesse a documentação Swagger
```
http://localhost:8080/swagger-ui.html
```

## Build

Para compilar o projeto:
```bash
./gradlew build
```

## Testes

Para executar todos os testes:
```bash
./gradlew test
```

Testes disponíveis:
- `TransacaoServiceTest` - Testes da camada de serviço de transações
- `EstatisticasServiceTest` - Testes da camada de serviço de estatísticas
- `TransacaoControllerTest` - Testes do controller de transações
- `EstatisticaControllerTest` - Testes do controller de estatísticas

## Endpoints

### Transações

#### Adicionar Transação
```http
POST /transacao
Content-Type: application/json

{
  "valor": 150.50,
  "dataHora": "2024-07-01T10:30:00-03:00"
}
```

**Respostas:**
- `201 Created` - Transação gravada com sucesso
- `400 Bad Request` - Erro de requisição
- `422 Unprocessable Entity` - Campos não atendem os requisitos
- `500 Internal Server Error` - Erro interno

#### Limpar Transações
```http
DELETE /transacao
```

**Respostas:**
- `200 OK` - Transações deletadas com sucesso
- `400 Bad Request` - Erro de requisição
- `500 Internal Server Error` - Erro interno

### Estatísticas

#### Obter Estatísticas
```http
GET /estatistica?intervaloBusca=60
```

**Parâmetros:**
- `intervaloBusca` (opcional, padrão: 60) - Intervalo de tempo em segundos para buscar transações

**Resposta:**
```json
{
  "count": 10,
  "sum": 1500.50,
  "avg": 150.05,
  "min": 50.00,
  "max": 300.00
}
```

**Respostas:**
- `200 OK` - Busca efetuada com sucesso
- `400 Bad Request` - Erro na busca de estatísticas
- `500 Internal Server Error` - Erro interno

## Actuator Endpoints

A aplicação expõe os seguintes endpoints de monitoramento:

- `GET /actuator/health` - Status da aplicação
- `GET /actuator/info` - Informações da aplicação
- `GET /actuator/metrics` - Métricas da aplicação
- `GET /actuator/swagger-ui.html` - Documentação Swagger

## Arquitetura

A aplicação segue o padrão MVC com camadas bem definidas:

```
src/main/java/com/alan/transacao_api/
├── controller/          # Controllers REST
├── service/             # Lógica de negócio
├── dto/                 # Data Transfer Objects
├── exception/           # Tratamento de exceções
└── TransacaoApiApplication.java
```

## Estrutura de Dados

### TransacaoRequestDTO
```java
public record TransacaoRequestDTO(
    Double valor,
    OffsetDateTime dataHora
)
```

### EstatisticasResponseDTO
```java
public record EstatisticasResponseDTO(
    Long count,      // Quantidade de transações
    Double sum,      // Soma dos valores
    Double avg,      // Média dos valores
    Double min,      // Valor mínimo
    Double max       // Valor máximo
)
```

## Docker

Para executar a aplicação em um container Docker:

```bash
docker build -t transacao-api .
docker run -p 8080:8080 transacao-api
```

## Exemplo de Uso

```bash
# Adicionar uma transação
curl -X POST http://localhost:8080/transacao \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 250.00,
    "dataHora": "2024-07-01T14:30:00-03:00"
  }'

# Obter estatísticas dos últimos 60 segundos
curl http://localhost:8080/estatistica?intervaloBusca=60

# Limpar todas as transações
curl -X DELETE http://localhost:8080/transacao
```

## Tratamento de Erros

A aplicação possui um manipulador global de exceções (`GlobalExceptionHandler`) que trata:
- `UnprocessableEntity` - Dados inválidos na requisição
- Erros de validação
- Erros internos do servidor

## Métricas e Monitoramento

A aplicação integra-se com Spring Boot Actuator para fornecer:
- Health checks
- Informações da aplicação
- Métricas de performance
- Logs de acesso

## Licença

Este projeto faz parte do programa Javanauta.

## Autor

Alan F. Oliveira

## Suporte

Para questões ou problemas, abra uma issue no repositório GitHub.

---

Desenvolvido usando Spring Boot
