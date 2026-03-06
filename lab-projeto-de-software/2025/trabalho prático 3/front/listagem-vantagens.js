document.addEventListener('DOMContentLoaded', () => {
    carregarVantagens();
    
    const refreshBtn = document.getElementById('refreshBtn');
    refreshBtn.addEventListener('click', carregarVantagens);
});

async function carregarVantagens() {
    const container = document.getElementById('vantagens-container');
    const loading = document.getElementById('loading');
    const messageDiv = document.getElementById('message');
    
    try {
        showLoading(true);
        hideMessage();

        let eu = await api.me();
        let vantagens = await api.getVantagens();

        if (eu && eu.tipo == 'EMPRESA') {
            vantagens = vantagens.filter(v => v.empresaId === eu.id);
        }

        if (vantagens && vantagens.length > 0) {
            await exibirVantagens(vantagens);
        } else {
            exibirEmptyState();
        }
    } catch (error) {
        console.error('Erro ao carregar vantagens:', error);
        const errorMsg = error.message || 'Erro desconhecido ao carregar vantagens';
        showMessage(`❌ ${errorMsg}`, 'error');
        container.innerHTML = '';
    } finally {
        showLoading(false);
    }
}

async function exibirVantagens(vantagens) {
    const container = document.getElementById('vantagens-container');
    container.innerHTML = '';

    vantagens.forEach(vantagem => {
        console.log('Vantagem:', vantagem);

        const card = criarCardVantagem(vantagem);
        container.appendChild(card);
    });
}

function criarCardVantagem(vantagem) {
    const card = document.createElement('div');
    card.className = 'vantagem-card';

    const empresaBadge = document.createElement('div');
    empresaBadge.className = 'empresa-badge';
    empresaBadge.textContent = vantagem.empresaNome || 'Empresa';
    card.appendChild(empresaBadge);

    if (vantagem.fotoUrl) {
        const img = document.createElement('img');
        img.src = vantagem.fotoUrl;
        img.alt = vantagem.descricao;
        img.className = 'vantagem-imagem';
        img.onerror = function() {
            this.style.display = 'none';
        };
        card.appendChild(img);
    } else {
        const placeholder = document.createElement('div');
        placeholder.className = 'vantagem-imagem';
        placeholder.textContent = '📷 Sem imagem';
        card.appendChild(placeholder);
    }

    const descricao = document.createElement('div');
    descricao.className = 'vantagem-descricao';
    descricao.textContent = vantagem.descricao;
    card.appendChild(descricao);

    const custoDiv = document.createElement('div');
    custoDiv.className = 'vantagem-custo';
    
    const custoValor = document.createElement('span');
    custoValor.className = 'custo-valor';
    custoValor.textContent = `${vantagem.custo} moedas`;
    
    const custoLabel = document.createElement('span');
    custoLabel.className = 'custo-label';
    custoLabel.textContent = '💰';
    
    custoDiv.appendChild(custoValor);
    custoDiv.appendChild(custoLabel);
    card.appendChild(custoDiv);
    
    return card;
}

function exibirEmptyState() {
    const container = document.getElementById('vantagens-container');
    container.innerHTML = `
        <div class="empty-state" style="grid-column: 1 / -1;">
            <h3>📭 Nenhuma vantagem disponível</h3>
            <p>Não há vantagens cadastradas no momento.</p>
            <p>As empresas parceiras podem cadastrar novas vantagens.</p>
        </div>
    `;
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
    messageDiv.textContent = message;
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
