package sample.cash_register.bundle;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Contains references to all nodes of a single cash register, defined in CashRegisters.fxml
 */
public class CashRegisterBundle {
    private Label cashRegisterId;
    private Label cashierName;
    private Label transactionCount;
    private Label currentTransactionId;
    private Label subtotalCost;
    private Label tax;
    private Label totalCost;

    protected CashRegisterBundle(Label cashRegisterId, Label cashierName, Label transactionCount,
                              Label currentTransactionId, Label subtotalCost, Label tax,
                              Label totalCost) {
        this.cashRegisterId = cashRegisterId;
        this.cashierName = cashierName;
        this.transactionCount = transactionCount;
        this.currentTransactionId = currentTransactionId;
        this.subtotalCost = subtotalCost;
        this.tax = tax;
        this.totalCost = totalCost;
    }

    public Label getCashRegisterId() {
        return cashRegisterId;
    }

    public Label getCashierName() {
        return cashierName;
    }

    public Label getTransactionCount() {
        return transactionCount;
    }

    public Label getCurrentTransactionId() {
        return currentTransactionId;
    }

    public Label getSubtotalCost() {
        return subtotalCost;
    }

    public Label getTax() {
        return tax;
    }

    public Label getTotalCost() {
        return totalCost;
    }
}
