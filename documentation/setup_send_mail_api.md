Passo 1: Crie uma função ou procedure para enviar e-mails usando api do onesignal
```sql
CREATE OR REPLACE PROCEDURE enviar_email(
  p_destinatario IN VARCHAR2,
  p_assunto IN VARCHAR2,
  p_corpo IN VARCHAR2
) AS
  l_api_endpoint VARCHAR2(2000) := 'https://onesignal.com/api/v1/notifications'; -- Endpoint da API do provedor de e-mail
  l_api_key VARCHAR2(200) := 'ODRhYmMxYzItM2JiMC00OGQ1LWIwZDQtN2ZhODg5MjJlYzlj'; -- Chave de API fornecida pelo provedor de e-mail
  
  l_request_body VARCHAR2(32767);
  l_response CLOB;
BEGIN
  -- Construa o corpo da solicitação HTTP
  l_request_body := '{"include_email_tokens": "' || p_destinatario || '", "email_subject": "' || p_assunto || '", "email_body": "' || p_corpo || '", "app_id": "856070f3-24a6-4300-8dd8-392471bafd30"}';
  
  -- Use a biblioteca UTL_HTTP para enviar a solicitação HTTP POST para a API do provedor de e-mail
  l_response := utl_http.request(
    url => l_api_endpoint,
    method => 'POST',
    headers => utl_http.set_header_list(
      'Content-Type', 'application/json',
      'Authorization', 'Bearer ' || l_api_key
    ),
    body => l_request_body
  );
  
  -- Exemplo de código para exibir a resposta da API no console
  dbms_output.put_line('Resposta da API: ' || l_response);
END;
/


```

Passo 2: Crie triggers para acionar o envio de notificações de e-mail

1. Trigger for Confirmação de Cadastro:
```sql
CREATE OR REPLACE TRIGGER trg_confirmacao_cadastro
AFTER INSERT ON TBL_USUARIO
FOR EACH ROW
BEGIN
    -- Construct the email body
    DECLARE
        l_corpo_email VARCHAR2(2000);
    BEGIN
        l_corpo_email := '<html><head>Bem-vindo ao nosso sistema</head><body><h1>Bem-vindo, ' || :NEW.NOM_NAME || '!</h1><p>Obrigado por se cadastrar em nosso sistema. Para ativar sua conta, clique <a href="https://exemplo.com/ativar_conta?usuario=' || :NEW.ID_USUARIO || '">aqui</a>.</p></body></html>';
    
        -- Call the email sending procedure
        enviar_email(:NEW.DS_EMAIL, 'Confirmação de Cadastro', l_corpo_email);
    END;
END;
/
```

2. Trigger for Recuperação de Senha:
```sql
CREATE OR REPLACE TRIGGER trg_recuperacao_senha
BEFORE UPDATE OF KEY_SENHA ON TBL_USUARIO
FOR EACH ROW
WHEN (NEW.KEY_SENHA IS NOT NULL)
BEGIN
    -- Generate a random password reset code
    DECLARE
        l_reset_code VARCHAR2(50);
    BEGIN
        l_reset_code := DBMS_RANDOM.STRING('X', 10);
        :NEW.reset_code := l_reset_code;
    END;
    
    -- Construct the email body
    DECLARE
        l_corpo_email VARCHAR2(2000);
    BEGIN
        l_corpo_email := '<html><head>Recuperação de Senha</head><body><h1>Olá, ' || :NEW.NOM_NAME || '!</h1><p>Recebemos uma solicitação para redefinir sua senha. Para redefinir sua senha, clique <a href="https://exemplo.com/redefinir_senha?usuario=' || :NEW.ID_USUARIO || '&reset_code=' || l_reset_code || '">aqui</a>.</p></body></html>';
    
        -- Call the email sending procedure
        enviar_email(:NEW.DS_EMAIL, 'Recuperação de Senha', l_corpo_email);
    END;
END;


/
```

3. Trigger for Notificação de Reclamação à Empresa por Parte do Usuário:
```sql
CREATE OR REPLACE TRIGGER trg_notificacao_reclamacao
AFTER INSERT ON TBL_RECLAMACAO
FOR EACH ROW
BEGIN
    -- Construct the email body
    DECLARE
        l_corpo_email VARCHAR2(2000);
    BEGIN
        l_corpo_email := '<html><head>Reclamação Recebida</head><body><h1>Olá, ' || :NEW.TBL_USUARIO.NOM_NAME || '!</h1><p>Recebemos sua reclamação e estamos trabalhando para resolvê-la. Entraremos em contato em breve.</p></body></html>';
    
        -- Call the email sending procedure
        enviar_email(:NEW.TBL_USUARIO.DS_EMAIL, 'Notificação de Reclamação', l_corpo_email);
    END;
END;
/
```

4. Trigger for Notificação de Resposta da Empresa a Reclamação do Usuário:
```sql
CREATE OR REPLACE TRIGGER trg_notificacao_resposta
AFTER INSERT OR UPDATE ON empresa_reclamacao
FOR EACH ROW
BEGIN
    -- Check if the status of the reclamacao is updated to 'Respondida'
    IF :NEW.TBL_RECLAMACAO.status = 'Respondida' THEN
        -- Construct the email body
        DECLARE


            l_corpo_email VARCHAR2(2000);
        BEGIN
            l_corpo_email := '<html><head>Resposta à Reclamação</head><body><h1>Olá, ' || :NEW.TBL_RECLAMACAO.TBL_USUARIO.NOM_NAME || '!</h1><p>A empresa ' || :NEW.TBL_EMPRESA.NOM_NAME || ' respondeu à sua reclamação. Acesse nosso sistema para ver a resposta.</p></body></html>';
        
            -- Call the email sending procedure
            enviar_email(:NEW.TBL_RECLAMACAO.TBL_USUARIO.DS_EMAIL, 'Notificação de Resposta', l_corpo_email);
        END;
    END IF;
END;
/
```




