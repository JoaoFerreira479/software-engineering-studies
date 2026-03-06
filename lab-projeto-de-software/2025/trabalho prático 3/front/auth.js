const auth = (function () {
    function setAuth(authResp) {
        console.log('Setting auth with response:', authResp);
        if (!authResp || !authResp.token) return;
        localStorage.setItem('auth_token', authResp.token);
    }

    function clearAuth() {
        localStorage.removeItem('auth_token');
        if (typeof window.isDemoMode !== 'undefined' && window.isDemoMode()) {
            localStorage.removeItem('moeda_demo_user');
        }
    }

    async function getCurrentUser() {
        const token = localStorage.getItem('auth_token');
        if (!token) return null;

        let currentUser = await api.me().catch(err => {
            console.error('Erro ao obter dados do usuário atual:', err);
            return null;
        });

        return currentUser;
    }

    function logout(redirect = 'login.html') {
        clearAuth();
        window.location.href = redirect;
    }

    async function renderNav() {
        const user = await getCurrentUser();
        const header = document.createElement('header');
        header.style.cssText = `
            padding: 1.5rem 2rem;
            border-bottom: 2px solid #e9ecef;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: #ffffff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
            position: sticky;
            top: 1rem;
            z-index: 1000;
            margin: 0 auto 1.5rem;
            border-radius: 12px;
            max-width: 1200px;
            width: 100%;
            box-sizing: border-box;
        `;

        const title = document.createElement('div');
        title.style.cssText = `display:flex;align-items:center;gap:0.75rem;`;
        title.innerHTML = `<a href="index.html" style="text-decoration:none;color:#667eea;font-weight:700;font-size:1.75rem;display:flex;align-items:center;gap:0.75rem;">💰 Sistema de Moeda Estudantil</a>`;
        header.appendChild(title);

        const rightArea = document.createElement('div');
        rightArea.style.cssText = `display:flex;align-items:center;gap:1rem;margin-left:auto;`;

        if (typeof window.isDemoMode !== 'undefined' && window.setDemoMode) {
            const demoBtn = document.createElement('button');
            demoBtn.className = 'btn';
            if (window.isDemoMode()) {
                demoBtn.textContent = '🔌 Sair do modo demonstração';
                demoBtn.title = 'Voltar a usar o banco de dados';
                demoBtn.style.cssText = `padding:0.5rem 1rem;font-size:0.85rem;background:#6c757d;color:#fff;border:none;border-radius:8px;cursor:pointer;`;
                demoBtn.addEventListener('click', () => {
                    if (confirm('Desativar modo demonstração e voltar a usar o banco de dados?')) {
                        window.setDemoMode(false);
                        window.location.href = 'index.html';
                    }
                });
                rightArea.appendChild(demoBtn);
                if (typeof window.clearDemoData === 'function') {
                    const clearBtn = document.createElement('button');
                    clearBtn.textContent = '🗑️ Limpar dados';
                    clearBtn.title = 'Apagar todos os dados da demonstração e voltar ao estado inicial';
                    clearBtn.style.cssText = `padding:0.5rem 1rem;font-size:0.85rem;background:#dc3545;color:#fff;border:none;border-radius:8px;cursor:pointer;`;
                    clearBtn.addEventListener('click', () => {
                        if (confirm('Apagar todos os dados da demonstração? Saldos, transações e vantagens cadastradas voltarão ao estado inicial.')) {
                            window.clearDemoData();
                            window.location.reload();
                        }
                    });
                    rightArea.appendChild(clearBtn);
                }
            } else {
                demoBtn.textContent = '📴 Modo demonstração';
                demoBtn.title = 'Usar o sistema sem banco de dados (dados locais)';
                demoBtn.style.cssText = `padding:0.5rem 1rem;font-size:0.85rem;background:#28a745;color:#fff;border:none;border-radius:8px;cursor:pointer;`;
                demoBtn.addEventListener('click', () => {
                    if (confirm('Ativar modo demonstração? O sistema funcionará sem banco de dados, usando apenas dados locais.')) {
                        window.setDemoMode(true);
                        window.location.reload();
                    }
                });
            }
            if (!window.isDemoMode()) rightArea.appendChild(demoBtn);
        }

        if (user) {
            const logoutContainer = document.createElement('div');
            logoutContainer.style.cssText = `display:flex;align-items:center;gap:1rem;`;

            const userInfo = document.createElement('span');
            userInfo.style.cssText = `
                color: #6c757d;
                font-size: 0.9rem;
                font-weight: 500;
            `;
            userInfo.textContent = user.nome || user.email || 'Usuário';
            logoutContainer.appendChild(userInfo);

            const logoutBtn = document.createElement('button');
            logoutBtn.textContent = '🚪 Sair';
            logoutBtn.className = 'btn btn-danger';
            logoutBtn.style.cssText = `
                padding: 0.5rem 1rem;
                font-size: 0.9rem;
                min-width: auto;
            `;
            logoutBtn.addEventListener('click', () => {
                if (confirm('Deseja realmente sair do sistema?')) {
                    logout();
                }
            });
            logoutContainer.appendChild(logoutBtn);
            rightArea.appendChild(logoutContainer);
        }

        header.appendChild(rightArea);

        document.body.insertBefore(header, document.body.firstChild);
    }

    document.addEventListener('DOMContentLoaded', async () => {
        try { await renderNav(); } catch (_) { }
    });

    return {
        setAuth,
        getCurrentUser,
        logout,
        renderNav
    };
})();

if (typeof window !== 'undefined') window.auth = auth;
