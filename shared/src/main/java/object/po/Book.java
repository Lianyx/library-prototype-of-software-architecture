package object.po;

import object.enun.Category;

public class Book {
    private String id, name, author, ebookDir;
    private Category category;

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

    public String getEbookDir() {
        return ebookDir;
    }

    public void setEbookDir(String ebookDir) {
        this.ebookDir = ebookDir;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
