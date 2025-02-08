import java.util.Arrays;
public class Main {
   public static void main(String[] args) {
      InventoryManager inventoryManager = new InventoryManager();
      
    inventoryManager.addProduct(new Product("P001", "Widget A", 10, new Location(1, 1, 1)));
    inventoryManager.addProduct(new Product("P002", "Widget B", 5, new Location(1, 1, 2)));
    inventoryManager.addProduct(new Product("P003", "Widget C", 0, new Location(1, 1, 3)));
    Order order1 = new Order("O001", Arrays.asList("P001", "P002"), Order.Priority.EXPEDITED);
    Order order2 = new Order("O002", Arrays.asList("P003", "P001"), Order.Priority.STANDARD);
    Order order3 = new Order("O003", Arrays.asList("P002"), Order.Priority.EXPEDITED);
    inventoryManager.addOrder(order1);
    inventoryManager.addOrder(order2);
    inventoryManager.addOrder(order3);
    inventoryManager.processOrders();
    inventoryManager.printInventory();
   }
}
