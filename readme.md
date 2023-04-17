# Govtech IllumiServe

Este é um repositório do Govtech IllumiServe, uma plataforma que visa fornecer soluções para a iluminação pública em cidades. A plataforma permite que os cidadãos possam reportar problemas relacionados à iluminação pública, e permite que os gestores municipais possam gerenciar e solucionar esses problemas de forma eficiente.

## Como funciona

A plataforma é composta por dois aplicativos: um aplicativo para os cidadãos reportarem problemas de iluminação pública, e um aplicativo para os gestores municipais gerenciarem e solucionarem esses problemas.

### Aplicativo do cidadão

O aplicativo do cidadão permite que os usuários reportem problemas de iluminação pública em sua cidade. Para reportar um problema, o usuário precisa seguir os seguintes passos:

1. Abrir o aplicativo e selecionar a opção "Reportar problema".
2. Informar o tipo de problema (ex: lâmpada queimada, poste danificado, etc).
3. Informar a localização do problema (por exemplo, informando o endereço ou utilizando a função de geolocalização do aplicativo).
4. Anexar fotos do problema (opcional).
5. Enviar o relatório.

### Aplicativo do gestor municipal

O aplicativo do gestor municipal permite que os gestores municipais gerenciem e solucionem os problemas reportados pelos cidadãos. Para gerenciar os problemas, os gestores precisam seguir os seguintes passos:

1. Abrir o aplicativo e selecionar a opção "Gerenciar problemas".
2. Visualizar a lista de problemas reportados pelos cidadãos.
3. Selecionar um problema para ver mais detalhes.
4. Atribuir o problema para um técnico responsável.
5. Acompanhar o status do problema e atualizá-lo conforme necessário.
6. Marcar o problema como resolvido quando o mesmo for solucionado.

## Tecnologias utilizadas

A plataforma Govtech IllumiServe foi desenvolvida utilizando as seguintes tecnologias:

- React Native: para o desenvolvimento dos aplicativos móveis.
- Spring(java): para o desenvolvimento da API que conecta os aplicativos móveis com o banco de dados.[documentação](https://github.com/AndersonNascimentoDosSantos/govtech_illumiserve/tree/master/documentation)
- Oracle ou PostgreSQL: para o armazenamento dos dados.
## Atenção:
```
Ao usar outro banco de dados altere nas seguintes entidades o tipo de dado para TEXT:
 altere de columnDefinition = "CLOB"  para  columnDefinition = "TEXT"
 nas seguintes entidades:
 web_service/model/Avaliacao.java
 web_service/model/MensagemChat.java
 web_service/model/PostagemRedeSocial.java
 web_service/model/Notificacao.java
 web_service/model/MensagemForum.java
 web_service/model/TopicoForum.java
```

## Como contribuir

Você pode contribuir com a plataforma Govtech IllumiServe de várias maneiras, incluindo:

- Reportando bugs e problemas no repositório.
- Criando novas funcionalidades para a plataforma.
- Melhorando a documentação.

Se você quiser contribuir, basta seguir os seguintes passos:

1. Fork do repositório.
2. Faça as alterações que desejar em seu próprio repositório.
3. Envie um pull request para o repositório principal quando estiver satisfeito com suas alterações.

## Licença

Este projeto está licenciado sob a licença MIT. Leia o arquivo `LICENSE` para mais informações.
