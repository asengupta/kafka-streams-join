package kafka.scale;

import deserializers.BareOrderDeserializer;
import deserializers.OrderDiagnosticDeserializer;
import deserializers.OrderValueJoiner;
import model.BareOrder;
import model.BaselineOrder;
import model.OrderDiagnostic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import serializers.BareOrderSerializer;
import serializers.OrderDiagnosticSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TopologyApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        final Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-diagnostic-join");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        final StreamsBuilder streamsBuilder = new StreamsBuilder();
        final KStream<String, BareOrder> bareOrderStream = streamsBuilder.stream("bare-order-input-stream", Consumed.with(Serdes.String(), Serdes.serdeFrom(new BareOrderSerializer(),
                new BareOrderDeserializer())));
        final KStream<String, OrderDiagnostic> orderDiagnosticStream = streamsBuilder.stream("order-diagnostic-input-stream", Consumed.with(Serdes.String(), Serdes.serdeFrom(new OrderDiagnosticSerializer(), new OrderDiagnosticDeserializer())));
        KStream<String, BaselineOrder> joinedStream = bareOrderStream.join(orderDiagnosticStream, new OrderValueJoiner(),
                JoinWindows.of(TimeUnit.MINUTES.toMillis(5)), Joined.with(Serdes.String(),
                        Serdes.serdeFrom(new BareOrderSerializer(), new BareOrderDeserializer()),
                        Serdes.serdeFrom(new OrderDiagnosticSerializer(), new OrderDiagnosticDeserializer())));
//        joinedStream.to("baseline-order-output-stream");
        final Topology topology = streamsBuilder.build();
        final KafkaStreams streams = new KafkaStreams(topology, props);
        System.out.println(topology.describe());

        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
                System.out.println("Stopping Streams...");
            }
        });

        try {
            System.out.println("Starting Streams...");
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
    }
}
