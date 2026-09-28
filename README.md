# ERP/CRM para Assistencia Social

API REST para apoio ao acompanhamento de pessoas assistidas, desenvolvida como projeto de estudo com Java e Spring Boot. O foco atual esta no backend: cadastro de assistidos, dependentes, historico de visitas, rotas, planos de acao, usuarios do sistema e envio de chamados de suporte.

O repositorio tambem contem um diretorio `frontend/`, mas a interface React ainda nao possui implementacao funcional. A aplicacao utilizavel neste momento e a API backend.

## Estado da arquitetura

O backend usa uma arquitetura em camadas, organizada nos pacotes abaixo:

- `controller`: endpoints HTTP da API.
- `service`: casos de uso e regras de negocio.
- `model`: entidades JPA e regras proximas ao dominio.
- `repository`: persistencia com Spring Data JPA.
- `dto` e `mapper`: contratos de entrada/saida e conversao com MapStruct.
- `configuration` e `exception`: seguranca, inicializacao e tratamento de erros.

O projeto possui algumas regras de dominio encapsuladas nas entidades, como o calculo de idade de dependentes e o controle de periodos ativos de assistencia. Isso representa uma aproximacao de DDD para estudo, mas a estrutura atual nao e uma implementacao completa de DDD: os modulos ainda sao organizados principalmente por camada, sem bounded contexts ou arquitetura de portas e adaptadores.

Os testes foram introduzidos e ampliados durante a evolucao do projeto. Ha testes de modelos, servicos, controllers, configuracao de seguranca e tratamento global de excecoes. Portanto, o repositorio usa testes automatizados e exercita praticas de TDD, mas nao afirma que todo o historico de desenvolvimento tenha seguido estritamente o ciclo red-green-refactor.

## Funcionalidades implementadas

- Cadastro, consulta, atualizacao e exclusao de assistidos (`User`).
- Dependentes vinculados ao assistido, com idade calculada a partir da data de nascimento.
- Endereco, renda, status, observacoes, foto e habilidades do assistido.
- Periodos de assistencia, com impedimento de mais de um periodo ativo por assistido.
- Registro de historico de visitas.
- Cadastro e gerenciamento de rotas de visita e suas paradas.
- Planos de acao por assistido.
- Gestao de usuarios do sistema.
- Envio de chamados de suporte por SMTP, com `replyTo` opcional.
- Documentacao OpenAPI/Swagger, console H2 no perfil de desenvolvimento e tratamento padronizado de erros da API.

## Tecnologias

- Java 21
- Spring Boot 3.0.12
- Spring Web, Validation, Data JPA, Security e Mail
- H2 para desenvolvimento e PostgreSQL para producao
- MapStruct e Lombok
- Springdoc OpenAPI/Swagger
- JUnit 5, Mockito e Spring Security Test
- Docker e Docker Compose

## Endpoints principais

Os endpoints abaixo exigem autenticacao HTTP Basic, exceto a documentacao da API e o console H2 no ambiente de desenvolvimento.

| Recurso | Base URL |
| --- | --- |
| Assistidos | `/api/users` |
| Historico de visitas | `/api/visit-history` |
| Rotas de visita | `/api/visit-routes` |
| Planos de acao | `/api/users/{userId}/action-plans` |
| Usuarios do sistema | `/api/system-users` |
| Chamados de suporte | `POST /api/support/ticket` |

Com a aplicacao em execucao, a interface do Swagger fica em `http://localhost:8080/swagger-ui/index.html` e o console H2 em `http://localhost:8080/h2-console` no perfil `dev`.

## Executar localmente

Prerequisitos: JDK 21. O Maven Wrapper ja esta incluido no repositorio. O perfil `dev` atual le `MAIL_USERNAME` e `MAIL_PASSWORD` ao montar o contexto Spring; em uma maquina local onde essas variaveis nao estiverem configuradas, informe valores apropriados para executar a aplicacao completa ou a suite de testes.

```bash
export MAIL_USERNAME=seu-email@gmail.com
export MAIL_PASSWORD=sua-senha-de-aplicativo
./mvnw spring-boot:run
```

Por padrao, a aplicacao inicia com o perfil `dev`, usando H2 em memoria. Na primeira inicializacao, o administrador e criado com os valores de `ADMIN_INITIAL_USERNAME` e `ADMIN_INITIAL_PASSWORD` (ou os valores padrao da aplicacao quando essas variaveis nao forem informadas).

Para executar a suite de testes:

```bash
export MAIL_USERNAME=seu-email@gmail.com
export MAIL_PASSWORD=sua-senha-de-aplicativo
./mvnw test
```

## Executar com Docker e PostgreSQL

1. Crie o arquivo de configuracao a partir do exemplo:

   ```bash
   cp .env.example .env
   ```

2. Preencha pelo menos `DB_USER`, `DB_PASSWORD`, `ADMIN_USERNAME` e `ADMIN_PASSWORD` no arquivo `.env`.

3. Suba os servicos:

   ```bash
   docker compose up --build
   ```

O container do backend inicia com o perfil `prod` e se conecta ao PostgreSQL do Compose. Nunca versione o arquivo `.env` com credenciais reais.

## Configuracao por ambiente

- `application.properties`: configuracoes comuns e selecao de perfil.
- `application-dev.properties`: H2 em memoria, console H2 e configuracoes locais.
- `application-prod.properties`: conexao PostgreSQL e CORS configuravel.

Variaveis de ambiente relevantes:

- `SPRING_PROFILES_ACTIVE`: perfil ativo; o padrao e `dev`.
- `SPRING_DATASOURCE_URL`, `DB_USER` e `DB_PASSWORD`: conexao com PostgreSQL em producao.
- `ADMIN_INITIAL_USERNAME` e `ADMIN_INITIAL_PASSWORD`: valores do administrador inicial quando a aplicacao e executada diretamente.
- `ADMIN_USERNAME` e `ADMIN_PASSWORD`: variaveis usadas pelo `docker-compose.yml`, que as repassa ao backend como `ADMIN_INITIAL_USERNAME` e `ADMIN_INITIAL_PASSWORD`.
- `MAIL_USERNAME` ou `MAIL_USER_NAME`, e `MAIL_PASSWORD`: credenciais SMTP.
- `CORS_ALLOWED_ORIGINS`: lista separada por virgulas dos frontends autorizados.

## Testes e proximos passos

Os testes automatizados cobrem partes importantes da aplicacao, incluindo regras de dominio, servicos, controllers, seguranca e respostas de erro. Em ambiente local sem configuracao SMTP, alguns testes que carregam o contexto completo nao iniciam. Mesmo com essas variaveis informadas, `SupportControllerTest` ainda precisa receber ou simular um `JavaMailSender` no contexto de teste. A evolucao natural do projeto e ampliar a cobertura antes de novas funcionalidades, implementar o frontend e, caso a adocao de DDD seja aprofundada, reorganizar o codigo por contextos de negocio.
