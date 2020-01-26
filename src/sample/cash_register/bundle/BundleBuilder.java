package sample.cash_register.bundle;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.cash_register.CashRegisterProperty;

public class BundleBuilder {
    private Label cashRegisterId;
    private Label cashierName;
    private Label transactionCount;
    private Label currentTransactionId;
    private Label subtotalCost;
    private Label tax;
    private Label totalCost;

    private TableView<CashRegisterProperty> tableView;

    public BundleBuilder(Label cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }

    public CashRegisterBundle build() {
        return new CashRegisterBundle(cashRegisterId, cashierName, transactionCount,
                currentTransactionId, subtotalCost, tax,
                totalCost);
    }

    public BundleBuilder cashierName(Label cashierName) {
        this.cashierName = cashierName;
        return this;
    }

    public BundleBuilder transactionCount(Label transactionCount) {
        this.transactionCount = transactionCount;
        return this;
    }

    public BundleBuilder currentTransactionId(Label currentTransactionId) {
        this.currentTransactionId = currentTransactionId;
        return this;
    }

    public BundleBuilder subtotalCost(Label subtotalCost) {
        this.subtotalCost = subtotalCost;
        return this;
    }

    public BundleBuilder tax(Label tax) {
        this.tax = tax;
        return this;
    }

    public BundleBuilder totalCost(Label totalCost) {
        this.totalCost = totalCost;
        return this;
    }
}
