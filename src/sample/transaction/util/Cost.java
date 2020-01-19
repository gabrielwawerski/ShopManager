package sample.transaction.util;

import java.io.Serializable;

public class Cost implements Serializable {
    private final double subTotal;
    private final double tax = 0.23;
    private final double total;

    public Cost(double subTotal, double total) {
        this.subTotal = subTotal;
        this.total = total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }
}
