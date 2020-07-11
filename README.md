# Lab4 - Laboratório de Banco de Dados IV

## Atividade 4
### Requisitos
 1. Permita buscar um registro no Banco de Dados (BD) pelo seu ID por meio de método GET. O retorno deve ser no formato JSON;
 
 2. Permita inserção de registros em um BD por meio de método POST. O novo registro deve ser recebido no corpo da requisição com formato JSON. Se a requisição for processada com sucesso o código de retorno deve ser 201 (CREATED) e a resposta deve conter um cabeçalho (header) chamado "Location" contendo a URL para o serviço criado no item 1 e o ID do registro criado;
 
 3. Permita alterar o conteúdo de um registro do BD por meio de método PUT ou PATCH;
 
 4. Permita excluir um registro por meio do método DELETE; 
 
 5. Utilize autenticação básica (envio de usuário e senha em toda requisição) implementada com Filter e proíba a usuário sem autorização do tipo "ADMIN" o acesso aos serviços dos itens 3 e 4.


### Resolução
 - Requisito 1: Criado rota GET ```/cliente?id=``` para busca de cliente por ID. Este endpoint retorna 200 ou 404 dependendo do item ter sido encontrado ou não.
 
 - Requisito 2: Criado rota POST ```/cliente?tipo=?``` para Criação de recurso. É retornado um Header Location com o endereço do recurso.
Variável 'tipo' pode ser: ```pf``` ou ```pj```

 - Requisito 3: Criado rota PUT  ```/cliente``` para atualizar um objeto no banco de dados. Retorna 204 se o item foi atualizado, ou 500 caso não consiga atualizar, ou o item não foi encontrado.

 - Requisito 4: Criado rota DELETE ```/cliente?id=``` para deletar um recurso. É retornado 204 caso o item seja deletado, ou 404 caso o item não seja encontrado.
 
 
## Integrantes
-Andre

-Bruno

-Gabriel

-Leticia

-Vanessa

## Data de Entrega: até 14/07/2020
