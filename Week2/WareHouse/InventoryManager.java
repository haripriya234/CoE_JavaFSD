import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Logger;

public class InventoryManager {
    private static final Logger logger = Logger.getLogger(InventoryManager.class.getName());
    private Map<String, Product> products;
    private PriorityBlockingQueue<Order> orderQueue;

    public InventoryManager() {
        products = new ConcurrentHashMap<>();
        orderQueue = new PriorityBlockingQueue<>(10, new OrderComparator());
    }

    public synchronized void addProduct(Product product) {
        products.put(product.getProductID(), product);
        logger.info("Added product: " + product.getName());
    }

    public void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            if (order != null) {
                new Thread(() -> fulfillOrder(order)).start();
            }
        }
    }

    public void fulfillOrder(Order order) {
        logger.info("Processing order: " + order.getOrderID());
        for (String productID : order.getProductIDs()) {
            Product product = products.get(productID);
            if (product != null && product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                logger.info("Fulfilled " + product.getName() + " from " + product.getLocation());
            } else {
                logger.warning("Out of stock for product: " + productID);
            }
        }
    }

    public void addOrder(Order order) {
        orderQueue.offer(order);
        logger.info("Added order: " + order.getOrderID());
    }

    public void printInventory() {
        System.out.println("Current Inventory:");
        products.values().forEach(product -> 
            System.out.println(product.getName() + " - Quantity: " + product.getQuantity() + " - Location: " + product.getLocation()));
    }
}