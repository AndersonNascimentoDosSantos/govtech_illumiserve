# Configuração de Envio de E-mails via API no Oracle Database

Este guia fornece um passo a passo para configurar o envio de e-mails via API no Oracle Database. O exemplo apresentado utiliza a API UTL_HTTP para enviar os e-mails.

## Pré-requisitos

- Oracle Database versão 12 ou superior.
- Acesso como um usuário privilegiado (por exemplo, SYS ou SYSTEM).

## Passos

1. Verifique se a package UTL_HTTP está disponível no banco de dados. Execute a seguinte consulta para listar os objetos do banco de dados com o nome "UTL_HTTP" e do tipo "PACKAGE":

   ```sql
   SELECT * FROM dba_objects WHERE object_name = 'UTL_HTTP' AND object_type = 'PACKAGE';
   ```

   Certifique-se de que a package UTL_HTTP esteja presente no resultado da consulta.

2. Conceda o privilégio EXECUTE para a package UTL_HTTP ao usuário desejado. Substitua `username` pelo nome de usuário real:

   ```sql
   GRANT EXECUTE ON sys.utl_http TO username;
   ```

   Isso concede ao usuário permissão para executar a package UTL_HTTP.

   Observação: A concessão de privilégios em pacotes do sistema deve ser feita com cuidado, pois isso proporciona ao usuário capacidades potencialmente poderosas. Recomenda-se revisar e avaliar cuidadosamente os privilégios concedidos para garantir a segurança e evitar possíveis abusos.

## Conclusão

Após seguir os passos descritos neste guia, você terá configurado o envio de e-mails via API no Oracle Database utilizando a package UTL_HTTP. Certifique-se de adaptar os comandos e substituir os valores relevantes com base no seu ambiente específico.

Lembre-se de consultar a documentação oficial do Oracle Database para obter mais informações sobre o uso da package UTL_HTTP e outras funcionalidades relacionadas ao envio de e-mails.