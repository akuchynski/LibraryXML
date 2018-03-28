package by.htp.library.dao.impl;

import java.util.Set;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.dom.BooksDomBuilder;

public class DOMBookDaoImpl implements BookDao {

	Set<Book> books;

	@Override
	public Set<Book> readAll() {

		BooksDomBuilder domBuilder = new BooksDomBuilder();
		domBuilder.buildSetBooks("resources/library.xml");
		books = domBuilder.getBooks();

		return books;
	}
}
