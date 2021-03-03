kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-input-stream
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-input-stream
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
kafka-server-start /usr/local/etc/kafka/server.properties
kafka-console-consumer --topic bare-order-input-stream --from-beginning --bootstrap-server localhost:9092
