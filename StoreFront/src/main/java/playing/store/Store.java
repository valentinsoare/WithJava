package WithJava.StoreFront.src.main.java.playing.store;

import java.util.ArrayList;
import java.util.List;

public class Store {
    List<OrderItem> itemsOrder;
    List<ProductForSale> listWithProductsForSale;

    public Store() {
        this.itemsOrder = new ArrayList<>();
        this.listWithProductsForSale = new ArrayList<>();
    }

    public void addOrderItem(ProductForSale item, Integer quantity) {
        itemsOrder.add(new OrderItem(item, quantity));
    }

    public void printOrderItems() {
        int counting = 0;
        for (OrderItem item : itemsOrder) {
            System.out.printf("%s. Type of product = %s, Quantity = %s, Price calculated = %s%n", (counting + 1), item.getProduct().getType(), item.getQuantity(), item.getProduct().getSalesPrice(item.getQuantity()));
            counting++;
        }
    }

    public static void main(String[] args) {
        Laptop legionFive = new Laptop(482.20, "Gaming laptop with all new things",
                "Lenovo", "intel", 8, 15);
        Laptop speedPro = new Laptop(999.9, "All new shit",
                "Apple", "intel", 6, 17);
        Store myHome = new Store();

        myHome.addOrderItem(legionFive, 5);
        myHome.addOrderItem(speedPro, 10);

        myHome.printOrderItems();
    }

}
