<div align="center">

![Star Wars](https://github.com/lucasbsimao/StarWarsAPI/blob/master/images/star-wars.jpeg "Star Wars")

</div>


[![Build Status](https://travis-ci.org/lucasbsimao/purchaseapp.svg?branch=master)](https://travis-ci.org/lucasbsimao/StarWarsAPI)

# StarWarsAPI

### 1. Conceito

Esta API tem como propósito realizar buscas, inclusão e remoção de planetas do universo Star Wars. Estando de acordo com as informações do universo Star Wars original, a API retorna também o número de aparições nos filmes.

### 2. Dependências

Para executar este projeto são necessárias as seguintes dependências instaladas:

* [Java SDK 8](https://www.oracle.com/technetwork/java/javaee/downloads/java-ee-sdk-downloads-3908423.html);
* [Eclipse com Spring Tools Suite 3](https://www.eclipse.org/downloads/);
* [Mongo DB](https://docs.mongodb.com/manual/installation/)
* Todas as configurações para funcionamento correto da API são feitas pela aplicação, incluindo a criação do banco no Mongo DB;

Há também a necessidade que o [SwAPI](https://swapi.co/) esteja online, do contrário a api não conseguirá acessá-lo.

### 3. Testando a API

A documentação padrão da aplicação é fornecida pelo [Swagger](https://swagger.io/). Assim, uma vez que a API estiver online, a documentação estará disponível no endpoint ``` /docApi/swagger-ui.html ```, como é visto abaixo:

<div align="center">

![Swagger](https://github.com/lucasbsimao/StarWarsAPI/blob/master/images/swagger.png "Swagger")

</div>

O Swagger também permite fazer testes de requisição. Porém outras aplicações também podem ser utilizadas, como o [Postman](https://www.getpostman.com/).

#### 3.1 Lista de funcionalidades

1. Criando um planeta:

Endpoint: /planets/

```JSON
{
    "name": "Alderaan",
    "climate": "temperate",
    "terrain": "terrain": "grasslands, mountains"
}
```

2. Deletando um planeta:

Endpoint: /planets/{id}

3. Listar todos os planetas

Endpoint: /planets/

4. Buscar planeta por id:

Endpoint: /planets/{id}

5. Buscar planeta por nome:

Endpoint: /planets/name/{name}