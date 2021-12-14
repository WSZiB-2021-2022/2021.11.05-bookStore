package pl.edu.wszib.book.store.model;

public class OrderPosition {
    //TODO ID field
    private Book book;
    private int quantity;

    public OrderPosition(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public OrderPosition() {
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
