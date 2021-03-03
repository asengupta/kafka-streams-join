package deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.BaselineOrder;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class BaselineOrderDeserializer implements Deserializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            BaselineOrder baselineOrder = objectMapper.readValue(bytes, BaselineOrder.class);
            return baselineOrder;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        try {
            BaselineOrder baselineOrder = objectMapper.readValue(data, BaselineOrder.class);
            return baselineOrder;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {

    }
}
