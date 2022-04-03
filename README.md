# Rest API para sistema de gerenciamento de ativos de uma empresa

Api desenvolvida utilizando o [Ecossistema Spring](https://spring.io/) em conjunto
com [Jakarta Persistence JPA](https://jakarta.ee/specifications/persistence/)
, [Jakarta Bean Validation](https://jakarta.ee/specifications/bean-validation/), [ModelMapper](http://modelmapper.org/)
e [Flyway](https://flywaydb.org/).

Recursos da API (endpoints):

1 - Usuários - /users;<br>
2 - Empresas - /companies;<br>
3 - Equipamentos - /equipments;<br>
4 - Setores - /sectors;<br>
5 - Tipos de equipamentos - /types;<br>
6 - Autenticação (Login) - /auth/login;<br>
7 - Logout - /auth/logout.<br>

Todos os endoins com exceção de /auth/login e /auth/logout suportam as operações CRUD.<br>

Operação | Método
:---------:|:-------:
CREATE   | POST
READ     | GET
UPDATE   | PUT
DELETE   | DELETE

## Configuração de tokens

É possível configurar a duração em tempo dos tokens gerados e a quantidade máxima de tokens por usuário. 
Para realizar essas configurações, é necessário acessar o arquivo
[application.properties](src/main/resources/application.properties) e alterar respectivamente as propriedades:<br><br>
api.token.expirationtime<br>
api.token.maxtokensbyuser<br><br>
OBS: A propriedade api.token.expirationtime será convertida em um atributo do tipo Duration, 
o que significa que é necessário especificar após o valor, um sufixo de tempo que pode ser: ns, us, ms, s, m, h e d para nanossegundos, microssegundos, milissegundos, 
segundos, minutos, horas e dias, respectivamente.<br><br>
A unidade padrão é milissegundos, o que significa que se não for especificado uma unidade 
ao lado do valor numérico, o Spring converterá o valor em milissegundos.<br>


## Authenticação

Para consumir a API, primeiro é necessário logar no endpoint /auth/login 
passando um corpo contendo login e senha com o abaixo:<br>

  ```JSON
  {
  "login": "fernando",
  "password": "minhasenha123456"
}
  ```

O retorno será algo como:<br>

  ```JSON
{
  "id": 96,
  "token": "TOKEN_GERADO",
  "expirationTime": "2022-04-03T01:38:33.706536919"
}
```
O token retornado no corpo da requisição deverá ser passado no cabeçalho "Authorization" 
de todas as outras requisições e tem duração de 30 minutos.<br>

## Uso de endpoints

A usabilidade dos endpoins seguem o mesmo padrão, abaixo é possível ver o exemplo de consumo do recurso "Usuários".

### Usuários

- Listar os usuários (GET localhost:8080/users);
- Obter um usuário (GET localhost:8080/users/{userId});
- Adicionar um usuário (POST localhost:8080/users);<br>
  Exemplo de corpo:
  ```JSON
  Authentication: {TOKEN_GERADO_NA_ETAPA_DE_AUTENTICAÇÃO}
  {
    "name": "Fernando",
    "email": "fernando@gmail.com",
    "login": "nando2000",
    "password": "e10adc3949ba59abbe56e057f20f883e"
  }
  ```
- Atualizar um usuário (PUT localhost:8080/users/{userId});<br>
  Exemplo de corpo:
  ```JSON
  Authentication: {TOKEN_GERADO_NA_ETAPA_DE_AUTENTICAÇÃO}
  {
    "name": "Fernando Souza",
    "email": "fernando1@gmail.com",
    "login": "fernando2000",
    "password": "e10adc3949ba59abbe56e057f20f883e"
  }
  ```
- Excluir um usuário (DELETE localhost:8080/users/{userId}).