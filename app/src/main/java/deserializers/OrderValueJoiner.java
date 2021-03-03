package deserializers;

import model.BareOrder;
import model.BaselineOrder;
import model.OrderDiagnostic;
import org.apache.kafka.streams.kstream.ValueJoiner;

public class OrderValueJoiner implements ValueJoiner<BareOrder, OrderDiagnostic, BaselineOrder> {
    @Override
    public BaselineOrder apply(BareOrder bareOrder, OrderDiagnostic orderDiagnostic) {
        return new BaselineOrder();
    }
}
