#!/bin/bash
# wait-for-postgres.sh

set -e

host="$1"
port="$2"
shift
cmd="$@"

until psql -h "$host" -p "$port" -U "aos" -c '\l'; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

>&2 echo "Postgres is up - executing command"
exec $cmd
