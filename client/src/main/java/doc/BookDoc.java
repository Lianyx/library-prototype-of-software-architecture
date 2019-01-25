package doc;

import lombok.Getter;
import object.enun.BookType;
import object.po.Book;
import presentation.readerui.EBookReader;
import presentation.readerui.HTMLReader;
import presentation.readerui.PDFReader;

@Getter
public class BookDoc {
    private Book book;
    private EBookReader reader;

    public BookDoc(Book book) {
        this.book = book;
        if (BookType.HTML.equals(book.getEbookType())) {
            reader = new HTMLReader(book.getId());
        } else if (BookType.PDF.equals(book.getEbookType())) {
            reader = new PDFReader(book.getId());
        } else {
            reader = new EBookReader(book.getId());
        }
    }
}
