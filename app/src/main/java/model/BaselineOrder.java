package model;

public class BaselineOrder {
    public static int total = 0;
    public BareOrder bareOrder;
    public OrderDiagnostic orderDiagnostic;

    public BaselineOrder() {
        System.out.println("Creating a BaselineOrder...");
    }

    public BaselineOrder(BareOrder bareOrder, OrderDiagnostic orderDiagnostic) {
        this.bareOrder = bareOrder;
        this.orderDiagnostic = orderDiagnostic;
        total ++;
        if (total >= 1000000 - 1) System.out.println("DONE");
//        System.out.println("Creating a BaselineOrder..." + bareOrder.key + "/" + orderDiagnostic.key + "DATA " + bareOrder.data + "/" + orderDiagnostic.data );
        System.out.println("Creating a BaselineOrder..." + bareOrder.key + "/" + orderDiagnostic.key);
    }
}
