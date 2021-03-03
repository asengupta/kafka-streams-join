package model;

public class BaselineOrder {
    public BareOrder bareOrder;
    public OrderDiagnostic orderDiagnostic;

    public BaselineOrder() {
        System.out.println("Creating a BaselineOrder...");
    }

    public BaselineOrder(BareOrder bareOrder, OrderDiagnostic orderDiagnostic) {
        this.bareOrder = bareOrder;
        this.orderDiagnostic = orderDiagnostic;
        System.out.println("Creating a BaselineOrder..." + bareOrder.key + "/" + orderDiagnostic.key);
    }

    public BaselineOrder(BareOrder bareOrder, OrderDiagnostic orderDiagnostic, boolean b) {
        System.out.println("This is from Joiner");
    }
}
