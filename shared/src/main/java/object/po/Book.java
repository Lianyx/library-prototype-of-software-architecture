package object.po;

import object.enun.BookType;

import java.io.Serializable;

public class Book implements Serializable {
    private String id, name, author, ebookPath;
    private boolean _available;
    private BookType ebookType;
    private Category category;

    public Book() {
        this.ebookPath = "";
        this.ebookType = BookType.NULL;
    }

    public Book(String id, String name, String author, String ebookPath, BookType ebookType, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.ebookPath = ebookPath;
        this.ebookType = ebookType;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", ebookPath='" + ebookPath + '\'' +
                ", ebookType=" + ebookType +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (ebookPath != null ? !ebookPath.equals(book.ebookPath) : book.ebookPath != null) return false;
        if (ebookType != book.ebookType) return false;
        return category != null ? category.equals(book.category) : book.category == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (ebookPath != null ? ebookPath.hashCode() : 0);
        result = 31 * result + (ebookType != null ? ebookType.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
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

    public boolean isAvailable() {
        return _available;
    }

    public void setAvailable(boolean _available) {
        this._available = _available;
    }
}
