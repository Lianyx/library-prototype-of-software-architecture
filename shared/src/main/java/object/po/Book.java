package object.po;

import object.enun.BookType;

public class Book {
    private String id, name, author, ebookPath;
    private BookType ebookType;
    private Category category;

    public Book() {
    }

    public Book(String id, String name, String author, String ebookPath, BookType ebookType, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.ebookPath = ebookPath;
        this.ebookType = ebookType;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEbookPath() {
        return ebookPath;
    }

    public void setEbookPath(String ebookPath) {
        this.ebookPath = ebookPath;
    }

    public BookType getEbookType() {
        return ebookType;
    }

    public void setEbookType(BookType ebookType) {
        this.ebookType = ebookType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
