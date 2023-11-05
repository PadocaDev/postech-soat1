![postech.png](src%2Fmain%2Fresources%2Fimages%2Fpostech.png)


[![Java CI with Maven](https://github.com/matheus-mr94/postech-soat1/actions/workflows/maven.yml/badge.svg)](https://github.com/matheus-mr94/postech-soat1/actions/workflows/maven.yml) [![pipeline](https://github.com/matheus-mr94/postech-soat1/actions/workflows/pipeline.yml/badge.svg)](https://github.com/matheus-mr94/postech-soat1/actions/workflows/pipeline.yml)

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
<details>
  <summary> Terceira entrega</summary>

1.  Implementar um API Gateway e um function serverless para autenticar o cliente com base no CPF:
    1. Integrar ao sistema de autenticação para identificar o cliente
2.  Implementar as melhores práticas de CI/CD para a aplicação, segregando os códigos em repositórios, por exemplo:
    1. 1 repositório para o Lambda.
    2. 1 repositório para sua infra Kubernetes com Terraform.
    3. 1 repositório para sua infra banco de dados gerenciaveis com Terraform.
    4. 1 repositório para sua aplicação que é executada no Kubernetes.
3.  Os repositórios devem fazer deploy automatizado na conta da nuvem utilizando actions. 
As branchs main/master devem ser protegidas, não permitindo commits direto. Sempre utilize pull request.
4.  Melhorar a estrutura do banco de dados escolhido, documentar seguindo os padrões de modelagem de dados e
justificar a escolha do banco de dados.
5. Você tem a liberdade para escolher qual a infra de nuvem desejar, mas terá de utilizar os 
serviços serverless: functions (AWS Lamba, Azure functions ou Google Functions, por exemplo), 
banco de dados gerenciáveis (AWS RDS, Banco de Dados do Azure ou Cloud SQL no GCP, por exemplo),
sistema de autenticação (AWS Cognito, Microsoft AD ou Google Identity platform no GCP, por exemplo).

</details>

# Modelagem de Dados
Atualmente, a aplicação desenvolvida é um monolito que utiliza um único
banco de dados para armazenar os diversas informações requeridas pelo projeto.
Nossa escolha atual do MySQL como banco de dados relacional, é fundamentada 
em nossa experiência, nas necessidades atuais do projeto e na familiaridade
com a tecnologia.

Nossa equipe está familiarizada com o ecossistema do MySQL.
Isso nos permite manter um ambiente de desenvolvimento eficiente e 
garantir a qualidade do código. Além disso, como um banco relacional,
atende às nossas necessidades de integridade referencial e estruturação 
avançada. Ele é um ótimo caminho para manter a consistência e
confiabilidade dos dados.

Ao fazer essa escolha, estamos estabelecendo uma base sólida para a 
futura migração para o [Amazon RDS](https://aws.amazon.com/pt/rds/mysql/?pg=ln&sec=hiw), que oferece escalabilidade automática,
alta performance, segurança robusta e gerenciamento simplificado, 
alinhado com nossos objetivos de evolução tecnológica e entrega eficiente.
Além disso, o RDS oferece suporte ao MySQL, o que simplificará a transição 
ao passarmos à uma arquitetura serverless, tendo em vista que a 
aplicação continuará como um monolito neste momento.

Conforme a evolução do projeto, novas análises e reestruturações 
de arquitetura e modelagem podem ser necessárias. Estamos comprometidos em
manter a qualidade e o progresso contínuo da nossa aplicação, e a 
migração para o Amazon RDS é um passo significativo nessa direção.

## Modelo atual do banco de dados

![data_modeling.png](src%2Fmain%2Fresources%2Fimages%2Fdata_modeling.png)


## Flyway

O [Flyway](https://github.com/flyway/flyway) é uma ferramenta de código aberto que é usada para gerenciar e 
automatizar migrações de bancos de dados. Ele é amplamente utilizado no 
desenvolvimento de aplicativos e na manutenção de bancos de dados, 
especialmente em contextos de desenvolvimento ágil e DevOps. O Flyway ajuda 
as equipes de desenvolvimento a controlar as mudanças na estrutura do 
banco e a garantir que essas mudanças sejam aplicadas de forma consistente 
em diferentes ambientes.

A estrutura de documentação das alterações no banco de dados é a seguinte:

```
.
├── ...
├── src      
│   ├── main
│   │   ├── java
│   │   └── resources
│   │       ├── db.migration        #Guarda os arquivos com as atualizações
│   │       └── ...
│   └── test           
├── criar_migration.sh              #Cria o template de arquivo
├── README.md             
└── ...

```


# Execução

## Utilizando com Docker Compose
1. Executar `git clone git@github.com:matheus-mr94/postech-soat1.git`
2. Rodar `docker-compose up --build` na raiz do projeto

## Utilizando com Kubernets

1. Crie um arquivo mysql-secret.yaml dentro do diretório kubernetes/mysql.

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

2. Configurar banco de dados

Agora, configure os recursos do banco de dados. Primeiro, aplique o arquivo de solicitação de volume persistente:

```
kubectl apply -f kubernetes/mysql/mysql-persistentvolumeclaim.yaml
```

Em seguida, crie o deployment e o serviço MySQL:

```
kubectl apply -f kubernetes/mysql/mysql-deployment.yaml
kubectl apply -f kubernetes/mysql/mysql-service.yaml
```

3. Configurar a API

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

