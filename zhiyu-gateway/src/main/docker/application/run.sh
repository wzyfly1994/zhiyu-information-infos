#!/usr/bin/env bash
java $JAVA_OPTS -jar -Djava.security.egd=file:/dev/./urandom /app.jar \
    --group=$ACM_GROUP \
    --endpoint=$ACM_END_POINT \
    --fileExtension=$ACM_FILE_EXTENSION \
    --namespace=$ACM_NAMESPACE \
    --ramRoleName=$ACM_RAM_ROLE_NAME \
    --server.port=$SERVER_PORT
