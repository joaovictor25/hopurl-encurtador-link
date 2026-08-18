# HopURL

HopURL é um encurtador de links desenvolvido com Spring Boot. O projeto está em desenvolvimento, porém já permite gerar URLs encurtadas e redirecionar para o endereço original por meio do código criado.

## Demostração
<img src="https://www.axionweb.com.br/gif-hopurl-tela.gif" width="35" title="HopUrl"/>

## Visão Geral

O sistema recebe uma URL original, gera um identificador curto baseado em hash e armazena o relacionamento em banco de dados. Depois disso, o link encurtado pode ser acessado diretamente para redirecionamento.

### Principais recursos

- Geração de URL encurtada a partir de uma URL original.
- Redirecionamento automático para a URL cadastrada.
- Interface web simples para envio da URL.
- Persistência com banco H2 em memória.
- Console H2 habilitado para inspeção durante o desenvolvimento.

## Status do Projeto

Este projeto está em desenvolvimento.

Funcionalidade já disponível:

- gerar a URL encurtada;
- armazenar o vínculo entre código e URL original;
- acessar o link encurtado para redirecionamento.

Funcionalidades futuras podem incluir autenticação, histórico de links, métricas de acesso, expiração de URLs e personalização do código gerado.

## Tecnologias Utilizadas

- Java 25
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- H2 Database
- Maven

## Estrutura do Projeto

- `src/main/java/br/com/axionweb/hopurl/`
  - `controller` - endpoints da aplicação.
  - `service` - regras de negócio para geração e busca dos links.
  - `repository` - acesso aos dados.
  - `model` - entidade persistida no banco.
- `src/main/resources/static/index.html` - interface web para encurtar links.
- `src/main/resources/application.properties` - configurações da aplicação e do H2.

## Como Funciona

1. O usuário informa uma URL válida na interface web.
2. A aplicação envia a URL para o endpoint `POST /api/links`.
3. O serviço gera um código curto com base em SHA-256 e conversão para Base62.
4. O código e a URL original são salvos no banco.
5. A aplicação retorna a URL encurtada.
6. Ao acessar `/{codigo}`, a aplicação localiza a URL original e redireciona o usuário.

## Endpoints

### `POST /api/links`

Cria uma URL encurtada.

#### Exemplo de requisição

```json
{
  "urlOriginal": "https://exemplo.com/pagina"
}
```

#### Resposta

Retorna a URL encurtada em texto simples.

Exemplo:

```text
localhost:8080/abc123X
```

### `GET /{code}`

Redireciona para a URL original associada ao código informado.

Exemplo:

```text
http://localhost:8080/abc123X
```

## Interface Web

A aplicação disponibiliza uma página inicial estática em `http://localhost:8080/` com um formulário simples para informar a URL e gerar o link encurtado.

## Banco de Dados

O projeto utiliza H2 em memória, configurado para uso durante o desenvolvimento.

### Console H2

O console do banco está habilitado em:

```text
/h2
```

As credenciais definidas no projeto são:

- usuário: `sa`
- senha: vazia

## Como Executar

### Pré-requisitos

- Java 25 instalado
- Maven instalado, ou uso do wrapper `mvnw`

### Execução local

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
mvnw.cmd spring-boot:run
```

Depois, acesse:

- `http://localhost:8080/` para a interface;
- `http://localhost:8080/h2` para o console do banco.

## Observações Técnicas

- O código encurtado é gerado a partir da URL original.
- O sistema salva o registro da URL em banco para permitir o redirecionamento posterior.
- Como o banco configurado é em memória, os dados são perdidos ao reiniciar a aplicação.

## Próximos Passos

Algumas evoluções naturais para o projeto são:

- persistência em banco definitivo;
- tratamento de erros mais completo;
- validação aprimorada de URLs;
- testes automatizados para controller, service e repository;
- interface com melhor experiência para o usuário;
- métricas de acesso e gerenciamento dos links.
