/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kafka.scale;

import model.BareOrder;
import model.OrderDiagnostic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import serializers.BareOrderSerializer;
import serializers.OrderDiagnosticSerializer;

import java.util.Properties;
import java.util.UUID;

public class ProducerApp {
    public static void main(String[] args) throws Exception {
        String bareOrderTopicName = "bare-order-input-stream";
        String orderDiagnosticTopicName = "order-diagnostic-input-stream";

        Properties bareOrderProperties = createProperties(BareOrderSerializer.class.getName());
        Properties orderDiagnosticsProperties = createProperties(OrderDiagnosticSerializer.class.getName());

        Producer<String, BareOrder> bareOrderProducer = new KafkaProducer<>(bareOrderProperties);
        Producer<String, OrderDiagnostic> orderDiagnosticProducer = new KafkaProducer<>(orderDiagnosticsProperties);

        String uuid = UUID.randomUUID().toString() + "-";
        for (int i = 1; i <= 1000000; i++) {
            bareOrderProducer.send(new ProducerRecord<>(bareOrderTopicName, uuid + Integer.toString(i), new BareOrder(uuid + Integer.toString(i))));
            orderDiagnosticProducer.send(new ProducerRecord<>(orderDiagnosticTopicName, uuid + Integer.toString(i), new OrderDiagnostic(uuid + Integer.toString(i))));
        }
        System.out.println("Messages sent successfully");
        bareOrderProducer.close();
        orderDiagnosticProducer.close();
    }

    private static Properties createProperties(String serializerClass) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 1048588);
        props.put("linger.ms", 100);
        props.put("buffer.memory", 1048588);
//        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", serializerClass);
        return props;
    }
}
