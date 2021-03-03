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
    }
}
