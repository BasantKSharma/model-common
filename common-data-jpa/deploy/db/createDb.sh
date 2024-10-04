#!/bin/bash

#docker volume rm db_mysql_data
docker rm -f mysql
docker rm -f db-ui-mysql
docker volume prune -f

docker-compose --env-file ../.env -f docker-compose-mysql.yml up -d --force-recreate
#!/bin/bash

../deleteDanglingImages.sh