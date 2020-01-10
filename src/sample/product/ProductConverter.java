package sample.product;

public class ProductConverter {
    public static Product toProduct(ProductProperty prodProperty) {
        return new Product(prodProperty.getId(), prodProperty.getName(),
                prodProperty.getQuantity(), prodProperty.getPrice());
    }

    public static ProductProperty toProperty(Product product) {
        return new ProductProperty(product.getId(), product.getName(),
                product.getQuantity(), product.getPrice());
    }
}
