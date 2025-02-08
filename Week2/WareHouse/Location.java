public class Location {
   private int aisle;
   private int shelf;
   private int bin;

   // Constructors, getters, and setters

    public int getAisle() {
        return this.aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public int getShelf() {
        return this.shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getBin() {
        return this.bin;
    }

    public void setBin(int bin) {
        this.bin = bin;
    }

    public Location(int aisle, int shelf, int bin) {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

}
