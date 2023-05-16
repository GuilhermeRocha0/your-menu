# your-menu

## Endpoints

- [Cadastro de Categorias](#cadastro-de-categorias)
- [Listar Categorias](#listar-categorias)
- [Visualizar Categoria](#visualizar-categoria)
- [Editar Categorias](#editar-categorias)
- [Excluir Categoria](#excluir-categoria)
- [Cadastro de Itens](#cadastro-de-itens)
- [Listar Itens](#listar-itens)
- [Visualizar Item](#visualizar-item)
- [Editar Itens](#editar-itens)
- [Excluir Item](#excluir-item)

### Cadastro de Categorias

`POST` yourmenu/api/v1/categories

**Exemplo de Entrada**
```js
{
  "id": 1,
  "name": "Category 1",
  "items": []
}
```

**Campos de Requisição**
| Campo | Obrigatório | Tipo | Descrição |
|-------|:-------------:|-------|-----------|
| id | sim | int | O id da categoria para os itens
| name | sim | texto | O nome da categoria
| items | sim | object | Onde os itens serão armazenados

**Códigos da Resposta**
|código|descrição
|-|-
201 | a categoria foi cadastrada com sucesso
400 | os dados enviados são inválidos



### Listar Categorias

`GET` yourmenu/api/v1/categories

**Exemplo de Saida**
```js
[
  {
    "id": 1,
    "name": "Category 1",
    "items": []
  },
  {
    "id": 2,
    "name": "Category 2",
    "items": []
  }
]
```

**Códigos da Resposta**
|código|descrição
|-|-
200 | os dados das categorias foram retornados
404 | não existe categorias



### Visualizar Categoria

`GET` yourmenu/api/v1/categories/{id}

**Exemplo de Saida**
```js
{
  "id": 1,
  "name": "Category 1",
  "items": []
}
```

**Códigos da Resposta**
|código|descrição
|-|-
| 200 | os dados da categoria foram retornados
| 404 | não existe categoria com o ID informado


### Editar Categorias

`PUT` yourmenu/api/v1/categories/{id}

**Exemplo de Entrada**
```js
{
  "id": 1,
  "name": "Category 1",
  "items": []
}
```

**Campos de Requisição**
| Campo | Obrigatório | Tipo | Descrição |
|-------|:-------------:|-------|-----------|
| id | sim | int | O id da categoria para os itens
| name | sim | texto | O nome da categoria
| items | sim | object | Onde os itens serão armazenados

**Códigos da Resposta**
|código|descrição
|-|-
| 200 | os dados da categoria foram atualizados
| 404 | não existe categoria com o ID informado



### Excluir Categoria

`DELETE` yourmenu/api/v1/categories/{id}

**Códigos da Resposta**
|código|descrição
|-|-
| 200 | os dados da categoria foram excluídos
| 202 | os dados da categoria foram encontrados mas não ainda excluídos
| 204 | houve a ação de excluir mas os dados da categoria não foram encontrados



### Cadastro de Itens

`POST` yourmenu/api/v1/categories/{id}/items

**Exemplo de Entrada**
```js
{
  "id": 1,
  "name": "Item 1",
  "price": 10.00,
  "description": "Description of Item 1",
  "image": url,
  "daysOfTheWeek": [0,1,2],
	"category": {
		"id": 1,
		"name": "Category 1"
	}
}
```

**Campos de Requisição**
| Campo | Obrigatório | Tipo | Descrição |
|-------|:-------------:|-------|-----------|
| id | sim | int | O id do item
| name | sim | texto | O nome do item do cardápio
| price | sim | double | O preco do item
| description | não | texto | A descrição do item do cardápio
| image | não | texto | O envio de um arquivo de imagem
| daysOfTheWeek | não | array | Um array de números dos dias da semana

**Códigos da Resposta**
|código|descrição
|-|-
201 | o item foi cadastrada com sucesso
400 | os dados enviados são inválidos



### Listar Itens

`GET` yourmenu/api/v1/items

**Exemplo de Saida**
```js
[
  {
    "id": 1,
    "name": "Item 1",
    "price": 20.00,
    "description": null,
    "image": url,
    "daysOfTheWeek": [0,1,2,3,4,5,6],
    "category": {
      "id": 1,
      "name": "Category 1"
    }
  },
  {
    "id": 2,
    "name": "Item 2",
    "price": 15.00,
    "description": "Description of Item 2",
    "image": url,
    "daysOfTheWeek": [0,2,4],
    "category": {
      "id": 1,
      "name": "Category 1"
    }
  }
]
```

**Códigos da Resposta**
|código|descrição
|-|-
200 | os dados dos itens foram retornados
404 | não existe itens nessa categoria


### Listar Itens da Categoria

`GET` yourmenu/api/v1/categories/{id}/items

**Exemplo de Saida**
```js
[
  {
    "id": 1,
    "name": "Item 1",
    "price": 10.00,
    "description": "Description of Item 1",
    "image": url,
    "daysOfTheWeek": [0,1,2],
    "category": {
      "id": 1,
      "name": "Category 1"
    }
  },
  {
    "id": 2,
    "name": "Item 2",
    "price": 15.00,
    "description": "Description of Item 2",
    "image": url,
    "daysOfTheWeek": [0,2,4]],
    "category": {
      "id": 1,
      "name": "Category 1"
    }
  }
]
```

**Códigos da Resposta**
|código|descrição
|-|-
200 | os dados dos itens foram retornados
404 | não existe itens nessa categoria



### Visualizar Item

`GET` yourmenu/api/v1/categories/{id}/items/{id}

**Exemplo de Saida**
```js
{
  "id": 1,
  "name": "Item 1",
  "price": 10.00,
  "description": "Description of Item 1",
  "image": url,
  "daysOfTheWeek": [0,1,2],
  "category": {
    "id": 1,
    "name": "Category 1"
  }
}
```

**Códigos da Resposta**
|código|descrição
|-|-
| 200 | os dados do item foram retornados
| 404 | não existe item com o ID informado nessa categoria



### Editar Itens

`PUT` yourmenu/api/v1/categories/{id}/items/{id}

**Exemplo de Entrada**
```js
{
  "id": 1,
  "name": "Item 1",
  "price": 10.00,
  "description": "Description of Item 1",
  "image": url,
  "daysOfTheWeek": [0,1,2],
  "category": {
    "id": 1,
    "name": "Category 1"
  }
}
```

**Campos de Requisição**
| Campo | Obrigatório | Tipo | Descrição |
|-------|:-------------:|-------|-----------|
| id | sim | int | O id do item
| name | sim | texto | O nome do item do cardápio
| price | sim | double | O preco do item
| description | não | texto | A descrição do item do cardápio
| image | não | texto | O envio de um arquivo de imagem
| daysOfTheWeek | não | array | Um array de números dos dias da semana

**Códigos da Resposta**
|código|descrição
|-|-
| 200 | os dados do item foram atualizados
| 404 | não existe item com o ID informado



### Excluir Item

`DELETE` yourmenu/api/v1/categories/{id}/items/{id}

**Códigos da Resposta**
|código|descrição
|-|-
| 200 | os dados do item foram excluídos
| 202 | os dados do item foram encontrados mas não ainda excluídos
| 204 | houve a ação de excluir mas os dados do item não foram encontrados
