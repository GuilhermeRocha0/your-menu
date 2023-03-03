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
  "name": "Main Dishes",
  "items": {}
}
```

**Campos de Requisição**
| Campo | Obrigatório | Tipo | Descrição |
|-------|:-------------:|-------|-----------|
| id | sim | int | O id da categoria para os itens
| name | sim | texto | O nome da categoria
| items | sim | object | Onde os itens serão armazenados

### Listar Categorias

`GET` yourmenu/api/v1/categories

**Exemplo de Saida**

```js
{
  {
    "id": 1,
    "name": "Main Dishes",
    "items": {}
  },
  {
    "id": 2,
    "name": "Entrees",
    "items": {}
  }
}
```

### Visualizar Categoria

`GET` yourmenu/api/v1/categories/{id}

**Exemplo de Saida**

```js
{
  "id": 1,
  "name": "Main Dishes",
  "items": {}
}
```

### Editar Categorias

`PUT` yourmenu/api/v1/categories/{id}

**Exemplo de Entrada**

```js
{
  "id": 1,
  "name": "Main dishes",
  "items": {}
}
```

**Campos de Requisição**
| Campo | Obrigatório | Tipo | Descrição |
|-------|:-------------:|-------|-----------|
| id | sim | int | O id da categoria para os itens
| name | sim | texto | O nome da categoria
| items | sim | object | Onde os itens serão armazenados

### Excluir Categoria

`DELETE` yourmenu/api/v1/categories/{id}

### Cadastro de Itens

`POST` yourmenu/api/v1/categories/{id}/items

**Exemplo de Entrada**

```js
{
  "id": 1,
  "name": "Main dishes",
  "items": {
    "id": 1,
    "name": "Item 1",
    "price": 10.00,
    "description": "Description of Item 1",
    "image": url,
    "daysOfTheWeek": [0,1,2]
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

### Listar Itens

`GET` yourmenu/api/v1/categories/{id}/items

**Exemplo de Saida**

```js
{
  {
    "id": 1,
    "name": "Item 1",
    "price": 10.00,
    "description": "Description of Item 1",
    "image": url,
    "daysOfTheWeek": [0,1,2]
  },
  {
    "id": 2,
    "name": "Item 2",
    "price": 15.00,
    "description": "Description of Item 2",
    "image": url,
    "daysOfTheWeek": [0,2,4]
  }
}
```

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
  "daysOfTheWeek": [0,1,2]
}
```

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
  "daysOfTheWeek": [0,1,2]
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

### Excluir Item

`DELETE` yourmenu/api/v1/categories/{id}/items/{id}
