package serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class OrderDiagnosticSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(o);
//            System.out.println("BYTES = " + bytes);
            return bytes;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(data);
//            System.out.println("BYTES = " + bytes);
            return bytes;
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {

    }
}
