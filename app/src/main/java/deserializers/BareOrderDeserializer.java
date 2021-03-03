package deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.BareOrder;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class BareOrderDeserializer implements Deserializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
//            System.out.println("Bytes = " + bytes);
            BareOrder bareOrder = objectMapper.readValue(bytes, BareOrder.class);
            return bareOrder;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        try {
//            System.out.println("Bytes = " + data);
            BareOrder bareOrder = objectMapper.readValue(data, BareOrder.class);
            return bareOrder;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {

    }
}
