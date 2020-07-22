# API de Publicações - Teste para Empresa Aurum

## Table of Contents

- [Visão Geral](#visão-geral)
- [Instalação](#instalação)
- [Documentação](#documentação)
- [Roadmap](#roadmap)





## API de Publicações

## Visão Geral:

Aplicação desenvolvida como requisito de etapada no processo de admissão da empresa Aurum.
O Objetivo da aplicação é a realização de uma API que seja capaz de:
Fazer uma API que recebe publicações e salva no banco
Quando a publicação for do tipo HEARING criar um compromisso na agenda com a data da publicação que pode vir na propriedade classifiedDate. Caso não consiga encontrar a data marcar para 3 dias uteis após a data encontrada na propriedade clippingDate
Caso a publicação venha marcada como  importante deve ser criado um alerta informando a chegada
Fazer uma API para excluir  uma ou todas publicações de um cliente
Fazer API para listar as publicações do cliente com paginação
Permitir que o usuário traga uma publicação específica e marcar ela como visualizada
Fazer uma API para listar todos os Alertas do usuario com paginação
Fazer uma API para listar todos os compromissos do usuario com paginação


#### Instalação:

##### Requisitos:
 * Java JDK
 * Maven


##### Para instalar o projeto é necessária a realização dos seguintes passos:
###### 1. Abra o terminal e digite na pasta raiz do projeto:
```
mvn install 
```
###### 2. após isso digite:
```
cd target
java [nome do arquivo gerado]
```

#### Documentação:

A documentação do projeto foi realizada através do Swagger, e se encontra disponível em [documentação](https://localhost:8080/swagger-ui.html)

#### Roadmap:

- [ ] Autenticação no ambiente com uso de OAUTH2
- [ ] Permitir que o usuário altere sua senha de acesso
- [ ] Criar perfis de usuários
- [ ] Continuous deploy
- [ ] Internacionalização das mensagens


[HOME](#table-of-contents)
