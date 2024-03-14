-- Inserts para `Tarefa` com `pessoa_alocada`
INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada, finalizado)
VALUES ('Desenvolver Sistema', 'Desenvolvimento do sistema X',
DATEADD('DAY', 30, CURRENT_TIMESTAMP), 'DESENVOLVIMENTO', 100.0, 1, true);

INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada, finalizado)
VALUES ('Revisão Financeira', 'Revisar os relatórios financeiros do trimestre',
DATEADD('DAY', 15, CURRENT_TIMESTAMP), 'FINANCAS', 40.0, 2, true);

INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada, finalizado)
VALUES ('Campanha de Marketing', 'Preparar a nova campanha de marketing digital',
DATEADD('DAY', 20, CURRENT_TIMESTAMP), 'MARKETING', 60.0, 3, false);

INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada, finalizado)
VALUES ('Análise de Custos', 'Análise detalhada dos custos de produção',
DATEADD('DAY', 10, CURRENT_TIMESTAMP), 'FINANCAS', 30.0, 4, false);

INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada, finalizado)
VALUES ('Planejamento Orçamentário', 'Planejar o orçamento para o próximo ano',
DATEADD('DAY', 25, CURRENT_TIMESTAMP), 'FINANCAS', 50.0, 5, true);

INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada, finalizado)
VALUES ('Tarefa pendente', 'Tarefa pendente',
DATEADD('DAY', 25, CURRENT_TIMESTAMP), 'FINANCAS', 50.0, null, true);
