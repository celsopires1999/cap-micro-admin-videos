# FC3-micro-videos-java

Esse microsserviço é parte do projeto prático do curso Full Cycle 3.0

## Ferramentas necessárias

- JDK 21
- IDE de sua preferência
- Docker

## Como executar?

1. Executar as migrações do MySQL com o Flyway:

```shell
./gradlew flywayMigrate
```

2. Executar a aplicação como SpringBoot app:

```shell
./gradlew bootRun
export $(grep -v '^#' .env | xargs) && ./gradlew bootRun
export $(grep -v '^#' .env | xargs) && ./gradlew bootRun --debug-jvm
```

3. Essa é outra opção:

```shell
GOOGLE_CLOUD_CREDENTIALS=A \
  GOOGLE_CLOUD_PROJECT=A \
  ./gradlew bootRun
```

> Também é possível executar como uma aplicação Java através do
> método main() na classe Main.java

### Migrações do banco de dados com Flyway

#### Executar as migrações

Caso seja a primeira vez que esteja subindo o banco de dados, é necessário
executar as migrações SQL com a ferramenta `flyway`.
Execute o comando a seguir para executar as migrações:

```shell
./gradlew flywayMigrate
```

Pronto! Agora sim o banco de dados MySQL está pronto para ser utilizado.

<br/>

#### Limpar as migrações do banco

É possível limpar (deletar todas as tabelas) seu banco de dados, basta
executar o seguinte comando:

```shell
./gradlew flywayClean
```

MAS lembre-se: "Grandes poderes, vem grandes responsabilidades".

<br/>

#### Reparando as migrações do banco

Existe duas maneiras de gerar uma inconsistência no Flyway deixando ele no estado de reparação:

1. Algum arquivo SQL de migração com erro;
2. Algum arquivo de migração já aplicado foi alterado (modificando o `checksum`).

Quando isso acontecer o flyway ficará em um estado de reparação
com um registro na tabela `flyway_schema_history` com erro (`sucesso = 0`).

Para executar a reparação, corrija os arquivos e execute:

```shell
./gradlew flywayRepair
```

Com o comando acima o Flyway limpará os registros com erro da tabela `flyway_schema_history`,
na sequência execute o comando FlywayMigrate para tentar migrar-los novamente.

<br/>

#### Outros comandos úteis do Flyway

Além dos comandos já exibidos, temos alguns outros muito úteis como o info e o validate:

```shell
./gradlew flywayInfo
./gradlew flywayValidate
```

Para saber todos os comandos disponíveis: [Flyway Gradle Plugin](https://flywaydb.org/documentation/usage/gradle/info)

<br/>

#### Para executar os comandos em outro ambiente

Lá no `build.gradle` configuramos o Flyway para lêr primeiro as variáveis de
ambiente `FLYWAY_DB`, `FLYWAY_USER` e `FLYWAY_PASS` e depois usar um valor padrão
caso não as encontre. Com isso, para apontar para outro ambiente basta sobrescrever
essas variáveis na hora de executar os comandos, exemplo:

```shell
FLYWAY_DB=jdbc:mysql://prod:3306/adm_videos FLYWAY_USER=root FLYWAY_PASS=123h1hu ./gradlew flywayValidate
```

#### Gerando o artefato produtivo (jar)

Para gerar o artefato produtivo, basta executar o comando:

```
./gradlew bootJar
```

#### Executando a aplicação no Sandbox

```
docker compose -f docker-compose.sandbox.yml up -d
```

#### Executando a aplicação com a imagem de Produção

```
docker compose -f docker-compose.prd.yml up -d
```

### Keycloak

#### Setup

1. Subir o container e navegar ate `http://host.docker.internal:8443/`
2. Criar um realm novo para o projeto: `fc3-codeflix`
3. Navegar ate Realm settings > General > Endpoints
   - Esses endpoints são importantes para fazer-mos a integração
4. Navegar ate Realm settings > Keys
   - Iremos utilizar a chave publica do algoritmo RS256 para verificar o token
5. Criar o client:
   - Client Id: fc3-admin-catalogo-de-videos
   - Client authentication: ON -- isso faz acesso confidential
   - Redirect URL: confidential
   - Comentar das credentials `client and secret` que usaremos para login manual
6. Criar a role:
   - Role: catalogo-admin
   - Description: Role que dá permissão de admin para os usuários
7. Criar um group:
   - Name: catalogo-admin
   - Role mapping: assign `catalogo-admin`
8. Criar um usuario:
   - Nome: myuser
   - Groups: adicionar ao `catalogo-admin`
   - Criar um credentials: `123456`
9. Criar o client para o frontend:

- Client Id: react-auth
- Client authentication: OFF -- isso faz acesso publico
- Root URL: `http://localhost:3000`
- Valid redirect URIs: `http://localhost:3000/*` -- É necessário o /\*
- Web origins: `http://localhost:3000` -- Essa propriedade evita bloqueio de CORS
- Realm Settings -> Security Defenses -> Content-Security-Policy: `frame-src 'self'; frame-ancestors 'self' http://localhost:3000;`
