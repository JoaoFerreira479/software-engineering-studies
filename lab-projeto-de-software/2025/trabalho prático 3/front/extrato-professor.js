document.addEventListener('DOMContentLoaded', async () => {
    const currentUser = await auth.getCurrentUser() || null;

    if (!currentUser) {
        showMessage('❌ Usuário não autenticado. Faça login para ver seu extrato.', 'error');
        return;
    }

    const hidden = document.getElementById('hidden-professor-id');
    if (hidden) hidden.value = currentUser.id;
    const placeholder = document.getElementById('current-professor-placeholder');
    if (placeholder) placeholder.innerHTML = `<p><strong>Professor:</strong> ${currentUser.nome} (${currentUser.matricula || currentUser.id})</p>`;

    try {
        showLoading(true);
        hideMessage();

        const extrato = await api.getExtratoProfessor(currentUser.id);

        document.getElementById('saldo-valor').textContent = `${extrato.saldo || 0} moedas`;

        exibirTransacoes(extrato.transacoes || []);

        document.getElementById('extrato-container').style.display = 'block';
    } catch (error) {
        console.error('Erro ao consultar extrato do professor atual:', error);
        const errorMsg = error.message || 'Erro desconhecido ao consultar extrato';
        showMessage(`❌ ${errorMsg}`, 'error');
        document.getElementById('extrato-container').style.display = 'none';
    } finally {
        showLoading(false);
    }
});

function exibirTransacoes(transacoes) {
    const container = document.getElementById('transacoes-list');
    
    if (transacoes.length === 0) {
        container.innerHTML = '<div class="empty-state"><p>Nenhuma transação encontrada.</p></div>';
        return;
    }

    transacoes.sort((a, b) => new Date(b.data) - new Date(a.data));
    
    container.innerHTML = '';
    
    transacoes.forEach(transacao => {
        const item = criarItemTransacao(transacao);
        container.appendChild(item);
    });
}

function criarItemTransacao(transacao) {
    const item = document.createElement('div');
    item.className = 'transacao-item';
    
    const data = new Date(transacao.data).toLocaleString('pt-BR');
    const quantidade = transacao.quantidade || 0;
    const motivo = transacao.motivo || 'Sem motivo informado';
    const alunoNome = transacao.alunoNome ?? transacao.aluno?.nome ?? 'Aluno não informado';
    
    item.innerHTML = `
        <div class="transacao-header">
            <span class="transacao-tipo resgate">📤 Envio de Moedas</span>
            <span class="transacao-data">${data}</span>
        </div>
        <div class="transacao-valor resgate">
            - ${quantidade} moedas
        </div>
        <div class="transacao-motivo">
            <strong>Para:</strong> ${alunoNome}
        </div>
        <div class="transacao-detalhes">
            <strong>Motivo:</strong> ${motivo}
        </div>
    `;
    
    return item;
}

function showLoading(show) {
    const loading = document.getElementById('loading');
    if (show) {
        loading.classList.add('active');
    } else {
        loading.classList.remove('active');
    }
}

function showMessage(message, type = 'info') {
    const messageDiv = document.getElementById('message');
    messageDiv.innerHTML = message;
    messageDiv.className = `message ${type}`;
    messageDiv.style.display = 'block';
    
    if (type !== 'error') {
        setTimeout(() => {
            hideMessage();
        }, 5000);
    }
}

function hideMessage() {
    const messageDiv = document.getElementById('message');
    messageDiv.style.display = 'none';
}

