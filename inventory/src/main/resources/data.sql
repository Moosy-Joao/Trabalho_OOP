-- Inserir categorias
INSERT INTO categorias (nome, descricao) VALUES 
('Cimento e Argamassa', 'Materiais para construção e acabamento'),
('Ferragens', 'Parafusos, pregos, dobradiças e ferramentas'),
('Madeira', 'Madeiras para construção e acabamento'),
('Tintas e Vernizes', 'Materiais para pintura e acabamento'),
('Hidráulica', 'Tubos, conexões e materiais hidráulicos'),
('Elétrica', 'Fios, cabos e materiais elétricos')
ON CONFLICT DO NOTHING;

-- Inserir fornecedores
INSERT INTO fornecedores (nome, cnpj, telefone, email) VALUES 
('Construtora ABC Ltda', '12345678000195', '11987654321', 'contato@construtorabc.com'),
('Materiais Silva S.A.', '98765432000187', '11876543210', 'vendas@materiaissilva.com'),
('Ferro & Cia', '45678912000134', '11765432109', 'comercial@ferroecia.com'),
('Madeireira Central', '78912345000156', '11654321098', 'atendimento@madeireiracentral.com'),
('Tintas Premium', '32165498000178', '11543210987', 'pedidos@tintaspremium.com')
ON CONFLICT DO NOTHING;

-- Inserir produtos (com verificação de existência das categorias)
INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Cimento Portland CP II-E-32', 'Saco de cimento 50kg', 100, 25.90, id FROM categorias WHERE nome = 'Cimento e Argamassa' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Argamassa AC-I', 'Argamassa colante interior 20kg', 50, 18.50, id FROM categorias WHERE nome = 'Cimento e Argamassa' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Parafuso Phillips 6x40mm', 'Parafuso para madeira', 500, 0.25, id FROM categorias WHERE nome = 'Ferragens' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Dobradiça 3 polegadas', 'Dobradiça em aço inox', 80, 12.90, id FROM categorias WHERE nome = 'Ferragens' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Tábua de Pinus 2,5x15x300cm', 'Madeira tratada para construção', 30, 45.00, id FROM categorias WHERE nome = 'Madeira' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Tinta Acrílica Branca 18L', 'Tinta para parede interna/externa', 25, 89.90, id FROM categorias WHERE nome = 'Tintas e Vernizes' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Tubo PVC 100mm 6m', 'Tubo para esgoto', 40, 35.80, id FROM categorias WHERE nome = 'Hidráulica' LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO produtos (nome, descricao, quantidade, preco_unitario, categoria_id) 
SELECT 'Fio elétrico 2,5mm 100m', 'Fio de cobre para instalação', 20, 125.00, id FROM categorias WHERE nome = 'Elétrica' LIMIT 1
ON CONFLICT DO NOTHING;