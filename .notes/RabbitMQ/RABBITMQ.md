# Configurar o RabbitMQ manualmente

1. Criar a exchange "video.events"
- type: direct
- durable: true

2. Criar a fila "video.created.queue"
- type: classic
- durable: true

3. Criar a binding entre a exchange e a fila
- routing key: video.created

4. Criar a fila "video.encoded.queue"
- type: classic
- durable: true

5. Criar a binding entre a exchange e a fila
- routing key: video.encoded
