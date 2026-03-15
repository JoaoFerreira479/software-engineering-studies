(function () {
    const STORAGE_KEY = 'moeda_demo_mode';
    const DATA_KEY = 'moeda_demo_data';

    window.isDemoMode = function () {
        return localStorage.getItem(STORAGE_KEY) === 'true';
    };

    window.setDemoMode = function (on) {
        if (on) {
            localStorage.setItem(STORAGE_KEY, 'true');
        } else {
            localStorage.removeItem(STORAGE_KEY);
            localStorage.removeItem(DATA_KEY);
            localStorage.removeItem('auth_token');
            localStorage.removeItem('moeda_demo_user');
        }
    };

    window.clearDemoData = function () {
        localStorage.removeItem(DATA_KEY);
        localStorage.removeItem('moeda_demo_user');
        localStorage.removeItem('auth_token');
        if (typeof window._demoData !== 'undefined') window._demoData = null;
    };

    function loadData() {
        try {
            const raw = localStorage.getItem(DATA_KEY);
            if (raw) return JSON.parse(raw);
        } catch (_) {}
        return getInitialData();
    }

    function saveData(data) {
        try {
            localStorage.setItem(DATA_KEY, JSON.stringify(data));
        } catch (_) {}
    }

    function getInitialData() {
        const instituicoes = [
            { id: 1, nome: 'Universidade Federal Demo', cnpj: '00.000.000/0001-91', endereco: 'Rua Demo, 100', telefone: null, email: null },
            { id: 2, nome: 'Instituto Tecnológico Demo', cnpj: '00.000.000/0002-72', endereco: 'Av. Exemplo, 200', telefone: null, email: null }
        ];
        const alunos = [
            { id: 1, nome: 'Ana Demo', email: 'aluno@demo.com', cpf: '111.111.111-11', rg: '11.111.111-1', endereco: 'Rua A, 1', curso: 'Engenharia', saldo: 50, instituicao: { id: 1, nome: instituicoes[0].nome } },
            { id: 2, nome: 'Bruno Demo', email: 'aluno2@demo.com', cpf: '222.222.222-22', rg: '22.222.222-2', endereco: 'Rua B, 2', curso: 'Computação', saldo: 30, instituicao: { id: 1, nome: instituicoes[0].nome } }
        ];
        const professores = [
            { id: 1, nome: 'Carlos Demo', email: 'professor@demo.com', cpf: '333.333.333-33', departamento: 'Exatas', saldo: 100, instituicao: { id: 1, nome: instituicoes[0].nome } },
            { id: 2, nome: 'Diana Demo', email: 'professor2@demo.com', cpf: '444.444.444-44', departamento: 'Humanas', saldo: 80, instituicao: { id: 1, nome: instituicoes[0].nome } }
        ];
        const empresas = [
            { id: 1, nome: 'Empresa Parceira Demo', email: 'empresa@demo.com', cnpj: '00.000.000/0003-53' }
        ];
        const vantagens = [
            { id: 1, descricao: 'Desconto 20% em livros', custo: 15, fotoUrl: null, codigoCupom: null, empresaId: 1, empresaNome: empresas[0].nome, empresa: { id: 1, nome: empresas[0].nome } },
            { id: 2, descricao: 'Café grátis na cantina', custo: 10, fotoUrl: null, codigoCupom: null, empresaId: 1, empresaNome: empresas[0].nome, empresa: { id: 1, nome: empresas[0].nome } },
            { id: 3, descricao: 'Camiseta do curso', custo: 25, fotoUrl: null, codigoCupom: null, empresaId: 1, empresaNome: empresas[0].nome, empresa: { id: 1, nome: empresas[0].nome } }
        ];
        const transacoes = [];
        return { instituicoes, alunos, professores, empresas, vantagens, transacoes };
    }

    function getData() {
        if (!window._demoData) window._demoData = loadData();
        return window._demoData;
    }

    function persist() {
        saveData(getData());
    }

    function getCurrentDemoUser() {
        try {
            const raw = localStorage.getItem('moeda_demo_user');
            return raw ? JSON.parse(raw) : null;
        } catch (_) {
            return null;
        }
    }

    function setCurrentDemoUser(user) {
        if (user) localStorage.setItem('moeda_demo_user', JSON.stringify(user));
        else localStorage.removeItem('moeda_demo_user');
    }

    function resolveUserByEmail(email) {
        const data = getData();
        const e = (email || '').toLowerCase();
        const aluno = data.alunos.find(a => (a.email || '').toLowerCase() === e);
        if (aluno) return { ...aluno, tipo: 'ALUNO', id: aluno.id };
        const professor = data.professores.find(p => (p.email || '').toLowerCase() === e);
        if (professor) return { ...professor, tipo: 'PROFESSOR', id: professor.id };
        const empresa = data.empresas.find(emp => (emp.email || '').toLowerCase() === e);
        if (empresa) return { ...empresa, tipo: 'EMPRESA', id: empresa.id };
        return null;
    }

    function nextId(arr, key) {
        const max = arr.length ? Math.max(...arr.map(x => x[key] || 0)) : 0;
        return max + 1;
    }

    function delay(ms) {
        return new Promise(r => setTimeout(r, ms || 50));
    }

    window.demoApi = {
        login: async (payload) => {
            await delay();
            const user = resolveUserByEmail(payload?.email);
            if (!user) throw new Error('Use em modo demo: aluno@demo.com, professor@demo.com ou empresa@demo.com (qualquer senha).');
            setCurrentDemoUser(user);
            localStorage.setItem('auth_token', 'demo-token');
            return { token: 'demo-token', id: user.id, tipo: user.tipo, nome: user.nome, email: user.email };
        },
        register: async () => { await delay(); throw new Error('Cadastro desativado no modo demonstração.'); },
        me: async () => {
            await delay();
            return getCurrentDemoUser();
        },

        getInstituicoes: async () => { await delay(); return getData().instituicoes; },
        getInstituicao: async (id) => {
            await delay();
            const i = getData().instituicoes.find(x => x.id === Number(id));
            if (!i) throw new Error('Instituição não encontrada');
            return i;
        },

        getAlunos: async () => { await delay(); return getData().alunos; },
        getAluno: async (id) => {
            await delay();
            const a = getData().alunos.find(x => x.id === Number(id));
            if (!a) throw new Error('Aluno não encontrado');
            return a;
        },
        createAluno: async () => { await delay(); throw new Error('Criar aluno desativado no modo demo.'); },
        updateAluno: async () => { await delay(); throw new Error('Atualizar aluno desativado no modo demo.'); },
        getExtratoAluno: async (id) => {
            await delay();
            const data = getData();
            const aluno = data.alunos.find(a => a.id === Number(id));
            if (!aluno) throw new Error('Aluno não encontrado');
            const transacoes = data.transacoes.filter(t => t.aluno && t.aluno.id === Number(id));
            return { saldo: aluno.saldo, transacoes };
        },

        getProfessores: async () => { await delay(); return getData().professores; },
        getProfessor: async (id) => {
            await delay();
            const p = getData().professores.find(x => x.id === Number(id));
            if (!p) throw new Error('Professor não encontrado');
            return p;
        },
        createProfessor: async () => { await delay(); throw new Error('Criar professor desativado no modo demo.'); },
        updateProfessor: async () => { await delay(); throw new Error('Atualizar professor desativado no modo demo.'); },
        getExtratoProfessor: async (id) => {
            await delay();
            const data = getData();
            const prof = data.professores.find(p => p.id === Number(id));
            if (!prof) throw new Error('Professor não encontrado');
            const transacoes = data.transacoes.filter(t => t.professor && t.professor.id === Number(id));
            return { saldo: prof.saldo, transacoes };
        },

        getEmpresas: async () => { await delay(); return getData().empresas; },
        getEmpresa: async (id) => {
            await delay();
            const e = getData().empresas.find(x => x.id === Number(id));
            if (!e) throw new Error('Empresa não encontrada');
            return e;
        },
        createEmpresa: async () => { await delay(); throw new Error('Criar empresa desativado no modo demo.'); },
        updateEmpresa: async () => { await delay(); throw new Error('Atualizar empresa desativado no modo demo.'); },

        getVantagens: async () => { await delay(); return getData().vantagens; },
        getVantagem: async (id) => {
            await delay();
            const v = getData().vantagens.find(x => x.id === Number(id));
            if (!v) throw new Error('Vantagem não encontrada');
            return v;
        },
        createVantagem: async (vantagem) => {
            await delay();
            const data = getData();
            const id = nextId(data.vantagens, 'id');
            const empresa = data.empresas.find(e => e.id === (vantagem.empresa?.id || vantagem.empresaId));
            const nova = {
                id, descricao: vantagem.descricao, custo: vantagem.custo, fotoUrl: vantagem.fotoUrl || null,
                codigoCupom: null, empresaId: empresa?.id, empresaNome: empresa?.nome, empresa: empresa || { id: 1, nome: 'Demo' }
            };
            data.vantagens.push(nova);
            persist();
            return nova;
        },
        updateVantagem: async () => { await delay(); throw new Error('Atualizar vantagem desativado no modo demo.'); },
        deleteVantagem: async (id) => {
            await delay();
            const data = getData();
            data.vantagens = data.vantagens.filter(v => v.id !== Number(id));
            persist();
            return null;
        },

        getTransacoes: async () => { await delay(); return getData().transacoes; },
        getTransacao: async (id) => {
            await delay();
            const t = getData().transacoes.find(x => x.id === Number(id));
            if (!t) throw new Error('Transação não encontrada');
            return t;
        },
        createTransacao: async (transacao) => {
            await delay();
            const data = getData();
            const tipo = (transacao.tipo || '').toUpperCase();

            if (tipo === 'ENVIO') {
                const profId = transacao.professor?.id;
                const alunoId = transacao.aluno?.id;
                const qty = transacao.quantidade || 0;
                if (!profId || !alunoId || qty <= 0) throw new Error('Professor, aluno e quantidade são obrigatórios.');
                const prof = data.professores.find(p => p.id === Number(profId));
                const aluno = data.alunos.find(a => a.id === Number(alunoId));
                if (!prof || !aluno) throw new Error('Professor ou aluno não encontrado.');
                if (prof.saldo < qty) throw new Error('Professor não possui saldo suficiente.');
                prof.saldo -= qty;
                aluno.saldo += qty;
                const tr = {
                    id: nextId(data.transacoes, 'id'),
                    tipo: 'ENVIO',
                    quantidade: qty,
                    motivo: transacao.motivo || 'Demo',
                    data: new Date().toISOString(),
                    professor: { id: prof.id, nome: prof.nome },
                    aluno: { id: aluno.id, nome: aluno.nome },
                    vantagem: null,
                    vantagemCupom: null
                };
                data.transacoes.push(tr);
                persist();
                return tr;
            }

            if (tipo === 'RESGATE') {
                const alunoId = transacao.aluno?.id;
                const vantagemId = transacao.vantagem?.id;
                const aluno = data.alunos.find(a => a.id === Number(alunoId));
                const vantagem = data.vantagens.find(v => v.id === Number(vantagemId));
                if (!aluno || !vantagem) throw new Error('Aluno e vantagem são obrigatórios.');
                if (aluno.saldo < vantagem.custo) throw new Error('Saldo insuficiente.');
                aluno.saldo -= vantagem.custo;
                const cupom = 'CUPOM-' + Math.random().toString(36).slice(2, 10).toUpperCase();
                const tr = {
                    id: nextId(data.transacoes, 'id'),
                    tipo: 'RESGATE',
                    quantidade: vantagem.custo,
                    motivo: transacao.motivo || 'Resgate demo',
                    data: new Date().toISOString(),
                    professor: null,
                    aluno: { id: aluno.id, nome: aluno.nome },
                    vantagem: { id: vantagem.id, descricao: vantagem.descricao, codigoCupom: vantagem.codigoCupom },
                    vantagemCupom: cupom
                };
                data.transacoes.push(tr);
                persist();
                return tr;
            }

            throw new Error('Tipo de transação não suportado no modo demo.');
        }
    };
})();
