package doc;

import lombok.Getter;
import object.po.Book;
import presentation.readerui.EBookReader;
import presentation.readerui.HTMLReader;

@Getter
public class BookDoc {
    private Book book;
    private EBookReader reader;

    public BookDoc(Book book) {
        this.book = book;
        reader = new HTMLReader(book.getId());
//        if (book.getEbookType().equals(BookType.HTML)) {
//            reader = new HTMLReader(book.getId());
//        } else if (book.getEbookType().equals(BookType.PDF)) {
//            reader = new PDFReaderUIController(book.getId());
//        } else {
//            reader = new BaseReaderUIController(book.getId());
//        }
    }
}
