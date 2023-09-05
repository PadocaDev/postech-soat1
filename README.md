![postech.png](src%2Fmain%2Fresources%2Fimages%2Fpostech.png)



# Objetivo
Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a
expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser
caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um
hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida.
O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será
preparado corretamente. Sem um sistema de controle de pedidos, pode haver confusão entre os
atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos
podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a
perda de negócios.

Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa
atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada.
Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em cliente insatisfeitos e
impactando os negócios de forma negativa.

Dessa forma, o objetivo do projeto do curso é desenvolver um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e
interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um
atendente, com funcionalidades de pagamento, criação, acompanhamento e entrega do pedido.

# Descrição
Projeto desenvolvido em Java 20 e Spring Framework, com banco de dados MySQL, Docker para
conteinerização, Maven para gerenciamento de dependências e utilização de JUnit nos testes.


# Entregáveis

<details>
  <summary> Primeira entrega</summary>

1. [Documentação do sistema (DDD) utilizando a linguagem ubíqua](https://miro.com/app/board/uXjVMJITQpk=/?share_link_id=811300643755), dos seguintes fluxos:
    1. Realização do pedido e pagamento
    2. Preparação e entrega do pedido
2. Uma aplicação para todo sistema de backend (monolito) que deverá ser desenvolvido seguindo os
   padrões apresentados nas aulas:
    1. Utilizando arquitetura hexagonal
    2. APIs:
        1. Cadastro do Cliente
        2. Identificação do Cliente via CPF
        3. Criar, editar e remover de produto
        4. Buscar produtos por categoria
        5. Fake checkout, apenas enviar os produtos escolhidos para a fila
        6. Listar os pedidos
    3. Aplicação deverá ser escalável para atender grandes volumes nos horários de pico
    4. Banco de dados a nossa escolha
3. A aplicação deve ser entregue com um [Dockerfile](https://github.com/matheus-mr94/postech-soat1/blob/8761acfb11dec777636908b59e961b798ee6916f/Dockerfile) configurado para
   executá-la corretamente. Para validação da POC, temos a seguinte limitação de infraestrutura:
    1. 1 instância para banco de dados
    2. 1 instância para executar aplicação

</details>
<details>
  <summary> Segunda entrega</summary>

1. Arquivos de configuração do Kubernetes
   1. Deployment da aplicação com, ao menos, 2 pods
   2. Service para Load Balancer do tipo NLB ou ALB
   3. Configuração de acesso aos serviços da AWS parametrizados via secrets
2. Refatorar o código utilizando Clean Code e Clean Architecture
3. Alterar checkout do pedido para retornar a identificação
4. Endpoint para consulta de status de pagamento do pedido, informando se foi aprovado ou não
5. Webhook para receber confirmação de pagamento aprovado ou recusado
6. Reordenação do resultado da lista de pedidos, seguindo o padrão: Pronto > Em Preparação > Recebido;
Pedidos com status Finalizado não devem aparecer na lista.
7. Endpoint para atualização do status do pedido
8. Integração com o Mercado Pago para geração de QRCode para pagamento e integração
com webhook para captação desses pagamentos.

</details>

# Execução
1. Executar `git clone git@github.com:matheus-mr94/postech-soat1.git`
2. Rodar `docker-compose up --build` na raiz do projeto

# Executando com Kubernets

1 - Crie um arquivo mysql-secret.yaml dentro do diretório kubernetes/mysql.

```
apiVersion: v1
kind: Secret
metadata:
  name: mysql-secret
data:
  MYSQL_ROOT_PASSWORD: {CHAVE}
  MYSQL-DATABASE: {CHAVE}
  MYSQL-USER: {CHAVE}
  MYSQL-PASSWORD: {CHAVE}
```

Certifique-se de codificar as chaves em Base64 antes de inseri-las no arquivo.

Em seguida, execute o comando a seguir para criar o objeto:

```
kubectl apply -f kubernetes/mysql/mysql-secret.yaml
```

2 - Configurar banco de dados

Agora, configure os recursos do banco de dados. Primeiro, aplique o arquivo de solicitação de volume persistente:

```
kubectl apply -f kubernetes/mysql/mysql-persistentvolumeclaim.yaml
```

Em seguida, crie o deployment e o serviço MySQL:

```
kubectl apply -f kubernetes/mysql/mysql-deployment.yaml
kubectl apply -f kubernetes/mysql/mysql-service.yaml
```

3 - Configurar a API

Aplique o deployment e o serviço da aplicação:

```
kubectl apply -f kubernetes/app/app-deployment.yaml
kubectl apply -f kubernetes/app/app-service.yaml
```

# Funcionalidades
## Cadastro do Cliente
POST http://localhost:8080/clientes
```json
{
  "nome": "Ramin Paema",
  "email": "ramin@teste.com",
  "cpf": "95741879853"
}
```

## Identificação do Cliente via CPF
GET http://localhost:8080/clientes/{cpf}

## Criar, editar e remover produto
### Criação
POST http://localhost:8080/produtos
```json
{
  "nome": "Batata frita",
  "categoria": "ACOMPANHAMENTO",
  "preco": "5.50"
}
```

### Edição
PUT http://localhost:8080/produtos/{produtoId}/edita
```json
{
  "nome": "Batata frita",
  "categoria": "SOBREMESA",
  "preco": "10.00"
}
```

### Remoção
DELETE http://localhost:8080/produtos/{produtoId}/remove

## Buscar produtos por categoria
GET http://localhost:8080/produtos/categoria/{categoria}

## Criar, listar pedidos e atualizar status de um pedido
### Criar
POST http://localhost:8080/pedidos
```json
{
    "produtosPedidos": 
    [
        {
            "produtoId": 1,
            "quantidade": 2
        },
        {
            "produtoId": 1,
            "quantidade": 1
        }
    ]
}
```

Resposta:

![qrcode_pagamento.png](src%2Fmain%2Fresources%2Fimages%2Fqrcode_pagamento.png)

### Listar
GET http://localhost:8080/pedidos/todos

### Atualizar Status
POST http://localhost:8080/pedidos/atualiza-status
```json
{
  "idPedido": 1,
  "statusDoPedido": "RECEBIDO"
}
```
Por padrão, o status inicial do pedido será AGUARDANDO_PAGAMENTO.

## Consultar status de pagamento de um pedido e confirmar pagamento

### Consultar status de pagamento
GET http://localhost:8080/pagamentos/consulta-status-pagamento/pedido/{pedidoId}
Por padrão, após um pedido ser criado, o status inicial do pagamento dele será PENDENTE.

### Confirmar pagamento
POST http://localhost:8080/pagamentos/confirma-pagamento
```json
{
  "pedidoId": 1,
  "pagamentoStatus": "APROVADO"
}
```
Os status de pagamento disponíveis são: PENDENTE, APROVADO, REPROVADO, CANCELADO.

