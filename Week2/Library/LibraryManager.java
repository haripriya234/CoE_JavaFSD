class LibraryManager extends LibrarySystem {
    @Override
    public void addBook(Book book) { books.add(book); }

    @Override
    public void addUser(User user) { users.add(user); }

    @Override
    public synchronized void borrowBook(String ISBN, String userID) throws Exception {
        lock.lock();
        try {
            Book book = books.stream().filter(b -> b.getISBN().equals(ISBN) && !b.isBorrowed()).findFirst().orElseThrow(() -> new Exception("Book not available"));
            User user = users.stream().filter(u -> u.getUserID().equals(userID)).findFirst().orElseThrow(() -> new Exception("User not found"));
            if (user.getBorrowedBooks().size() >= 3) throw new Exception("Max books allowed exceeded");
            book.setBorrowed(true);
            user.getBorrowedBooks().add(book);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized void returnBook(String ISBN, String userID) throws Exception {
        lock.lock();
        try {
            User user = users.stream().filter(u -> u.getUserID().equals(userID)).findFirst().orElseThrow(() -> new Exception("User not found"));
            Book book = user.getBorrowedBooks().stream().filter(b -> b.getISBN().equals(ISBN)).findFirst().orElseThrow(() -> new Exception("Book not borrowed"));
            book.setBorrowed(false);
            user.getBorrowedBooks().remove(book);
            System.out.println(user.getName() + " returned " + book.getTitle());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void reserveBook(String ISBN, String userID) throws Exception {
        System.out.println("Feature not implemented yet.");
    }

    @Override
    public Book searchBook(String title) {
        return books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }
}
    