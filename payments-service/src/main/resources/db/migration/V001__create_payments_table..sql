-- V001__create_payments_table..sql
-- Script de migração para criar a tabela de pagamentos:
CREATE TABLE pagamentos (

    id BINARY(16) NOT NULL,
    
    -- Chave de idempotência para evitar duplicidade de transações
    chave_idempotencia VARCHAR(100) UNIQUE,

    -- Valores financeiros
    valor DECIMAL(19,4) NOT NULL,
    moeda VARCHAR(3) DEFAULT 'BRL',

    -- Referências de Domínio (IDs de quem paga e quem recebe)
    pagador_id BINARY(16) NOT NULL,
    recebedor_id BINARY(16) NOT NULL,

    -- Detalhes da Operação
    metodo_pagamento VARCHAR(30) NOT NULL, -- Ex: PIX, CARTAO_CREDITO
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE', -- PENDENTE, SUCESSO, FALHA
    
    -- Informações Bancárias/Externas
    nome_banco VARCHAR(100),
    referencia_externa VARCHAR(255), -- ID no provedor (Stripe, Asaas, etc)
    
    -- Controle de Concorrência e Auditoria (Essencial na Clean Arch)
    versao INT NOT NULL DEFAULT 0, 
    criado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    atualizado_em TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    confirmado_em TIMESTAMP(3) NULL,

    -- Campo flexível para logs ou dados específicos de cada método
    informacoes_adicionais JSON NULL,

    PRIMARY KEY (id),
    INDEX idx_pagamentos_pagador (pagador_id),
    INDEX idx_pagamentos_status (status),
    INDEX idx_pagamentos_criacao (criado_em)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;