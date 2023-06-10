#!/bin/bash

# Verifica se foi fornecido o nome da migration como argumento
if [ -z "$1" ]; then
  echo "Por favor, forneça um nome para a migration."
  exit 1
fi

# Diretório onde as migrations serão armazenadas
migrations_dir="src/main/resources/db/migration"

# Verifica se o diretório de migrations existe
if [ ! -d "$migrations_dir" ]; then
  echo "O diretório de migrations não existe: $migrations_dir"
  exit 1
fi

# Obtém a data atual
date="$(date '+%Y.%m.%d.%H.%M.%S')"

# Cria o nome do arquivo da migration usando a data e o nome fornecidos
migration_file="$migrations_dir/V${date}__$1.sql"

# Cria o arquivo da migration
touch "$migration_file"