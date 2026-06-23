#!/bin/bash

echo "=========================================================="
echo "  Iniciando Verificação para Ambiente de Produção (Prod)  "
echo "=========================================================="

# Função para checar variáveis de ambiente
check_env_var() {
    VAR_NAME=$1
    if [ -z "${!VAR_NAME}" ]; then
        echo "❌ ERRO: A variável de ambiente $VAR_NAME não está configurada."
        return 1
    else
        echo "✅ $VAR_NAME está configurada."
        return 0
    fi
}

ERRORS=0

# Lista de variáveis obrigatórias para produção
check_env_var "SPRING_DATASOURCE_URL" || ERRORS=$((ERRORS+1))
check_env_var "SPRING_DATASOURCE_USERNAME" || ERRORS=$((ERRORS+1))
check_env_var "SPRING_DATASOURCE_PASSWORD" || ERRORS=$((ERRORS+1))
check_env_var "SPRING_SECURITY_USER_PASSWORD" || ERRORS=$((ERRORS+1))

if [ $ERRORS -gt 0 ]; then
    echo "----------------------------------------------------------"
    echo "❌ Falha na inicialização. Configure as variáveis acima antes de rodar em Produção."
    echo "Exemplo de execução:"
    echo "  export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/meubanco"
    echo "  export SPRING_DATASOURCE_USERNAME=usuario_prod"
    echo "  export SPRING_DATASOURCE_PASSWORD=senha_prod"
    echo "  export SPRING_SECURITY_USER_PASSWORD=senha_admin_forte"
    echo "  bash scripts/run_prod.sh"
    echo "=========================================================="
    exit 1
fi

echo "----------------------------------------------------------"
echo "🚀 Iniciando aplicação Spring Boot no perfil: PROD"
echo "=========================================================="

export SPRING_PROFILES_ACTIVE=prod
./mvnw spring-boot:run
