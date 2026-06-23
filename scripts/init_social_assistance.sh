#!/bin/bash
echo "=========================================================="
echo " Inicializando ambiente ERP/CRM para Assistência Social"
echo "=========================================================="

echo "[1/3] Verificando dependências..."
if ! command -v mvn &> /dev/null
then
    echo "Aviso: O comando 'mvn' não foi encontrado globalmente."
    echo "Certifique-se de usar o wrapper './mvnw' para rodar o projeto."
fi

echo "[2/3] Verificando banco de dados H2..."
echo "O projeto está configurado para usar o H2 na memória. Nenhuma configuração adicional de banco é necessária para desenvolvimento inicial."

echo "[3/3] Variáveis de Ambiente..."
echo "Lembre-se de configurar a variável GOOGLE_MAPS_API_KEY no seu ambiente ou em application.properties caso vá testar a geolocalização."

echo "Pronto! Você pode iniciar o servidor rodando:"
echo " ./mvnw spring-boot:run"
echo "=========================================================="
