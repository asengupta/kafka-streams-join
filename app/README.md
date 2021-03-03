kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-input-stream
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic bare-order-input-stream
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-diagnostic-input-stream
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
kafka-server-start /usr/local/etc/kafka/server.properties
kafka-console-consumer --topic bare-order-input-stream --from-beginning --bootstrap-server localhost:9092
kafka-topics --list --zookeeper localhost:2181
kafka-topics --zookeeper localhost:2181 --alter --topic bare-order-input-stream --partitions 5
kafka-topics --zookeeper localhost:2181 --alter --topic order-diagnostic-input-stream --partitions 5
kafka-topics --zookeeper localhost:2181 --delete --topic order-input-stream
kafka-topics --zookeeper localhost:2181 --delete --topic bare-order-input-stream
kafka-topics --zookeeper localhost:2181 --delete --topic order-diagnostic-input-stream
rm -rf /usr/local/var/lib/kafka-logs/*
rm -rf /usr/local/var/lib/zookeeper/version-2/*
