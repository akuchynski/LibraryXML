package by.htp.library.dao;

import by.htp.library.bean.Book;
import java.util.Set;

public interface BookDao {

	Set<Book> readAll();

}
