package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.product.Product;

public class EditDialogController {
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField priceTextField;

    private Stage dialogStage;
    private Product product;

    public void handleOkButton(ActionEvent actionEvent) {
        // if price text field doesn't contain number, set it's text to original price
        // TODO only 1 dot should be permitted; currently crashes when more than 1 is in textfield
        if (!priceTextField.getText().matches("[0-9.]+")) {
            priceTextField.setText(Double.toString(product.getPrice()));
            return;
        }

        // if changes to input fields have been made - save them, otherwise close the dialog
        if (!product.getName().equals(nameTextField.getText())
                || product.getPrice() != Double.parseDouble(priceTextField.getText())) {
            product.setName(nameTextField.getText());
            product.setPrice(Double.parseDouble(priceTextField.getText()));
        }
        dialogStage.close();
    }
    public void handleCancelButton(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets fields info to product about to be edited.
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;

        nameTextField.setText(product.getName());
        priceTextField.setText(Double.toString(product.getPrice()));
    }

    public Product getProduct() {
        return product;
    }
}
