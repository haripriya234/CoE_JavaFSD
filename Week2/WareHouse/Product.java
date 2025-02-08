public class Product {
   private String productID;
   private String name;
   private int quantity;
   private Location location;

   // Constructors, getters, and setters

    public String getProductID() {
        return this.productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Product(String productID, String name, int quantity, Location location) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

}
