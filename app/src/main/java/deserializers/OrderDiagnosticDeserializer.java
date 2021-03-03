package deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.OrderDiagnostic;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class OrderDiagnosticDeserializer implements Deserializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            OrderDiagnostic orderDiagnostic = objectMapper.readValue(bytes, OrderDiagnostic.class);
            return orderDiagnostic;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        try {
            OrderDiagnostic orderDiagnostic = objectMapper.readValue(data, OrderDiagnostic.class);
            return orderDiagnostic;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {

    }
}
