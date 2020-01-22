package sample.transaction.util;

import java.io.Serializable;

public class Cost implements Serializable {
    private double subTotal;
    private double tax = 0.23;
    private double total;

    private Cost(double subTotal, double total) {
        this.subTotal = subTotal;
        this.total = total;
    }

    public static Cost of(double subTotal, double total) {
        return new Cost(subTotal, total);
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
