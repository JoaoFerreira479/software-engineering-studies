const API_BASE_URL = 'http://localhost:8080';

async function apiRequest(endpoint, method = 'GET', data = null) {
    const url = `${API_BASE_URL}${endpoint}`;
    const token = localStorage.getItem('auth_token');
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            ...(token ? { 'Authorization': `Bearer ${token}` } : {})
        },
    };

    if (data && (method === 'POST' || method === 'PUT')) {
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(url, options);

        if (response.status === 204) {
            return null;
        }

        const responseData = await response.json().catch(() => null);

        if (!response.ok) {
            let errorMessage = 'Erro desconhecido';

            if (responseData) {
                if (responseData.message) {
                    errorMessage = responseData.message;
                } else if (responseData.errors) {
                    const errors = Object.values(responseData.errors).join(', ');
                    errorMessage = `Erro de validação: ${errors}`;
                }
            } else {
                errorMessage = `Erro ${response.status}: ${response.statusText}`;
            }

            throw new Error(errorMessage);
        }

        return responseData;
    } catch (error) {
        if (error instanceof Error) {
            throw error;
        }

        console.error('Erro na requisição:', error);
        throw new Error('Erro de conexão com o servidor. Verifique se o back-end está rodando.');
    }
}

const api = {
    login: (payload) => apiRequest('/auth/login', 'POST', payload),
    register: (payload) => apiRequest('/auth/register', 'POST', payload),
    me: () => apiRequest('/auth/me'),

    getInstituicoes: () => apiRequest('/api/instituicoes'),
    getInstituicao: (id) => apiRequest(`/api/instituicoes/${id}`),

    getAlunos: () => apiRequest('/api/alunos'),
    getAluno: (id) => apiRequest(`/api/alunos/${id}`),
    createAluno: (aluno) => apiRequest('/api/alunos', 'POST', aluno),
    updateAluno: (id, aluno) => apiRequest(`/api/alunos/${id}`, 'PUT', aluno),
    getExtratoAluno: (id) => apiRequest(`/api/alunos/${id}/extrato`),

    getProfessores: () => apiRequest('/api/professores'),
    getProfessor: (id) => apiRequest(`/api/professores/${id}`),
    createProfessor: (professor) => apiRequest('/api/professores', 'POST', professor),
    updateProfessor: (id, professor) => apiRequest(`/api/professores/${id}`, 'PUT', professor),
    getExtratoProfessor: (id) => apiRequest(`/api/professores/${id}/extrato`),

    getEmpresas: () => apiRequest('/api/empresas'),
    getEmpresa: (id) => apiRequest(`/api/empresas/${id}`),
    createEmpresa: (empresa) => apiRequest('/api/empresas', 'POST', empresa),
    updateEmpresa: (id, empresa) => apiRequest(`/api/empresas/${id}`, 'PUT', empresa),

    getVantagens: () => apiRequest('/api/vantagens'),
    getVantagem: (id) => apiRequest(`/api/vantagens/${id}`),
    createVantagem: (vantagem) => apiRequest('/api/vantagens', 'POST', vantagem),
    updateVantagem: (id, vantagem) => apiRequest(`/api/vantagens/${id}`, 'PUT', vantagem),
    deleteVantagem: (id) => apiRequest(`/api/vantagens/${id}`, 'DELETE'),

    getTransacoes: () => apiRequest('/api/transacoes'),
    getTransacao: (id) => apiRequest(`/api/transacoes/${id}`),
    createTransacao: (transacao) => apiRequest('/api/transacoes', 'POST', transacao),
};

if (typeof window.isDemoMode !== 'undefined' && window.isDemoMode()) {
    Object.assign(api, window.demoApi);
}
