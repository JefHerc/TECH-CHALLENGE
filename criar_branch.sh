#!/bin/bash

echo "=== Criador de Branch e Commit Inicial ==="

read -p "Digite o número da issue (ex: 10): " ISSUE
read -p "Digite o título da tarefa (ex: Criar tela de login): " TITLE

BRANCH="#$ISSUE"
COMMIT="chore: $TITLE (#$ISSUE)"
TOUCH_FILE="issue-$ISSUE.txt"

git checkout main
git pull origin main
git checkout -b "$BRANCH"

echo "$TITLE" > "$TOUCH_FILE"
git add "$TOUCH_FILE"
git commit -m "$COMMIT"
git push origin "$BRANCH"

echo ""
echo "✅ Branch '$BRANCH' criada e enviada com commit:"
echo "    $COMMIT"
echo "📌 Agora vá ao GitHub e crie um Pull Request para a issue #$ISSUE."
