#!/bin/bash

echo "=== Criador de Branch e Commit Inicial ==="

# Solicitar o número da issue e o título da tarefa
read -p "Digite o número da issue (ex: 10): " ISSUE
read -p "Digite o título da tarefa (ex: Criar tela de login): " TITLE

# Solicitar o tipo de commit (feat, fix, chore, etc.)
read -p "Digite o tipo de commit (ex: feat, fix, chore, etc.): " COMMIT_TYPE

# Definir o nome da branch e a mensagem do commit
BRANCH="#$ISSUE"
COMMIT="$COMMIT_TYPE: $TITLE (#$ISSUE)"
TOUCH_FILE="#$ISSUE.txt"

# Ações do Git
git checkout main
git pull origin main
git checkout -b "$BRANCH"

# Criar um arquivo com o título da tarefa
echo "$TITLE" > "$TOUCH_FILE"
git add "$TOUCH_FILE"
git commit -m "$COMMIT"
git push origin "$BRANCH"

# Exibir confirmação
echo ""
echo "✅ Branch '$BRANCH' criada e enviada com commit:"
echo "    $COMMIT"
echo "📌 Pull Request será criado automaticamente."
