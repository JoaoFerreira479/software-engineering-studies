document.addEventListener('DOMContentLoaded', async () => {
    const currentUser = await auth.getCurrentUser() || null;

    const hidden = document.getElementById('hidden-empresa-id');
    if (hidden && currentUser) hidden.value = currentUser.id;

    const form = document.getElementById('vantagemForm');
    form.addEventListener('submit', handleSubmit);
});

async function carregarEmpresas() {
    const selectEmpresa = document.getElementById('empresa');
    
    try {
        const empresas = await api.getEmpresas();
        
        selectEmpresa.innerHTML = '<option value="">Selecione uma empresa...</option>';
        
        if (empresas && empresas.length > 0) {
            empresas.forEach(empresa => {
                const option = document.createElement('option');
                option.value = empresa.id;
                option.textContent = `${empresa.nome} (${empresa.cnpj})`;
                selectEmpresa.appendChild(option);
            });
        } else {
            showMessage('ℹ️ Nenhuma empresa cadastrada. <a href="register.html">Cadastre-se como empresa</a>.', 'info');
        }
    } catch (error) {
        console.error('Erro ao carregar empresas:', error);
        const errorMsg = error.message || 'Erro desconhecido ao carregar empresas';
        showMessage(`❌ ${errorMsg}`, 'error');
    }
}

async function handleSubmit(event) {
    event.preventDefault();
    
    const form = event.target;
    const formData = new FormData(form);

    const empresaId = formData.get('empresa');
    const descricao = formData.get('descricao').trim();
    const custo = parseFloat(formData.get('custo'));
    const fotoUrl = formData.get('fotoUrl').trim();
    
    if (!empresaId) {
        showMessage('❌ Por favor, selecione uma empresa.', 'error');
        return;
    }
    
    if (!descricao) {
        showMessage('❌ Por favor, preencha a descrição da vantagem.', 'error');
        return;
    }
    
    if (!custo || custo <= 0) {
        showMessage('❌ Por favor, informe um custo válido (maior que zero).', 'error');
        return;
    }

    let empresa;
    try {
        empresa = await api.getEmpresa(empresaId);
    } catch (error) {
        const errorMsg = error.message || 'Erro desconhecido ao buscar empresa';
        showMessage(`❌ ${errorMsg}`, 'error');
        return;
    }

    const vantagem = {
        descricao: descricao,
        custo: custo,
        fotoUrl: fotoUrl || null,
        empresa: {
            id: empresa.id
        }
    };

    try {
        showLoading(true);
        const resultado = await api.createVantagem(vantagem);
        
        showMessage('✅ Vantagem cadastrada com sucesso!', 'success');
        form.reset();

        setTimeout(() => {
            window.location.href = 'listagem-vantagens.html';
        }, 2000);
    } catch (error) {
        console.error('Erro ao cadastrar vantagem:', error);
        const errorMsg = error.message || 'Erro desconhecido ao cadastrar vantagem';
        showMessage(`❌ ${errorMsg}`, 'error');
    } finally {
        showLoading(false);
    }
}

function showMessage(message, type = 'info') {
    const messageDiv = document.getElementById('message');
    messageDiv.innerHTML = message;
    messageDiv.className = `message ${type}`;
    messageDiv.style.display = 'block';
    
    if (type !== 'error') {
        setTimeout(() => {
            messageDiv.style.display = 'none';
        }, 5000);
    }
}

function showLoading(show) {
    const form = document.getElementById('vantagemForm');
    const submitBtn = form.querySelector('button[type="submit"]');
    
    if (show) {
        submitBtn.disabled = true;
        submitBtn.textContent = 'Cadastrando...';
    } else {
        submitBtn.disabled = false;
        submitBtn.textContent = 'Cadastrar Vantagem';
    }
}
