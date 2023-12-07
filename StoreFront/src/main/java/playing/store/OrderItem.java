package WithJava.StoreFront.src.main.java.playing.store;

public class OrderItem {
    private Integer quantity;
    private ProductForSale product;

    private static Integer validateQuantity(Integer quantity) {
        String errorMessage = String.format("%n%s%n", "ERROR please use a quantity greater than zero!");
        Integer valueToReturn = -1;

        if (quantity <= 0) {
            System.out.printf("%s%s", " ".repeat(5), errorMessage);
        } else {
            valueToReturn = quantity;
        }

        return valueToReturn;
    }

    public OrderItem(ProductForSale product, Integer quantity) {
        this.product = product;
        this.quantity = validateQuantity(quantity);
    }

    public void setProduct(ProductForSale product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = validateQuantity(quantity);
    }

    public ProductForSale getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}




