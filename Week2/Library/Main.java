public class Main {
    public static void main(String[] args) {
        LibraryManager libManager = new LibraryManager();

        libManager.addBook(new Book("Java Programming", "James Gosling", "12345"));
        libManager.addBook(new Book("Data Structures", "Mark Weiss", "67890"));

        libManager.addUser(new User("Alice", "U001"));
        libManager.addUser(new User("Bob", "U002"));

        Thread t1 = new Thread(() -> {
            try {
                libManager.borrowBook("12345", "U001");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                libManager.borrowBook("12345", "U002");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}